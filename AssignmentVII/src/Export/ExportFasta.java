package Export;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ExportFasta {
	private ArrayList<String>[] set;
	private String name1;
	private String name2;
	private String motif;
	private int[][] pos;
	
	public ExportFasta(ArrayList<String>[] set, int[][] pos, ArrayList<String> m, String outPath, int l, int d){
	this.set=set;	
	this.name1 = outPath+"_marked";
	this.name2 = outPath;
	this.motif=m.toString();
	this.pos=pos;
	
	File output1=null;
	File output2=null;
	boolean alreadyExists=true;
	int i=1;
	

	while(alreadyExists){
		output1 = new File(name1);
		output2 = new File(name2);
		if(!output1.exists()){

			alreadyExists=false;
		}
		else{
			name1=outPath+"_marked_"+i;
			name2=outPath+i;
			i++;
		}
	}
	name1= name1+".txt";
	name2= name2+".fasta";
	try {
		output1.createNewFile();
		FileWriter fw= new FileWriter(output1.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		
		String fileHeader= ";Here are "+set.length+" random Sequences with a planted Motif \n "
				+ ";Motif Base: "+motif+"\n"
				+ "; Motif was with a length of: "+l+" and possible deviations up to: "+d;
		String seqHeader;
		String sequence;
		
		bw.newLine();
		bw.write(fileHeader);
		bw.newLine();
		
		for(i=0; i<set.length; i++){
			seqHeader= ">Sequence "+i+" Motif was entered from "+pos[i][0]+" to "+pos[i][1];
			sequence= set[i].toString();
			sequence = sequence.substring(1, sequence.length()-1);
			sequence= sequence.replaceAll("\\,", "");
			sequence= sequence.replaceAll("\\s+", "");
			sequence= sequence.trim();

			bw.write(seqHeader);
			bw.newLine();
			bw.write(sequence);
			bw.newLine();
		}
		bw.close();
		
		
		//Real Fasta File!
		output2.createNewFile();
		fw= new FileWriter(output2.getAbsoluteFile());
		bw = new BufferedWriter(fw);
		
		fileHeader= ";Here are "+set.length+" random Sequences with a planted Motif \n "
				+ ";Motif Base: "+motif+"\n"
				+ "; Motif was with a length of: "+l+" and possible deviations up to: "+d;
		
		bw.newLine();
		bw.write(fileHeader);
		bw.newLine();
		
		for(i=0; i<set.length; i++){
			seqHeader= ">Sequence "+i+" Motif was entered from "+pos[i][0]+" to "+pos[i][1];
			sequence= set[i].toString();
			sequence= sequence.replaceAll("\\,", "");
			sequence= sequence.replaceAll("\\s+", "");
			sequence=sequence.replaceAll("\\[", "");
			sequence=sequence.replaceAll("\\]", "");
			sequence= sequence.trim();

			bw.write(seqHeader);
			bw.newLine();
			bw.write(sequence);
			bw.newLine();
		}
		bw.close();

	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	}
}
