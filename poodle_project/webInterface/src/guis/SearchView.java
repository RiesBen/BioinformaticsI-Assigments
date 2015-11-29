package guis;

import java.io.File;

import com.gargoylesoftware.htmlunit.javascript.host.Selection;
import com.poodledb.PoodledbUI;
import com.poodledb.popup;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.BrowserWindowOpener;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.declarative.Design;
import com.vaadin.ui.themes.ValoTheme;

import SearchForm.GeneralSearchParameter;
import SearchForm.PrimerParameter;
import SearchForm.ProteinConstructParameter;
import SearchForm.VectorParameter;

@Push
public class SearchView extends HorizontalLayout {
	VerticalLayout root;
	Button searchButton;
	SearchResultView resultView = new SearchResultView();
	VerticalLayout generalSearchParameter = new GeneralSearchParameter();
	VerticalLayout vectorParameter = new VectorParameter();
	VerticalLayout primerParameter = new PrimerParameter();
	VerticalLayout proteinConstructParameter = new ProteinConstructParameter();
	
	HorizontalLayout serverTableBar = new HorizontalLayout();
	HorizontalLayout parameters = new HorizontalLayout();

	//SQL - THINGS
	String dbSQL="Wiesner"; 
	String tableSQL="all";
	
	
	public SearchView(){
		//root Layout 
		root = new VerticalLayout();
		root.setDefaultComponentAlignment(Alignment.TOP_CENTER);
		root.setSpacing(true);
		
		//initialize
		resultView = new SearchResultView();
		generalSearchParameter = new GeneralSearchParameter();
		serverTableBar = new HorizontalLayout();
		parameters = new HorizontalLayout();
		
//		Layout:
		generalSearchParameter.addStyleName("Parameters");
		
		//Components
		//Logo
		//Integration of Logo
		String basepath = "/home/benjamin_schroeder/BioinformaticsI-Assigments/poodle_project/webInterface/WebContent/";//Change in the END!!!
		FileResource resource1 = new FileResource(new File(basepath+"/WEB-INF/img/logo_poodle.png"));
		Image logo= new Image("",resource1);
		logo.setWidth("256px");
		
		// Search Button 
		//initialize
		searchButton = new Button("Search");
		searchButton.addStyleName("searchButton");
		//function:
		searchButton.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
//				root.removeAllComponents();
				
				SearchResultView result = new SearchResultView();
//				SearchBar look = newS
				root.addComponent(result.getLayout());
				Label na = new Label("LALALA");
				root.addComponent(na);
			}
		});
		
		//Selection Buttons (for table
		//Which server or Table??
		serverTableBar.setSpacing(true);
		//Select which Database:
		NativeSelect dbSelect = new NativeSelect("Select the Database");
		dbSelect.addItems("All","Sprangers", "Wiesner");
		dbSelect.setValue("Wiesner");
		//Select which table you want to search through
		NativeSelect tableSelect = new NativeSelect("Pre-Select");
		tableSelect.addItems("All", "Primer", "Vector", "Protein-Construct");
		tableSelect.setValue("All");
		
		// Handling of Selections:
//		dbSelect.addValueChangeListener(event -> dbSQL = (String) event.getProperty().getValue())));
		tableSelect.addValueChangeListener(event -> this.tableValues((String) event.getProperty().getValue()));


		//STICK everything together
		//Server and table selection
		serverTableBar.addComponent(tableSelect);
		serverTableBar.addComponent(dbSelect);
		
		//Parameters initial:
		parameters.addComponent(generalSearchParameter);
		parameters.setSpacing(true);
		//build up:
		root.addComponent(logo);
		root.addComponent(serverTableBar);
		root.addComponent(parameters);
		root.addComponent(searchButton);
		addComponent(root);
	}
	
	public Layout getRoot(){
		return root;
	}
	
	private void tableValues(String val){
		switch(val){
			case "All":
				parameters.removeAllComponents();
				parameters.addComponent(generalSearchParameter);
				break;
			case "Primer":
				parameters.removeAllComponents();
				parameters.addComponent(generalSearchParameter);
				parameters.addComponent(primerParameter);
				break;
			case "Vector":
				parameters.removeAllComponents();
				parameters.addComponent(generalSearchParameter);
				parameters.addComponent(vectorParameter);
				break;
			case "Protein-Construct":
				parameters.removeAllComponents();
				parameters.addComponent(generalSearchParameter);
				parameters.addComponent(proteinConstructParameter);
				break;
		}
	}
}
