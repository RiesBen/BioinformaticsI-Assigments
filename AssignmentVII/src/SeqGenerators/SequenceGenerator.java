package SeqGenerators;

import java.util.ArrayList;

import Alphabets.*;

public class SequenceGenerator {
	ArrayList<String> seq = new ArrayList<String>();
	int n; // is the Sequence lenght
	BasicAlphabets E; // contains the alphabet
	
	public SequenceGenerator(int n, BasicAlphabets E){
		this.n=n;
		this.E=E;
	}
	
	public void generateSeq(){
		if(seq != null){
			seq = new ArrayList<String>();	//Reset List if full
		}
		for (int i=0; i<n; i++){
			int x =(int) ((Math.random()*100)%E.getAlphabetLength());
			seq.add(E.getSymbolByIndex(x));
		}
	}
	
	public ArrayList<String> getSequence(){
		return seq;
	}
}
