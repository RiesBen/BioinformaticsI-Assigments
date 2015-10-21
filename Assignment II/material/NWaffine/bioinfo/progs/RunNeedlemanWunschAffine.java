package exampleCode.bioinfo.progs;

import java.io.File;
import java.io.FileReader;

import exampleCode.bioinfo.align.NeedlemanWunschAffine;
import exampleCode.bioinfo.io.FastA;


/** 
 * report a global alignment using the Needleman Wunsch algorithm  and affine gap penalties
 */
public class RunNeedlemanWunschAffine  {
    /**
     * runs the Needleman Wunsch algorithm.
     * @throws Exception
     */
    static public void main (String[] args) throws Exception {
        long startTime=System.currentTimeMillis();

        //TODO add command line switches and help page
        
        //TODO extend the command lines options to be able to load scoring matrices, too
        //to do so, check the class ScoringMatrix and make yourself familiar with the needed file format 
        
        //TODO add a switch to allow the user to write the output to a multi-fasta file
        //instead of system.out (the score should then be part of the fasta headers)
        
        if(args.length!=6)
            throw new Exception("Usage: file1 file2 matchscore mismatchscore gapopen gapextend");
        
        FastA fasta1=new FastA();
        fasta1.read(new FileReader(new File(args[0])));
        FastA fasta2=new FastA();
        fasta2.read(new FileReader(new File(args[1])));

        // setup algorithm parameters:
        NeedlemanWunschAffine nw=new NeedlemanWunschAffine();
        nw.setSequence1(fasta1.getFirstSequence());
        nw.setSequence2(fasta2.getFirstSequence());
        nw.setMatchScore(Integer.parseInt(args[2]));
        nw.setMismatchScore(Integer.parseInt(args[3]));
        nw.setGapOpenPenalty(Integer.parseInt(args[4]));
        nw.setGapExtensionPenalty(Integer.parseInt(args[5]));

        // Display input:
        System.out.println("Input:");
        System.out.println("seq1="+nw.getSequence1());
        System.out.println("seq2="+nw.getSequence2());

        // run algorithm:
        nw.compute();

        // Display alignment:
        System.out.println("Alignment:");
        System.out.println("seq1="+nw.getAligned1());
        System.out.println("seq2="+nw.getAligned2());

        System.out.println("Score="+nw.getScore());

        long usedTime=(System.currentTimeMillis()-startTime)/1000;
        System.err.println("Time: "+usedTime+" sec");
        long totalMem = Runtime.getRuntime().totalMemory()/(long)1024;
        long usedMem = totalMem - Runtime.getRuntime().freeMemory()/(long)1024;
        System.err.println("Memory: "+usedMem+"k");
    }
}
