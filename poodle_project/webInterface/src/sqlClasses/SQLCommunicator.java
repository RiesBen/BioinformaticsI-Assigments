package sqlClasses;

import java.sql.SQLException;

import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.*;
import com.vaadin.data.util.sqlcontainer.query.TableQuery;
import org.sqlite.*;


public class SQLCommunicator {

	JDBCConnectionPool pool;
	String db;
	TableQuery tq;
	String [][] searchCriteria;
	SQLContainer container;
	
	public SQLCommunicator(String db){
		this.db = db;	
		try {
			pool = new SimpleJDBCConnectionPool("org.sqlite.JDBC", "jdbc:sqlite:/home/benjamin_schroeder/BioinformaticsI-Assigments/poodle_project/src/dbUtils/poodle_sqlite3.dat", "", "", 2, 5);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	}

	public void setTableQuerry(String a){
		tq= new TableQuery(a, pool);
		tq.setVersionColumn("OPTLOCK");
		try {
			container = new SQLContainer(tq);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public SQLContainer getContainer(){
		return container;
	}
}
