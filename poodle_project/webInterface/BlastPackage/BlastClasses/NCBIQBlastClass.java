package BlastClasses;
import static org.biojava.nbio.ws.alignment.qblast.BlastAlignmentParameterEnum.ENTREZ_QUERY;
import java.io.*;
import org.biojava.nbio.core.sequence.io.util.IOUtils;
import org.biojava.nbio.ws.alignment.qblast.*;
import org.biojava.nbio.ws.alignment.qblast.BlastAlignmentParameterEnum;

/**
 * This class performs a BLAST request on NCBI server
 * NEEDS: biojava-core, biojava-ws
 */ 
public class NCBIQBlastClass {

  // Member variables
  private String blastResult = "";

  // Member functions 
  public void runBLASTRequest(String program, String sequence, String db, String query) {

    NCBIQBlastService service = new NCBIQBlastService();
    BlastProgramEnum bProgram = null;

    // set program
    switch (program.toLowerCase()) {
      case "blastn":
        bProgram = BlastProgramEnum.blastn;
        break;
      case "blastp":
        bProgram = BlastProgramEnum.blastp;
        break;
      case "blastx":
        bProgram = BlastProgramEnum.blastx;
        break;
      case "megablast":
        bProgram = BlastProgramEnum.megablast;
        break;
      case "tblastn":
        bProgram = BlastProgramEnum.tblastn;
        break;
      case "tblastx":
        bProgram = BlastProgramEnum.tblastx;
        break;
      default:
        bProgram = BlastProgramEnum.blastn;
        break;
    }

    // set alignment options
    NCBIQBlastAlignmentProperties props = new NCBIQBlastAlignmentProperties();
    props.setBlastProgram(bProgram);
    props.setBlastDatabase(db);
    props.setAlignmentOption(ENTREZ_QUERY, query);
 
    // set output options
    NCBIQBlastOutputProperties outputProps = new NCBIQBlastOutputProperties();
    outputProps.setAlignmentNumber(5);
 
    String rid = null;          // blast request ID
    BufferedReader reader = null;
    try {
      // send blast request and save request id
    	System.out.println("sequence:"+sequence+"\n props"+props);
      rid = service.sendAlignmentRequest(sequence, props);
 
      // wait until results become available. Alternatively, one can do other computations/send other alignment requests
      while (!service.isReady(rid)) {
        System.out.println("Waiting for results. Sleeping for 5 seconds");
        Thread.sleep(5000);
      }
 
      // read results when they are ready
      InputStream in = service.getAlignmentResults(rid, outputProps);
      reader = new BufferedReader(new InputStreamReader(in));
 
      String line;
      while ((line = reader.readLine()) != null) {
        this.blastResult += line + System.getProperty("line.separator");
      }

    } catch (Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    } finally {
      // clean up
      IOUtils.close(reader);
 
      // delete given alignment results from blast server (optional operation)
      service.sendDeleteRequest(rid);
    }
  }

  public String getResult() {
    return this.blastResult;
  }
}
