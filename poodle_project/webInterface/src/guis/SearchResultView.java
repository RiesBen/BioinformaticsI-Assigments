package guis;

import com.poodledb.NavigationBar;
import com.vaadin.annotations.Push;
import com.vaadin.ui.*;

import SearchForm.SearchBar;
import sqlClasses.SQLCommunicator;

@Push
public class SearchResultView extends VerticalLayout{
	GridLayout grid = new GridLayout(1,4);
	SearchBar searchBar = new SearchBar();
	Table primerTable = new Table("Primer");
	Table vectorTable = new Table("Vector");
	Table proteinTable = new Table("Protein");

	public SearchResultView(SQLCommunicator sqlC){		

		switch(sqlC.getTable()){
		case "Primer":
			primerTable.setContainerDataSource(sqlC.getContainer());
			grid.addComponent(primerTable, 0, 1);
			break;
		case "cloningVectors":
			vectorTable.setContainerDataSource(sqlC.getContainer());
			grid.addComponent(vectorTable, 0, 1);
			break;
		case "proteinConstructs":
			proteinTable.setContainerDataSource(sqlC.getContainer());
			grid.addComponent(proteinTable, 0, 1);
			break;
		case "all":
			primerTable.setContainerDataSource(sqlC.getPrimerQuerry());
			vectorTable.setContainerDataSource(sqlC.getVectorQuerry());
			proteinTable.setContainerDataSource(sqlC.getProteinQuerry());

			grid.addComponent(primerTable, 0, 1);
			grid.addComponent(vectorTable, 0, 2);
			grid.addComponent(proteinTable, 0, 3);
			break;
		}

		grid.addComponent(searchBar.getLayout(), 0, 0);
		addComponent(grid);
	}
}
