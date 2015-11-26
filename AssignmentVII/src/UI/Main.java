package UI;

import java.io.BufferedWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.FileHandler;

import Alphabets.*;
import Export.ExportFasta;
import SeqGenerators.*;

public class Main {


	
	public static void main(String[] args){
		int k; // ammount of sequences in the set
		int n; // lenght of each generated sequence
		int l; //motif length
		int d; //
		String outPath;	//for o utputFile
		BasicAlphabets E;
		ArrayList<String> M; //Motif
		
		int[][] pos;
		ArrayList<String>[] set;
		
		//benötigte Klassen
		SetGenerator setGen; //Generates the Sequence set
		ExportFasta export; // Class to Export things
		
		
		
		//Start Programm
		System.out.println("Welcome to MotifSeqGen!\n"
				+ "Please enter your parameters or run a test: \n"
				+ "1. Enter Parameters \n"
				+ "2. Test - (default Parameters) \n"
				+ "3. Exit \n");
		//INPUT Control:
		if (true){
			k=5;
			n=20;		
			l=5;
			d=1;
			E= new BasicDNA();	
			SequenceGenerator MGenerator = new SequenceGenerator(l, E);
			MGenerator.generateSeq();
			M= MGenerator.getSequence();
			outPath="./test";
			
			
			System.out.println("\n The Test was chosen wit Parameters:\n"
					+ "k=5 \t n=20 \t l=5 \t d=1\n"
					+ "E= BasicDNA \t M="+M.toString());
		}
	
		else{
			k = Integer.parseInt(args[0]);
			n = Integer.parseInt(args[1]);
			l = Integer.parseInt(args[2]);
			d = Integer.parseInt(args[3]);
			E = new BasicDNA(); // SOLVE THIS MORE VARIABLE
		}
		
		
		//Main work:
		// 1. generate Sequence set:
		setGen= new SetGenerator(k, l, n, M, d, E);
		set = setGen.getSequenceSet();
		pos = setGen.getPositionsOfMotifs();
		// 2. generate Output
		export = new ExportFasta(set, pos, M, outPath, l, d);
		

		
		System.out.println("Programm Finished");
			
	}
}
