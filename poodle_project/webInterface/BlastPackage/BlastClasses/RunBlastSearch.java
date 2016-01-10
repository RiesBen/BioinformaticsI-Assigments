package BlastClasses;
import java.io.*;
import java.util.*;

public class RunBlastSearch {

  static public void main(String[] args) throws Exception {
    String mode = args[0];
    String program = args[1];
    String sequence = args[2];
    String database = args[3];
    String query = args[4];
    String blastResult = null;

    if (mode.equals("local")) {
      BlastSearch localBlast = new BlastSearch(sequence, database, program);
      localBlast.runBlastSearch();
      blastResult = localBlast.getResult();
    }else if(mode.equals("NCBI")) {
      // perform NCBI search
      NCBIQBlastClass ncbiBlast = new NCBIQBlastClass();
      //ncbiBlast.runBLASTRequest(program, sequence, database, query);
      ncbiBlast.runBLASTRequest("blastp", "MKWVTFISLLFLFSSAYSRGVFRRDAHKSEVAHRFKDLGEENFKALVLIAFAQYLQQCP", "swissprot", "\"serum albumin\"[Protein name] AND mammals[Organism]");
      blastResult = ncbiBlast.getResult();
    }
    
    System.out.println(blastResult);
  }
}
