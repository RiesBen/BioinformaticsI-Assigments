/*
 * Benjamin Schroeder
 * Jonas Ditz
 */
package exampleCode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

/**
 * Application of the Needleman-Wunsch Algorithm
 * Bioinformatics 1, WS 15/16
 * Dr. Kay Nieselt and Alexander Seitz
 */
public class RunNeedlemanWunsch {
    /**
     * Run the Needleman-Wunsch Algorithm
     * @param args commandline arguments
     * @throws Exception
     */
    static public void main (String[] args) throws Exception {
        
        CommandLineParser options = new CommandLineParser(args);
        CmdLineParser parser = new CmdLineParser(options);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.exit(1);
        }

        FastA fasta1 = new FastA();
        fasta1.read(new BufferedReader(new FileReader(options.getSeq1())));
        
        FastA fasta2 = new FastA();
        fasta2.read(new BufferedReader(new FileReader(options.getSeq2())));

        // setup algorithm parameters:
        NeedlemanWunsch nw = new NeedlemanWunsch();

        //set sequences
        nw.setSequence1(fasta1.getSequence(0));
        nw.setSequence2(fasta2.getSequence(0));

        //set match, mismatch score and gap penalty
        nw.setMatchScore(options.getMatchScore());
        nw.setMismatchScore(options.getMismatchScore());
        nw.setGapPenalty(options.getOpeningCost());

        // display input
        System.out.println("Input:");
        System.out.println("\t" + "seq1 = " + nw.getSequence1());
        System.out.println("\t" + "seq2 = " + nw.getSequence2());
        System.out.println();

        // run algorithm
        nw.compute();

        if(options.getSingle()){
            if(options.getOutFile() == null) {
                System.out.println("Alignment :");
                System.out.println("\t" + "aligned1 = " + nw.getAligned1(0));
                System.out.println("\t" + "aligned2 = " + nw.getAligned2(0));
                System.out.println("--------------------------------");
                System.out.println("Alignment-Score = "+nw.getScore());
            }else {
                nw.writeAlignment(options.getOutFile(), true);
            }
        }else{
            if(options.getOutFile() == null) {
                // display all possible optimal alignments
                for(int i = 0; i < nw.getNumberOfAlignments(); i++) {
                    System.out.println("Alignment " + (i+1) + ":");
                    System.out.println("\t" + "aligned1 = " + nw.getAligned1(i));
                    System.out.println("\t" + "aligned2 = " + nw.getAligned2(i));
                }
        
                System.out.println("--------------------------------");
                System.out.println("Alignment-Score = "+nw.getScore());
            }else {
                nw.writeAlignment(options.getOutFile(), false);
            }
        }
    }
}
