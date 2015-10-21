package exampleCode.bioinfo.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 *
 * @authors Daniel Huson and Janko Dietzsch
 */

public class FastA {
	private ArrayList<FastAItem> FastAList;

	/**
	 * default constructor
	 */

	public FastA() {
		this.FastAList = new ArrayList<FastAItem>();
	}
	
	/**
     * add a header and sequence
     * @param header
     * @param sequence
     */
    public void add (String header, String sequence) {
    	FastAItem item = new FastAItem(header, sequence);
        this.FastAList.add(item);
    }

    /**
     * clear the data
     */
    public void clear() {
    	this.FastAList.clear();
    }

    /**
     * gets the header
     * @param i the index
     * @return
     */
    public String getHeader(int i) {
        return this.FastAList.get(i).getHeader();
    }

    /**
     * sets the header and sequence
     * @param i the index
     * @param header
     */
    public void set (int i,String header,String sequence) {
            this.FastAList.get(i).setHeader(header).setSequence(sequence);
    }

    /**
     * gets the sequence
     * @param i the index
     * @return           the sequence
     */
    public String getSequence(int i) {
        return this.FastAList.get(i).getSequence();
    }

    /**
     * gets the first header
     * @return first header
     */
    public String getFirstHeader ()
    {
        return this.FastAList.get(0).getHeader();
    }

    /**
     * gets the first sequence
     * @return first sequence
     */
    public String getFirstSequence ()
    {
        return this.FastAList.get(0).getSequence();
    }

    /**
     * sets the size of sequences
     * @param n the size
     */
    public void setSize (int n)
    {
        if (n > FastAList.size()) FastAList.ensureCapacity(n);
    }

    /**
     * get the size of sequences
     * @return size of sequences
     */
    public int getSize ()
    {
        return FastAList.size();
    }

    
    /**
     * read header and sequence in fastA format
     * @param r
     * @throws java.io.IOException
     */
    public void read (Reader r) throws IOException
    {
        this.clear();

        BufferedReader br=new BufferedReader(r);
        FastAItem item = null;
        while (br.ready()) {
        	item = new FastAItem();
        	item.read(br);
        	this.FastAList.add(item);
        }
    }

    /**
     * write header and sequence in fastA format
     * @param w
     * @throws IOException
     */
    public void write (Writer w) throws IOException
    {
    	for (FastAItem item: this.FastAList) {
    		item.write(w);
    	}
    }

    /**
     * compares the content of two FastA objects
     * Two FastA objects are equal if and only if 
     * they contain the same FastAItems in the same 
     * order
     * 
     * @param item the other FastA object 
     * @return result of the comparison
     */
    public boolean contentEquals(FastA obj)
    {
    	if (obj == null) return false;
    	if (obj == this) return true;
    	
    	if (this.getSize() != obj.getSize()) return false;
    	if (this.getSize() == 0) return true;
    	
    	boolean eq = true;
    	
    	for (int i = 0; i < this.getSize(); i++) 
    		if (!this.FastAList.get(i).contentEquals(obj.FastAList.get(i))) {
    			eq = false; 
    			break;
    		};
    	
    	return eq;
    }


	public static void main(String[] args) throws Exception {
	    if((args.length < 1) || (args.length > 2))
            throw new Exception("Usage: FastA infile or FastA infile outfile");

        FastA fasta=new FastA();

        FileReader fr = new FileReader(new File(args[0]));
        
        fasta.read(fr);
        
        fr.close();
        
        if (args.length == 2) {
        	FileWriter fw = new FileWriter(args[1]);
        	fasta.write(fw);
        	fw.close();
    	} else {
			StringWriter sw = new StringWriter();
			fasta.write(sw);
			System.out.print(sw);
			sw.close();
    	};
	}
}
