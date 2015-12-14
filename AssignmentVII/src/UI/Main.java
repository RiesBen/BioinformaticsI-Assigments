package UI;

import java.io.BufferedWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.FileHandler;

import Alphabets.*;
import Export.ExportFasta;
import SeqGenerators.*;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;


public class Main {
	    
	public static void main(String[] args){
		int k; // ammount of sequences in the set
		int n; // lenght of each generated sequence
		int l; //motif length
		int d; //
		String outPath;	//for o utputFile
		BasicAlphabets E =null;
		ArrayList<String> M =null; //Motif
		
		int[][] pos;
		ArrayList<String>[] set;
		
		//benötigte Klassen
		SetGenerator setGen; //Generates the Sequence set
		ExportFasta export; // Class to Export things
		
		
		
		//Start Programm
		// is there a help request?
		if(containsHelp(args)){
	    	System.out.println("Welcome to MotifSeqGen!\n\n"
	    			+ "This little programm, generates k random sequences of length n, which all contain a Motif of length l (can also be 0). "
	    			+ "The motif can deviates by d positions in each sequences.\n "
	    			+ "All the generated Sequences are saved in a .txt file with name o, where the motif is marked in the sequences and in a .fasta-file with name o which can be used for further studies.\n"
	    			+ "The Sequences can be generated at the moment out of two Alphabets: n is for BasicDNA (A,C,T,G) and p is for basicProtein(all basic 20 AA).\n"
	    			+ "\n Authors: Jonas Ditz and Benjamin Schroeder \n\n"
					+ "Options: \n"
					+ "1. Enter Parameters (here n, k, d and (l or M) are requiered!)\n"
					+ "2. no Parameters: run Test\n\n"
					+ "Use options or run test:\n"
					+ "\t-n \t Enter the Sequence lenght of each sequence in the set\n"
					+ "\t-k \tenter Ammount of Sequences in the set\n"
					+ "\t-l \tenter the Motif length\n"
					+ "\t-d \tEnter ammount of deviating positions\n"
					+ "\t-o \tName the output-File\n"
					+ "\t-M \tEnter here a Motif (-l is then ignored. No whitespaces!)\n"
					+ "\t-E \tEnter an Alphabet: n for basic DNA; p for basic Protein\n"
					+ "\t-h \tsee this nice help message!\n");
		
		}
		else if(unknownOption(args)){
			System.out.println("Error: there is a unknown option or a Argument Missing. Try the option -h to get help.");
		}
		else{
			CmdLineParse parser = new CmdLineParse(args);
			
			//INPUT Control:
			if(args.length == 0){
				k=5;
				n=20;		
				l=5;
				d=1;
				E= new BasicDNA();	
				SequenceGenerator MGenerator = new SequenceGenerator(l, E);
				MGenerator.generateSeq();
				M= MGenerator.getSequence();
				outPath="./test";
				System.out.println("Welcome to MotifSeqGen!\n"
						+ "Please enter your parameters or run a test: \n"
						+ "Running the test! \n"
						+ "Try the -h help option ;) \n");
				
				System.out.println("\n The Test was chosen wit Parameters:\n"
						+ "k=5 \t n=20 \t l=5 \t d=1\n"
						+ "E= BasicDNA \t M="+M.toString());
			}
			else{
				System.out.println("Welcome to MotifSeqGen!\n");
				k = parser.getk();
				n = parser.getN();
				l = parser.getL();
				d = parser.getD();
				outPath=parser.getOut();
				
				try{	
					// k is necessary
					if(k<=0){
						throw new Exception ("please enter a positive Value for the ammount of Sequences");
					}
					// n is necessary
					if(n<=0){
						throw new Exception ("please enter a positive Value for the lenght of the Sequences");
					}
					// d should be at least 0 otherwise non-sense
					if(d<=0 ){
						throw new Exception ("please enter a positive Value for the deviations in the Motif");
					}
					// If there's no name entered!
					if(outPath==null){
						outPath= "untitled";
					}
					
					//Handle E: E is the alphabet. a Default is the basic DNA alphabet. but if a unknown letter is entered, throw error.
					if(parser.getE() == 'n' || parser.getE() == ' '){
						E = new BasicDNA(); // SOLVE THIS MORE VARIABLE
					}
					else if(parser.getE() == 'p'){
						E= new BasicProtein();
					}
					else{
						throw new Exception("Could not find Alphabet");
					}
					
					//Handle M: either M is entered or a length for the motif (at leas 0). otherwise wrong entry.
					if(parser.getM()!=null){
						M= new ArrayList<String>();
						String tmp = parser.getM();
						for(int i=0; i<tmp.length(); i++){
							char tmp2 = tmp.charAt(i);
							M.add(""+tmp2);
						}
					}
					else if(parser.getM() == null && parser.getL() >=0){
						SequenceGenerator MGenerator = new SequenceGenerator(l, E);
						MGenerator.generateSeq();
						M= MGenerator.getSequence();
					}
					else{
						throw new Exception("Please enter M or l (bigger or equal to 0).");
					}
					if(d > M.size()){
						throw new Exception(" There can not be more positions deviate, than the motif is long!");
					}
				}
				catch(Exception e){
					System.out.println("Parsing Failed:" +e);
				}
				
				System.out.println("\n The Test was chosen with Parameters:\n" 		
						+ "k="+k+" \t n="+n+" \t l="+l+" \t d="+d+"\n"
						+ "E="+parser.getE()+" \t M="+M.toString());
			
			
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
	}

	public static boolean containsHelp(String[] args){
		for(int i=0; i<args.length; i++){
			if( args[i].equals("-h") || args[i].equals("-help") || args[i].equals("-?") || args[i].equals("--help")){
				return true;
			}
		}
		return false;
	}	
	public static boolean unknownOption(String[]args){
		if(args.length%2!=0){
			return true;
		}
		for(int i=0; i<args.length; i=i+2){
			if(!args[i].equals("-n") && !args[i].equals("-l") && !args[i].equals("-k") && !args[i].equals("-d") && !args[i].equals("-o") && !args[i].equals("-M") && !args[i].equals("-E")){
				return true;
			}
		}
		return false;	
	}
}