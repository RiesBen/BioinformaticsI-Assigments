package BlastClasses;
import java.io.*;
import java.util.*;

import com.vaadin.server.VaadinService;

/**
 * Wrapper class to perform a BLAST search locally
 */
public class BlastSearch {

  // MEMBER VARIABLES
  private String blastDB = null;
  private String blastProgram = null;
  private String task = null;
  private String query = null;
  private long timestemp = 0;
  private String blastResult = "";

  // CONSTRUCTOR
  public BlastSearch(String query, String database, String program) {
    this.blastDB = database;
    this.blastProgram = program;
    this.query = query;

    java.util.Date date = new java.util.Date();
    this.timestemp = date.getTime();
    String[] auxTask = program.split("/");
    try {
      this.task = auxTask[auxTask.length -1];
      auxTask=this.task.split("\\.");
      this.task=auxTask[0];
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  // MEMBER FUNCTIONS

  // Setter functions
  public void setBlastDB(String database) {
    this.blastDB = database;
  }

  public void setBlastProgram(String program) {
    this.blastProgram = program;
  }

  public void setQuery(String query) {
    this.query = query;
  }

  // Getter functions
  public String getBlastDB() {
    return this.blastDB;
  }

  public String getBlastProgram() {
    return this.blastProgram;
  }

  public String getQuery() {
    return this.query;
  }

  public String getResult() {
    return this.blastResult;
  }

  // other functions
  private void createQueryFile(String queryFile) {
    Writer writer = null;

    try {
      writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(queryFile), "utf-8"));
      writer.write(">query1" + System.getProperty("line.separator"));
      writer.write(this.query + System.getProperty("line.separator"));
      System.out.println(queryFile + " was successfully created");
    }catch (IOException ex) {
      System.out.println(ex.toString());
      ex.printStackTrace();
    }finally {
     try {writer.close();} catch (Exception ex) {/*ignore*/}
    }
  }

  private void deleteQueryFile(String queryFile) {
    try {
      File file = new File(queryFile);
  
      if(file.delete()) {
        System.out.println(queryFile + " was successfully deleted");
      }else {
        System.out.println("Was not able to delete " + queryFile);
      }
    }catch(Exception e) {
      System.out.println(e.toString());
      e.printStackTrace();
    }
  }

  public void runBlastSearch() {
    // create query file
	String basepath= VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
	String qfPath = "/WEB-INF/tmp/";
    String queryFile =basepath+qfPath+"query" + String.valueOf(this.timestemp) + ".fasta";   
    createQueryFile(queryFile);

    try {
      // set up Runtime
      Runtime rt = Runtime.getRuntime();
      String execString = this.blastProgram + " -query " + queryFile + " -db " + this.blastDB + " -task " + this.task + " -html";
      System.out.println("Running " + execString + " ...");
      Process pr = rt.exec(execString);
      
      BufferedReader error = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
      BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));

      String line = null;
      while((line = error.readLine()) != null) {
        System.out.println(line);
      }
 
      line = null;
      while((line = input.readLine()) != null) {
        this.blastResult += line + System.getProperty("line.separator");
        //System.out.println(line);
      }

      int exitVal = pr.waitFor();

    }catch(Exception e) {
      System.out.println(e.toString());
      e.printStackTrace();
    }

    // delete query file
    deleteQueryFile(queryFile);
  }
}
