package Parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class parserFasta {
	private String filePath;
	private LinkedList<String[]> sequences  = new LinkedList<String[]>();
	private String[][] sequencesArray;
	
	public parserFasta(String path){
		this.filePath=path;
		readFile(path);
	}
	/**
		Used parts of the code from exercise!
	 */
	public void readFile(String file){
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String currLine = "";
			int x= 1;
			String[] e = new String [3];

			while((currLine =br.readLine()) != null){
				if(Pattern.matches(">.*", currLine)){
					e= new String[3];
					e[0]= ""+x;
					x++;
					e[1] = currLine;
					e[2]="";
					sequences.add(e);
				}
				else if(Pattern.matches(".+",currLine) ){
					e[2]= e[2]+currLine;
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		sequencesArray= new String[sequences.size()][3];
		for(int x=0; x<sequences.size(); x++){
			String[] temp = sequences.pop();
			for(int y=0; y<3; y++){
				sequencesArray[x][y]= temp[y];
			}
		}
	}

	public void readOut(){
		String[] e;
		int i =0;
		while(i < sequences.size() && (e = sequences.get(i)) != null ){
			System.out.println("Sequence-Number: "+e[0]);
			System.out.println("Header: "+e[1]);
			System.out.println("Sequence: "+e[2]);
			System.out.println();
			i++;
		}
	}

	public String[][] getsequences(){
		return sequencesArray;
	}
	
	public void createFastaFile(String file){
		try {
			String[] e;
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file, false)));
			for(int i=0; i<sequences.size() && (e=sequences.get(i))!=null; i++){
				out.println(e[1]);
				out.println(e[2]);
			}
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}