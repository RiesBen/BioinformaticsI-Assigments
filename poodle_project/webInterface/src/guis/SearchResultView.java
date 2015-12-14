package guis;

import com.poodledb.NavigationBar;
import com.vaadin.annotations.Push;
import com.vaadin.data.Container.Filter;
import com.vaadin.ui.*;

import SearchForm.GeneralSearchParameter;
import SearchForm.PrimerParameter;
import SearchForm.ProteinConstructParameter;
import SearchForm.SearchBar;
import SearchForm.VectorParameter;
import sqlClasses.SQLCommunicator;

@Push
public class SearchResultView extends VerticalLayout{
	GridLayout grid = new GridLayout(1,4);
	SearchBar searchBar;
	Table primerTable;
	Table vectorTable;
	Table proteinTable;
	
	public SearchResultView(SQLCommunicator sqlC, GeneralSearchParameter gP, PrimerParameter pP, VectorParameter vP, ProteinConstructParameter pcP){
		//Table selection, and searchbar init
		switch(sqlC.getTable()){
		case "primer":
			sqlC.setGeneralFilter(gP);
			sqlC.setPrimerFilter(pP);
			primerTable = new Table("Primer");
			
			primerTable.setContainerDataSource(sqlC.getPrimerContainer());
			grid.addComponent(primerTable, 0, 1);
			
			searchBar= new SearchBar(gP, pP);
			break;
		case "cloningVectors":
			sqlC.setGeneralFilter(gP);
			sqlC.setVectorFilter(vP);
			vectorTable = new Table("Vector");
					
			sqlC.setVectorFilter(vP);
			vectorTable.setContainerDataSource(sqlC.getVectorContainer());
			searchBar= new SearchBar(gP, vP);
			
			grid.addComponent(vectorTable, 0, 1);
			break;
		case "proteinConstructs":
			sqlC.setGeneralFilter(gP);
			sqlC.setProteinFilter(pcP);
			
			proteinTable = new Table("Protein");
			proteinTable.setContainerDataSource(sqlC.getProteinContainer());
			grid.addComponent(proteinTable, 0, 1);
			searchBar= new SearchBar(gP, pcP);
			break;
			
		case "all":
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
