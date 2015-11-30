package sqlClasses;

import java.sql.SQLException;

import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.*;
import com.vaadin.data.util.sqlcontainer.query.TableQuery;
import com.vaadin.server.VaadinService;

import org.sqlite.*;


public class SQLCommunicator {

	JDBCConnectionPool pool;
	String db;
	String table;
	TableQuery tq;
	String [][] searchCriteria;
	SQLContainer container;
	SQLContainer primerContainer;
	SQLContainer vectorContainer;
	SQLContainer proteinContainer;
	


	public SQLCommunicator(String db){
		this.db = db;
		String basepath= VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
		String dbPath = "/WEB-INF/db/poodle_sqlite3.dat";
		try {
			pool = new SimpleJDBCConnectionPool("org.sqlite.JDBC", "jdbc:sqlite:"+basepath+dbPath, "", "", 2, 5);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public void setTableQuerry(String a){
		table = a;
		switch(a){
		case "all":
			TableQuery tq1= new TableQuery("Primer", pool);
			tq1.setVersionColumn("OPTLOCK");
			try {
				primerContainer = new SQLContainer(tq1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			TableQuery tq2= new TableQuery("cloningVectors", pool);
			tq2.setVersionColumn("OPTLOCK");
			try {
				vectorContainer = new SQLContainer(tq2);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			TableQuery tq3= new TableQuery("proteinConstructs", pool);
			tq3.setVersionColumn("OPTLOCK");
			try {
				proteinContainer = new SQLContainer(tq3);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		default:
			tq= new TableQuery(a, pool);
			tq.setVersionColumn("OPTLOCK");
			try {
				container = new SQLContainer(tq);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		}
	}

	public String getTable(){
		return table;
	}
	public SQLContainer getProteinQuerry(){
		return proteinContainer;
	}
	public SQLContainer getPrimerQuerry(){
		return primerContainer;
	}
	public SQLContainer getVectorQuerry(){
		return vectorContainer;
	}

	public SQLContainer getContainer(){
		return container;
	}
}
