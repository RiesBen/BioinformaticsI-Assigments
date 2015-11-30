package SeqGenerators;

import java.util.ArrayList;

import Alphabets.BasicAlphabets;

public class MotifDeviator {
	private int d;
	private ArrayList<String> M;
	private BasicAlphabets E;
	
	public MotifDeviator(ArrayList<String> M, int d,BasicAlphabets E){
		this.d = d;
		this.M = M;
		this.E = E;
	}
	
	public ArrayList<String> deviate(){
		ArrayList<String> tmpMotif = (ArrayList<String>) M.clone();
		int[] deviationPositions= new int[d];
		
		// Choose random positions, for modifications.
		for(int j=0; j<d; j++){
			int tmpPos= (int) Math.round(((Math.random()*100)%tmpMotif.size()));
			
			if(arrayContains( deviationPositions, tmpPos)){
				j--;
			}
			else{
				deviationPositions[j]=tmpPos;
			}
		}
		
		// Now modify every position now
		for(int j=0; j<deviationPositions.length; j++){
			String dev =E.getSymbolByIndex((int) (Math.random()*100)%E.getAlphabetLength());
				tmpMotif.set((deviationPositions[j]-1), dev );
		}		
		return tmpMotif;
	}
	
	public boolean arrayContains(int[] ar, int i){
		for(int j=0; j<ar.length; j++){
			if(ar[j]==i){
				return true;
			}
		}
		return false;
	}
	
}
