package contentViews;

import com.poodledb.PoodledbUI;
import com.vaadin.annotations.Push;
import com.vaadin.data.Container.Filter;
import com.vaadin.ui.*;

import SearchForm.GeneralSearchParameter;
import SearchForm.PrimerParameter;
import SearchForm.ProteinConstructParameter;
import SearchForm.VectorParameter;
import pageElements.NavigationBar;
import pageElements.SearchBar;
import specificValues.WiesnerTables;
import sqlClasses.SQLCommunicator;

@Push
public class SearchResultView extends VerticalLayout{
	private GridLayout grid = new GridLayout(1,4);
	private SearchBar searchBar;
	private Table primerTable;
	private Table vectorTable;
	private Table proteinTable;
	
	public SearchResultView(SQLCommunicator sqlC, GeneralSearchParameter gP, PrimerParameter pP, VectorParameter vP, ProteinConstructParameter pcP){
		//Table selection, and searchbar init
		switch(sqlC.getTable()){
		case WiesnerTables.primerOption:
			sqlC.setPrimerFilter(gP, pP);
			primerTable = new Table("Primer");
			primerTable.setContainerDataSource(sqlC.getPrimerContainer());
			grid.addComponent(primerTable, 0, 1);
			searchBar= new SearchBar(gP, pP);
			break;
			
		case WiesnerTables.cloningVectorOption:
			sqlC.setVectorFilter(gP, vP);
			vectorTable = new Table("Vector");
			vectorTable.setContainerDataSource(sqlC.getVectorContainer());
			searchBar= new SearchBar(gP, vP);
			
			grid.addComponent(vectorTable, 0, 1);
			break;
		case WiesnerTables.proteinConstructOption:
			sqlC.setProteinFilter(gP, pcP);
			proteinTable = new Table("Protein");
			proteinTable.setContainerDataSource(sqlC.getProteinContainer());
			grid.addComponent(proteinTable, 0, 1);
			searchBar= new SearchBar(gP, pcP);
			break;
			
		case WiesnerTables.allOption:
			sqlC.setGeneralFilter(gP);
			
			primerTable = new Table("Primer");
			vectorTable = new Table("Vector");
			proteinTable = new Table("Protein");
			
			primerTable.setContainerDataSource(sqlC.getPrimerContainer());
			vectorTable.setContainerDataSource(sqlC.getVectorContainer());
			proteinTable.setContainerDataSource(sqlC.getProteinContainer());
			searchBar= new SearchBar(gP);
			
			grid.addComponent(primerTable, 0, 1);
			grid.addComponent(vectorTable, 0, 2);
			grid.addComponent(proteinTable, 0, 3);
			break;
		}
		grid.addComponent(searchBar,0,0);
		addComponent(grid);
	}
}
