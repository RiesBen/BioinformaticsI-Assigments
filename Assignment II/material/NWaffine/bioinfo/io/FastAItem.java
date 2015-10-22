package exampleCode.bioinfo.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;

/** 
 *
 * @authors Daniel Huson and Janko Dietzsch
 */

public class FastAItem {
	private String header;
	private String seq;
	
	/**
	 * default constructor
	 */
	public FastAItem() {
		this("","");
	}
	
	/**
	 * constructor with given header and sequence
	 * @param Header
	 * @param Sequence
	 */
	public FastAItem(String Header, String Sequence) {
		this.setHeader(Header);
		this.setSequence(Sequence);
	}
	
	/**
	 * @return Returns the header.
	 */
	public String getHeader() {
		return header;
	}
	/**
	 * @param header The header to set.
	 * @return The changed object itself.
	 */
	public FastAItem setHeader(String header) {
		this.header = header;
		return this;
	}
	/**
	 * @return Returns the seq.
	 */
	public String getSequence() {
		return seq;
	}
	/**
	 * @param seq The seq to set.
	 * @return The changed object itself.
	 */
	public FastAItem setSequence(String seq) {
		this.seq = seq;
		return this;
	}
	
	/**
     * read header and sequence in fastA format
     * from a character stream BufferedReader r
     * @param r
     * @throws java.io.IOException
     */
    public void read(BufferedReader br) throws IOException {
        String header = "";
        StringBuffer sequence = new StringBuffer("");

        String aLine = br.readLine().trim();
        
        // First there must be the header
        if (aLine.charAt(0) != '>') throw new IOException("Header signal '>' expected and not found!!!");
        header = aLine.substring(1).trim();
        
        // Read sequence part
       	br.mark(1024); // potential problem: length of one sequence line > 1024 characters  
    	aLine = br.readLine();
    	aLine.trim();
        do {
        	sequence.append(aLine);
        	br.mark(1024);
        	aLine = br.readLine();
        	if (aLine == null) break;
        	aLine.trim();
        } while (!aLine.startsWith(">",0)); // Next header begins 
        if (aLine != null) br.reset(); // We stopped before the next header -> set the focus back
        this.setHeader(header);
        this.setSequence(sequence.toString());
    }
    
    public void write (Writer w) throws IOException
    {
    	w.write(">"+this.getHeader()+"\n");
    	int d;
    	for(int c = 0; c < getSequence().length(); c+=80) {
    		d=Math.min(getSequence().length(),c+80);
            w.write(getSequence().substring(c,d)+"\n");
        };
    }
    
    /**
     * compares the content of two FastAItem objects
     * 
     * @param item the other FastAItem object 
     * @return result of the comparison
     */
    public boolean contentEquals(FastAItem item)
    {
    	if (item == null) return false;
    	if (item == this) return true;
    	
    	return ((this.header.contentEquals(item.header)) && (this.seq.contentEquals(item.seq)));
    }
}
