package SeqGenerators;

import java.util.ArrayList;
import java.util.LinkedList;

import Alphabets.BasicAlphabets;

public class SetGenerator {

	private int k; // ammount of sequences in the set
	private int n; // lenght of each generated sequence
	private int d; // deviations in Motif
	private int l; // Motif length
	private ArrayList<String> M; //Motif
	private BasicAlphabets E; //Defines the alphabet for the Sequences
	private ArrayList<String>[] sequenceSet; // Set of generated Sequences
	private int[][] pos;
	
	private SequenceGenerator seqGen; // Object for Set generator
	
	public SetGenerator(int k, int l, int n, ArrayList<String> m, int d, BasicAlphabets E){

		this.k=k;
		this.n=n;
		this.d=d;
		this.M=m;
		this.E=E;
		
		pos= new int[k][2];
		sequenceSet = new ArrayList[k];
		seqGen = new SequenceGenerator(n-l,E); // Generates Sequences of the length of n-l (l is motif lenght)
		
		
		for(int i=0; i<k; i++){
			// 1. generate a sequence
			seqGen.generateSeq();
			sequenceSet[i] = seqGen.getSequence();
			
			//2. deviate motif if necessary:
			String tmpMotif;
			if(d>0){
				MotifDeviator motifDeviator = new MotifDeviator(M, d, E); //this object contains the method for deviation
				tmpMotif = motifDeviator.deviate().toString(); // Deviate Motif
			}
			else{
				tmpMotif=M.toString();
			}
			
			//3. Plant randomly the motif
			int randPos = (int) Math.round(Math.random()*100%(n-l));
			pos[i][0]= randPos;
			pos[i][1]= randPos+l;
			sequenceSet[i].add(randPos, tmpMotif);
		}
	}
	
	public int[][] getPositionsOfMotifs(){
		return pos;
	}
	public ArrayList<String>[] getSequenceSet(){
		return sequenceSet;
	}
}
