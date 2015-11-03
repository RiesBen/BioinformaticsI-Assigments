package Run;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

import Analysis.Analysis;
import Parser.parserFasta;
import Parser.parserPSL;

public class Main {
	public static void main(String[] args) {
		String pathPsl = "C:/Users/Benjamin/Documents/GitHub/BioinformaticsI-Assigments/Assignment III/Material/output.psl";
		String pathFasta = "C:/Users/Benjamin/Documents/GitHub/BioinformaticsI-Assigments/Assignment III/Material/reads.fasta";
		
		System.out.println("read in the .psl-File");
		parserPSL blatOutput= new parserPSL(pathPsl); // read in the output file.
		System.out.println("read in the .psl-File - Finished");
		System.out.println("read in the .fasta-File");
		parserFasta fastaReads = new parserFasta(pathFasta);
		System.out.println("read in the .fasta-File - Finished");
		
		String[][] pslFile=blatOutput.getSequences();
		String[][] fastaFile=fastaReads.getsequences();
		
		
		System.out.println("Start Analysis:\n Here is the length:"+fastaFile.length);
		Analysis analysis = new Analysis(pslFile, fastaFile);
//		System.out.println(" How many Hits did blat find?:");
//		int[] hits= analysis.checkAmmountOfHits();
//		float percentageHits= hits[0]/fastaFile.length;
//		float percentageuniqueHits= hits[1]/fastaFile.length;
//		System.out.println("The absolute number: "+hits[0]+" Which is a percentage over the total: "+percentageHits);
//		System.out.println("The unique number: "+hits[1]+" Which is a percentage over the total: "+percentageuniqueHits);
		int mapped = analysis.mapped();
		System.out.println("Were not mapped: "+mapped+" Which is a percentage ove: ");

	}
}

