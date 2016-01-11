package contentViews;

import com.poodledb.PoodledbUI;
import com.vaadin.annotations.Push;
import com.vaadin.data.Container.Filter;
import com.vaadin.ui.*;

import Forms.GeneralSearchParameter;
import Forms.PrimerParameter;
import Forms.ProteinConstructParameter;
import Forms.VectorParameter;
import PackageCommunicators.SQLCommunicator;
import pageElements.NavigationBar;
import pageElements.SearchBar;
import specificValues.WiesnerTables;

@Push
public class SearchResultView extends BasicView{
//	private thisLayout this = new thisLayout(1,4);
	private SearchBar searchBar;
	private Table primerTable;
	private Table vectorTable;
	private Table proteinTable;
	
	public SearchResultView(SQLCommunicator sqlC, GeneralSearchParameter gP, PrimerParameter pP, VectorParameter vP, ProteinConstructParameter pcP){

		this.setSizeFull();
//		this.setMargin(true);
		//Table selection, and searchbar init
		switch(sqlC.getTable()){
		case WiesnerTables.primerOption:
			sqlC.setPrimerFilter(gP, pP);
			primerTable = new Table("Primer");
			primerTable.setContainerDataSource(sqlC.getPrimerContainer());
			searchBar= new SearchBar(gP, pP);
			
			primerTable.setContainerDataSource(sqlC.getPrimerContainer());
			primerTable.sort();
			
			this.addComponent(searchBar);
			this.addComponent(primerTable);
			break;
			
		case WiesnerTables.cloningVectorOption:
			sqlC.setVectorFilter(gP, vP);
			vectorTable = new Table("Vector");
			vectorTable.setContainerDataSource(sqlC.getVectorContainer());
			searchBar= new SearchBar(gP, vP);
			
			vectorTable.setWidth(this.getWidth(), Unit.PERCENTAGE);
			
			this.addComponent(searchBar);
			this.addComponent(vectorTable);
			break;
		case WiesnerTables.proteinConstructOption:
			sqlC.setProteinFilter(gP, pcP);
			proteinTable = new Table("Protein");
			proteinTable.setContainerDataSource(sqlC.getProteinContainer());
			searchBar= new SearchBar(gP, pcP);
			
			proteinTable.setWidth(this.getWidth(), Unit.PERCENTAGE);
			
			this.addComponent(searchBar);
			this.addComponent(proteinTable);
			break;
			
		case WiesnerTables.allOption:
			sqlC.setGeneralFilter(gP);
			
			primerTable = new Table("Primer");
			vectorTable = new Table("Vector");
			proteinTable = new Table("Protein");
			
			primerTable.setColumnReorderingAllowed(true);
			primerTable.setWidth(this.getWidth(), Unit.PERCENTAGE);
			vectorTable.setWidth(this.getWidth(), Unit.PERCENTAGE);
			proteinTable.setWidth(this.getWidth(), Unit.PERCENTAGE);
			
			primerTable.setContainerDataSource(sqlC.getPrimerContainer());
			primerTable.sort();
			
			vectorTable.setContainerDataSource(sqlC.getVectorContainer());
			vectorTable.sort();
			
			proteinTable.setContainerDataSource(sqlC.getProteinContainer());
			proteinTable.sort();
			searchBar= new SearchBar(gP);
			
			this.addComponent(searchBar);
			this.addComponent(primerTable);
			this.addComponent(vectorTable);
			this.addComponent(proteinTable);
			break;
		}

	}
}
