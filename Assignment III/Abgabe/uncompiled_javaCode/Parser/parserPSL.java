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

public class parserPSL {
	private String filePath;
	private String[][] content;

	public parserPSL(String path){
		this.filePath=path;
		readFile(path);
	}

	
	public void readFile(String file){
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			LinkedList<String> entries = extractLines(br);
			System.out.println("finished step 1");
			this.content = splitStringListToColumnArray(entries, 21, '\t');
			System.out.println("finished step 2");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	protected LinkedList<String> extractLines(BufferedReader br){
		LinkedList<String> entries = new LinkedList<String>();
		String currLine = "";	
		try {
			while((currLine =br.readLine()) != null){
				entries.add(currLine);
			}
		} catch (IOException e) {
			System.out.println("There was a problem in extractLines Methode");
			e.printStackTrace();
		}
		return entries; 
	}
	
	protected String[][] splitStringListToColumnArray(LinkedList<String> entries, int columns, char seperator){
		String[][] result= new String[entries.size()][columns];
		String temp="";	//builds up a string for to fill into the column.
		int headerLines=0;
		
		//Delete HEader.
		while(headerLines<5){
			String a=entries.pop();
			headerLines++;
		}
		
		//Split each line into the Columns
		int row =0;
			for(int x =0; x< entries.size(); x++){		//go through entry list
				char[] entry= entries.pop().toCharArray();
				int column=0;
				for(int y=0; y<entry.length; y++){		//go through entry and seperate each column
					if(seperator == entry[y]){		// jumpt to next column..
						if(temp==""){
							temp = "n.A.";
						}
						result[row][column]=temp;
						temp="";
						column++;
					}
					else if(' '==entry[y]){
						continue;
					}
					else{
						temp=temp+entry[y];	// concatenate the new char with already collected String.
					}
				}
				if(temp!=null){
					result[row][column]=temp;
					temp="";
				}
				row++;
			}
		return result;
	}

	public String[][] getSequences(){
		return content;
	}
	
}