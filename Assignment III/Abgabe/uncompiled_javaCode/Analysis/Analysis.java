package Analysis;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.xml.soap.Node;



public class Analysis {
	String[][] content;
	String[][] reads;
	
	public Analysis(String[][] content, String[][] reads){
		this.content=content;
		this.reads=reads;
	}
	
	//for exercise 1 &2
	public int[] checkAmmountOfHits(){
		int reads=0;
		int unique=0;
		LinkedList<String> save = new LinkedList<String>();	// a temporary list to compare if a read was already reckognized
		next:
		for(int x=0; x < content.length; x++){	// go through whole output of blat
			ListIterator<String> iterator = save.listIterator();
			String a;
			while(iterator.hasNext() && (a= iterator.next())!= null){	// check if a sequence name was already recognized
				if(a!=null && a.equals(content[x][9])){
					unique--;
					continue next;
				}
			}
			reads++; // If there is a new read.
			unique++;
			save.add(content[x][9]);
//			System.out.println("line:"+x);
		}
		int[] result = new int[2];
		result[0]=reads;
		result[1]=unique;
		return result;
	}
	
	
	//for exercise 3
	public int mapped(){
		boolean[] mapped = new boolean[1691694]; // found externally out, there are 1691694 positions in the H. pylori genome (wordcount and then took letters) 
		Arrays.fill(mapped, Boolean.FALSE);
		int result=10;
		for(int x=0; x<content.length; x++){// generate array of Hpylori genome and the positions which are mapped.
			int start;
			int end;
			if(content[x][15]!=null){
				start= Integer.parseInt(content[x][15]);
				if(content[x][16]!=null){
					end= Integer.parseInt(content[x][16]);
					for(;start<=end;start++){
						if(mapped[start]==true){ // the position was already mapped!
							continue;
						}
						else{	//found new position, which was not mapped.
							mapped[start]=true;
						}
					}
				}
			}
		}
		
		for(int x=0; x<mapped.length; x++){ // count the not mapped positions
			if(mapped[x]==false){
				result++;
			}
		}
		return result;
	}
}
