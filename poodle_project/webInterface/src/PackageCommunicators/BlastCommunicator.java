package PackageCommunicators;

import com.vaadin.server.VaadinService;

import BlastClasses.*;

public class BlastCommunicator {
	private	String mode;
	private String program;
	private String sequence;
	private String database;
	private String querry;
	private String resultPrimer=null;
	private String resultVector=null;
	private String resultProtein=null;
	private String blastResult=null;
	
	public BlastCommunicator(String mode, String programe, String sequence, String database, String table, String querry){
		String basepath= VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
		String pPath = "/WEB-INF/classes/ncbi_binaries/";
		String location;

		//initialisation
		this.mode=mode;
		this.program= basepath+pPath;
		this.sequence=sequence;
		this.database = database;
		this.querry = querry;

		if (mode.equals("local")) {
			try{
				//Check OS
				if(System.getProperty("os.name").contains("Windows")){
					program+="/win/bin/"+programe+".exe";
				}
				else if(System.getProperty("os.name").contains("Linux")){
					program+="/linux/bin/"+programe;
				}
				else if(System.getProperty("os.name").contains("Mac")){
					program+="/mac/bin/"+programe;
				}
				else{
					throw new Exception("unkown OS!");
				}

				//Check Table	
				if(table.equals("all")){
					location= basepath+"/WEB-INF/db/"+database+"/primer.fasta";
					BlastSearch localBlast1 = new BlastSearch(sequence, location, program);
					location= basepath+"/WEB-INF/db/"+database+"/clonVectors.fasta";
					BlastSearch localBlast2 = new BlastSearch(sequence, location, program);
					location= basepath+"/WEB-INF/db/"+database+"/protConst.fasta";
					BlastSearch localBlast3 = new BlastSearch(sequence, location, program);

					resultPrimer=localBlast1.getResult();
					resultVector=localBlast2.getResult();
					resultProtein=localBlast3.getResult();
				}
				else if(table.equals("primer")){
					location= basepath+"/WEB-INF/db/"+database+"/primer.fasta";
					
					BlastSearch localBlast = new BlastSearch(sequence, location, program);
					localBlast.runBlastSearch();
					System.out.println("PrimerLäuft");
					resultPrimer=localBlast.getResult();
					System.out.println(resultPrimer);
				}
				else if(table.equals("cloningVector")){
					location= basepath+"/WEB-INF/db/"+database+"/clonVector.fasta";
					
					BlastSearch localBlast = new BlastSearch(sequence, location, program);
					localBlast.runBlastSearch();
					System.out.println("PrimerLäuft");
					resultVector=localBlast.getResult();
				}
				else if(table.equals("proteinConstructs")){
					location= basepath+"/WEB-INF/db/"+database+"/protConst.fasta";
					
					BlastSearch localBlast = new BlastSearch(sequence, location, program);
					localBlast.runBlastSearch();
					System.out.println("PrimerLäuft");
					resultProtein=localBlast.getResult();
				}
			}

			catch (Exception e){
				System.out.println("There was a problem with your OS"+e);
			}
		}
		else if(mode.equals("internet")) {
			// perform NCBI search
			NCBIQBlastClass ncbiBlast = new NCBIQBlastClass();
			//ncbiBlast.runBLASTRequest(program, sequence, database, querry);
			ncbiBlast.runBLASTRequest(this.program, this.sequence, this.database, this.querry);
			blastResult = ncbiBlast.getResult();
			System.out.println(blastResult);
		}

		System.out.println(blastResult);
	}
	
	public String[] getResult(){
		String[] results = new String[4];
		results[0]=resultPrimer;
		results[1]=resultVector;
		results[2]=resultProtein;	
		results[3]=blastResult;
		return results;
	}
}
