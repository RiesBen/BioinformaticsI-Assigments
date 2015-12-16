package pageElements;

import java.io.File;

import com.vaadin.server.FileResource;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;

import SearchForm.GeneralSearchParameter;
import SearchForm.PrimerParameter;
import SearchForm.ProteinConstructParameter;
import SearchForm.VectorParameter;
import contentViews.SearchResultView;
import sqlClasses.SQLCommunicator;

public class SearchBar extends HorizontalLayout {


	//	SearchButton
	private Button searchButton;

	//Search Parameter:
	//General
	private GeneralSearchParameter generalParameters;
	private PrimerParameter primerParameters;
	private VectorParameter vectorParameters;
	private ProteinConstructParameter proteinContstructParameters;

	public SearchBar(GeneralSearchParameter gP){
		this.setSpacing(true);
		this.generalParameters = gP;
		this.generalParameters.changeViewSmall();
		this.addComponent(generalParameters);

		this.init();
	}

	public SearchBar(GeneralSearchParameter gP, PrimerParameter pP) {
		this.setSpacing(true);
		
		this.generalParameters = gP;
		this.generalParameters.changeViewSmall();
		this.addComponent(generalParameters);
	
		this.primerParameters=pP;
		this.primerParameters.changeViewSmall();
		this.addComponent(primerParameters);
		
		this.init();
	}


	public SearchBar(GeneralSearchParameter gP, VectorParameter vP) {
		this.setSpacing(true);
		this.generalParameters = gP;
		this.generalParameters.changeViewSmall();
		this.addComponent(generalParameters);
		
		this.vectorParameters=vP;
		this.vectorParameters.changeViewSmall();
		this.addComponent(vectorParameters);
		this.init();
	}


	public SearchBar(GeneralSearchParameter gP, ProteinConstructParameter pcP) {
		this.setSpacing(true);
		this.generalParameters = gP;
		this.generalParameters.changeViewSmall();
		this.addComponent(generalParameters);
		
		this.proteinContstructParameters=pcP;
		this.proteinContstructParameters.changeViewSmall();
		this.addComponent(proteinContstructParameters);
		this.init();
	}


	public void init() {
		// Search Button 
		searchButton = new Button("Search");
		searchButton.addStyleName("searchButton");
		searchButton.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				Label na = new Label("Did it again!");
				addComponent(na);
			}
		});

		//add
		this.addComponent(searchButton);
	}
}
