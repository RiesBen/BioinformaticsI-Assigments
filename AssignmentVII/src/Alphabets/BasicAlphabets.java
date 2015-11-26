package Alphabets;

public class BasicAlphabets {
	protected String[] E;
	
	public BasicAlphabets(){
	}
	
	public String getSymbolByIndex(int i){
		return E[i];
	}
	public int getAlphabetLength(){
		return E.length;
	}
}
