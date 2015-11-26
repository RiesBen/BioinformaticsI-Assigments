package UI;

import java.io.File;
import java.io.IOException;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

public class CmdLineParse {

/**
 * Class for parsing command line switches
 * Jonas Ditz and Benjamin Schroeder
 */

	 @Option(name="-k", usage="Number of Sequences in a Set")
    private int inK=-1;

    @Option(name="-n", usage="length of each sequence")
    private int inN=-1;

    @Option(name="-l", usage="Motif-length")
    private int inL;

    @Option(name="-d", usage="deviations of the Motif")
    private int inD=0;

    @Option(name="-out", usage="name of the OutFile")
    private String inOut=null;

    @Option(name="-M", usage="Motif")
    private String inM;

    @Option(name="-E", usage="Define Alphabet")
    private char inE = ' ';
  
    
    /*
     * Constructor
     */
    public CmdLineParse(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        parser.setUsageWidth(100);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            parser.printUsage(System.err);
            }
    } 

    /**
     * get k
     * @return inK
     */
    public int getk() {
        return inK;
    }

    /**
     * get n
     * @return inN
     */
    public int getN() {
        return inN;
    }

    /**
     * get l
     * @return inD
     */
    public int getL() {
        return inL;
    }

    /**
     * get Deviation in Motif
     * @return inD
     */
    public int getD() {
        return inD;
    }

    /**
     * get Out
     * @return inOUt
     */
    public String getOut() {
        return inOut;
    }

    /**
     * get M
     * @return Motif
     */
    public String getM() {
        return inM;
    }

     /**
     * get AlphabetSelect
     * @return alphabet
     */
    public char getE() {
        return inE;
    }
}
