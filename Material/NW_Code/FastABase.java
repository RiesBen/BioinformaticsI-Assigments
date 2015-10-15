package exampleCode;

/* 
 * Read and write fasta files
 * Bioinformatics 1, WS 15/16
 * Dr. Kay Nieselt and Alexander Seitz
 */

import java.util.Vector;

/**
 * base class for input/output
 * which holds all headers and sequences of a fasta file
 * in two vectors
 */
public class FastABase {
    private int size = 0;
    
    private Vector<String> headers = new Vector<String>();
    private Vector<String> sequences = new Vector<String>();


      /**
     * function to add a header and sequence 
     * @param header
     * @param sequence
     */
    public void add (String header, String sequence) {
        set(getSize(), header, sequence);
    }

    /**
     * clear data (all sequences and headers)
     */
    public void clear() {
        headers.clear();
        sequences.clear();
        size = 0;
    }

    /**
     * gets header with index i 
     * @param i
     * @return header at index i
     */
    public String getHeader(int i) {
        return (String)headers.get(i);
    }

    /**
     * sets header and sequence 
     * with index i       
     * @param i
     * @param header
     * @param sequence
     */
    public void set (int i, String header, String sequence) {
        if(i < getSize()) {
            this.headers.set(i, header);
            this.sequences.set(i, sequence);
        } else {
            setSize(i + 1);
            this.headers.add(i, header);
            this.sequences.add(i, sequence);
        }
    }

    /**
     * gets sequence with index i
     * @param i
     * @return sequence
     */
    public String getSequence(int i) {
        return (String)sequences.get(i);
    }

    /**
     * sets number of sequences to n
     * @param n number of sequences
     */
    public void setSize (int n) {
        if(n > size) {
            headers.setSize(n);
            sequences.setSize(n);
        }
        size = n;
    }

    /**
     * get number of sequences
     * @return number of sequences
     */
    public int getSize () {
        return size;
    }
}
