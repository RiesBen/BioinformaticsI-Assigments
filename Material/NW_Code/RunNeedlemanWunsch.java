/*
 * \TODO add your names here!!!
 */
package exampleCode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

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
        if(args.length!=5)
            throw new Exception("Usage: file1 file2 matchscore mismatchscore gappenalty");
        
        /*
         * \TODO
         * Adapt this class such that it accepts command line switches
         * e.g.
         * -i		for input files
         * -d		for gap penalty
         * -ms		for match score
         * -mms		for mismatch score
         * -single	only output one best alignment
         * -all		output all best alignments
         * -o		write output to a fasta file instead of system.out
         * 			in this case the alingments should be writen in fasta-format +
         * 			store the score of the alignment in the header of each fasta entry
         * -?		provide helpful usage information
         */
        
        FastA fasta1 = new FastA();
        fasta1.read(new BufferedReader(new FileReader(new File(args[0]))));
        
        FastA fasta2 = new FastA();
        fasta2.read(new BufferedReader(new FileReader(new File(args[1]))));

        // setup algorithm parameters:
        NeedlemanWunsch nw = new NeedlemanWunsch();

        //set sequences
        nw.setSequence1(fasta1.getSequence(0));
        nw.setSequence2(fasta2.getSequence(0));

        //set match, mismatch score and gap penalty
        nw.setMatchScore(Integer.parseInt(args[2]));
        nw.setMismatchScore(Integer.parseInt(args[3]));
        nw.setGapPenalty(Integer.parseInt(args[4]));

        // display input
        System.out.println("Input:");
        System.out.println("\t" + "seq1 = " + nw.getSequence1());
        System.out.println("\t" + "seq2 = " + nw.getSequence2());
        System.out.println();

        // run algorithm
        nw.compute();

     // display all possible optimal alignments
        for(int i = 0; i < nw.getNumberOfAlignments(); i++) {
            System.out.println("Alignment " + (i+1) + ":");
            System.out.println("\t" + "aligned1 = " + nw.getAligned1(i));
            System.out.println("\t" + "aligned2 = " + nw.getAligned2(i));
        }
        
        System.out.println("--------------------------------");
        System.out.println("Alignment-Score = "+nw.getScore());
        
        /*
         * \TODO here it is only possible to write the output to system.out
         * your program should also be able to write the ouput in fasta-format to
         * a user defined file! All the output should go in the same fasta file 
         * (multi-fasta file). In this, make sure, that you set the headers of
         * your entries such that:
         * 	- one can easily see which two sequences are aligned to each other
         *  - the score of the alignment should be encoded in the header of both
         *    aligned sequences
         */
        
        /*
         * \TODO make sure your code compiles correctly!
         */
        
        /*
         * \TODO explain in your report, why you think your changes are necessary!
         */
    }
}
