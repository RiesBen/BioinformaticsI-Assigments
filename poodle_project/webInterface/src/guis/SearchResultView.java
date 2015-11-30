package guis;

import com.poodledb.NavigationBar;
import com.vaadin.annotations.Push;
import com.vaadin.ui.*;

import SearchForm.SearchBar;
import sqlClasses.SQLCommunicator;

@Push
public class SearchResultView extends VerticalLayout{
	GridLayout grid = new GridLayout(1,3);
	SearchBar searchBar = new SearchBar();
	Table primerTable = new Table("Primer");
	
	
	public SearchResultView(SQLCommunicator sqlC){		
		
		primerTable.setContainerDataSource(sqlC.getContainer());
		
		grid.addComponent(searchBar.getLayout(), 0, 0);
		grid.addComponent(primerTable, 0, 1);
		addComponent(grid);
	}
}
