package Ex1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class Parser {
	private String filePath;
	private List<String[]> Sequences  = new LinkedList<String[]>();

	public Parser(String path){
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
					Sequences.add(e);
				}
				else if(Pattern.matches(".+",currLine) ){
					e[2]= e[2]+currLine;
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void readOut(){
		String[] e;
		int i =0;
		while(i < Sequences.size() && (e = Sequences.get(i)) != null ){
			System.out.println("Sequence-Number: "+e[0]);
			System.out.println("Header: "+e[1]);
			System.out.println("Sequence: "+e[2]);
			System.out.println();
			i++;
		}
	}

	public List<String[]> getSequences(){
		return Sequences;
	}
	
	public void createFastaFile(String file){
		try {
			String[] e;
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file, false)));
			for(int i=0; i<Sequences.size() && (e=Sequences.get(i))!=null; i++){
				out.println(e[1]);
				out.println(e[2]);
			}
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}