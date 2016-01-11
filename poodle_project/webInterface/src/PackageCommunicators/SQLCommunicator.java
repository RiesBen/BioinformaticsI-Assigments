package PackageCommunicators;

import java.sql.SQLException;

import com.vaadin.data.Container.*;
import com.vaadin.data.util.filter.Compare.Equal;
import com.vaadin.data.util.filter.Like;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.*;
import com.vaadin.data.util.sqlcontainer.query.OrderBy;
import com.vaadin.data.util.sqlcontainer.query.TableQuery;
import com.vaadin.server.VaadinService;

import Forms.GeneralSearchParameter;
import Forms.PrimerParameter;
import Forms.ProteinConstructParameter;
import Forms.VectorParameter;

//import org.sqlite.*;


public class SQLCommunicator {

	private JDBCConnectionPool pool;
	private String db;
	private String table;
	private TableQuery tq;
	private SQLContainer container;
	private SQLContainer primerContainer = null;
	private SQLContainer vectorContainer = null;
	private SQLContainer proteinContainer = null;



	public SQLCommunicator(String db){
		this.setDb(db);
		String basepath= VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
		String dbPath = "/WEB-INF/db/Wiesner/poodle_sqlite3.dat";
		try {
			pool = new SimpleJDBCConnectionPool("org.sqlite.JDBC", "jdbc:sqlite:"+basepath+dbPath, "", "", 2, 5);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setTableQuerry(String a){
		table = a;
		TableQuery tq1;
		TableQuery tq2;
		TableQuery tq3;

		switch(a){
		case "all":
			tq1= new TableQuery("Primer", pool);
			tq1.setVersionColumn("OPTLOCK");
			try {
				primerContainer = new SQLContainer(tq1);
				primerContainer.addOrderBy(new OrderBy("id",true));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			tq2= new TableQuery("cloningVectors", pool);
			tq2.setVersionColumn("OPTLOCK");
			try {
				vectorContainer = new SQLContainer(tq2);
				vectorContainer.addOrderBy(new OrderBy("id",true));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			tq3= new TableQuery("proteinConstructs", pool);
			tq3.setVersionColumn("OPTLOCK");
			try {
				proteinContainer = new SQLContainer(tq3);
				proteinContainer.addOrderBy(new OrderBy("id",true));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "primer":
			tq1= new TableQuery("Primer", pool);
			tq1.setVersionColumn("OPTLOCK");
			try {
				primerContainer = new SQLContainer(tq1);
				primerContainer.addOrderBy(new OrderBy("id",true));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "cloningVectors":
			tq2= new TableQuery("cloningVectors", pool);
			tq2.setVersionColumn("OPTLOCK");
			try {
				vectorContainer = new SQLContainer(tq2);
				vectorContainer.addOrderBy(new OrderBy("id",true));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "proteinConstructs":
			tq3= new TableQuery("proteinConstructs", pool);
			tq3.setVersionColumn("OPTLOCK");
			try {
				proteinContainer = new SQLContainer(tq3);
				proteinContainer.addOrderBy(new OrderBy("id",true));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			System.out.println("Error in Server selection!");
		}
	}


	public void setGeneralFilter(GeneralSearchParameter gP){
		String[][] searchCriteria  = gP.getParamteters(); 
		for(int i=0; i<searchCriteria.length; i++){
			if(!searchCriteria[i][1].trim().equals("") && !searchCriteria[i][1].equals(" ") && !(searchCriteria[i][1] == null)){
				System.out.println(searchCriteria[i][0]+"   ->"+searchCriteria[i][1]+"<-");
				Filter querry = new Equal(searchCriteria[i][0],searchCriteria[i][1]);
				primerContainer.addContainerFilter(querry);
				vectorContainer.addContainerFilter(querry);
				proteinContainer.addContainerFilter(querry);
			}
		}
	}

	public void setPrimerFilter(GeneralSearchParameter gP, PrimerParameter pP){
		String[][] searchCriteria  = gP.getParamteters(); 
		for(int i=0; i<searchCriteria.length; i++){
			if(!searchCriteria[i][1].trim().equals("") && !searchCriteria[i][1].equals(" ") && !(searchCriteria[i][1] == null)){
				System.out.println(searchCriteria[i][0]+"   ->"+searchCriteria[i][1]+"<-");
				Filter querry = new Like(searchCriteria[i][0],searchCriteria[i][1]);
				primerContainer.addContainerFilter(querry);
			}
		}
			searchCriteria  = pP.getParamteters(); 
			for(int i=0; i<searchCriteria.length; i++){
				if(!searchCriteria[i][1].equals("") && !searchCriteria[i][1].equals(" ") && !(searchCriteria[i][1] == null)){
					System.out.println(searchCriteria[i][0]+"   ->"+searchCriteria[i][1]+"<-");
					Filter querry = new Like(searchCriteria[i][0],searchCriteria[i][1]);
					primerContainer.addContainerFilter(querry);
				}
			}
		}
		public void setVectorFilter(GeneralSearchParameter gP, VectorParameter vP){
			String[][] searchCriteria  = gP.getParamteters(); 
			for(int i=0; i<searchCriteria.length; i++){
				if(!searchCriteria[i][1].trim().equals("") && !searchCriteria[i][1].equals(" ") && !(searchCriteria[i][1] == null)){
					System.out.println(searchCriteria[i][0]+"   ->"+searchCriteria[i][1]+"<-");
					Filter querry = new Like(searchCriteria[i][0],searchCriteria[i][1]);
					vectorContainer.addContainerFilter(querry);
				}
			}
			searchCriteria  = vP.getParamteters(); 
			for(int i=0; i<searchCriteria.length; i++){
				if(!searchCriteria[i][1].equals("") && !searchCriteria[i][1].equals(" ") && !(searchCriteria[i][1] == null)){
					System.out.println(searchCriteria[i][0]+"   "+searchCriteria[i][1]);
					Filter querry = new Like(searchCriteria[i][0],searchCriteria[i][1]);
					vectorContainer.addContainerFilter(querry);
				}
			}
		}
		public void setProteinFilter(GeneralSearchParameter gP, ProteinConstructParameter pcP){
			String[][] searchCriteria  = gP.getParamteters(); 
			for(int i=0; i<searchCriteria.length; i++){
				if(!searchCriteria[i][1].trim().equals("") && !searchCriteria[i][1].equals(" ") && !(searchCriteria[i][1] == null)){
					System.out.println(searchCriteria[i][0]+"   ->"+searchCriteria[i][1]+"<-");
					Filter querry = new Like(searchCriteria[i][0],searchCriteria[i][1]);
					proteinContainer.addContainerFilter(querry);
				}
			}
			searchCriteria  = pcP.getParamteters(); 
			for(int i=0; i<searchCriteria.length; i++){
				if(!searchCriteria[i][1].equals("") && !searchCriteria[i][1].equals(" ") && !(searchCriteria[i][1] == null)){
					System.out.println(searchCriteria[i][0]+"   "+searchCriteria[i][1]);
					Filter querry = new Equal(searchCriteria[i][0],searchCriteria[i][1]);
					proteinContainer.addContainerFilter(querry);
				}
			}
		}


		public boolean hasProteinContainer(){
			if(proteinContainer == null){
				return true;
			}
			else{
				return false;
			}
		}
		public boolean hasPrimerContainer(){
			if(primerContainer == null){
				return true;
			}
			else{
				return false;
			}
		}
		public boolean hasVectorContainer(){
			if(vectorContainer == null){
				return true;
			}
			else{
				return false;
			}
		}
		public String getTable(){
			return table;
		}
		public SQLContainer getProteinContainer(){
			return proteinContainer;
		}
		public SQLContainer getPrimerContainer(){
			return primerContainer;
		}
		public SQLContainer getVectorContainer(){
			return vectorContainer;
		}

		public SQLContainer getContainer(){
			return container;
		}

		public String getDb() {
			return db;
		}

		public void setDb(String db) {
			this.db = db;
		}
	}
