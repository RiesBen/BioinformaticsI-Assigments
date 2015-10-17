package exampleCode;

import java.io.File;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

/**
 * Class for parsing command line switches
 * Jonas Ditz and Benjamin Schroeder
 */

public class CommandLineParser {

    @Option(name="-i1", usage="input file, which stores the first sequence", required=true)
    private File inSeq1;

    @Option(name="-i2", usage="input file, which stores the second sequence", required=true)
    private File inSeq2;

    @Option(name="-o", usage="output to this file")
    private String out;

    @Option(name="-open", usage="set gap open penalty\nIf linear gap costs are used, this is the gap penalty")
    private int gapOpenPenalty = 1;

    @Option(name="-epen", usage="set gap extention penalty")
    private int gapExtPenalty = 1;

    @Option(name="-m", usage="set the score of a match")
    private int match = 1;

    @Option(name"-mm", usage="set the score of a mismatch")
    private int mismatch = 1;

    @Option(name="-single", usage="output only the best alignment, i.e. with highest alignment score\nIf you want all alignments, set this option to FALSE")
    private boolean single = true;

   
    // main
    public static void main(String[] args) throws IOException {
        new CommandLineParser().doMain(args);
    }

    public void doMain(String[] args) throws IOEception {
        CmdLineParser parser = new CmdLineParser(this);

        // set the width of your consol
        parser.setUsageWidth(100);

        
        try {
            // parse the arguments.
            parser.parseArgument(args);

            //check whether there are enough arguments
            if( arguments.isEmpty() )
                throw new CmdLineException(parser,"No argument is given");

        } catch( CmdLineException e ) {
            // if there's a problem in the command line,
            // you'll get this exception. this will report
            // an error message.
            System.err.println(e.getMessage());
            System.err.println("java SampleMain [options...] arguments...");
            // print the list of available options
            parser.printUsage(System.err);
            System.err.println();

            // print option sample. This is useful some time
            System.err.println("  Example: java SampleMain"+parser.printExample(ALL));

            return;
        }
    }

    /**
     * get inSeq1
     * @return inSeq1
     */
    public File getSeq1() {
        return inSeq1;
    }

    /**
     * get inSeq2
     * @return inSeq2
     */
    public File getSeq2() {
        return inSeq2;
    }

    /**
     * get output file
     * @return out
     */
    public File getOutFile() {
        return out;
    }

    /**
     * get gap open penalty
     * this is the gap penalty, if the user chose linear gap costs
     * @return gapOpenPenalty
     */
    public int getOpeningCost() {
        return gapOpenPenalty;
    }

    /**
     * get gap extension penalty
     * @return gapExtPenalty
     */
    public int getExtensionCost() {
        return gapExtPenalty;
    }

    /**
     * get match score
     * @return match
     */
    public int getMatchScore() {
        return match;
    }

     /**
     * get mismatch score
     * @return mismatch
     */
    public int getMismatchScore() {
        return mismatch;
    }

    /**
     * get single
     * @return single
     */
    public boolean getSingle() {
        return single;
    }


}
