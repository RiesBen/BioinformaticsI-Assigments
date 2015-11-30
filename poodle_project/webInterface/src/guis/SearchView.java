package guis;

import java.io.File;

import com.gargoylesoftware.htmlunit.javascript.host.Selection;
import com.poodledb.PoodledbUI;
import com.poodledb.popup;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.sqlcontainer.query.TableQuery;
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
import sqlClasses.SQLCommunicator;

@Push
public class SearchView extends VerticalLayout {
	Button searchButton;
	HorizontalLayout serverTableBar = new HorizontalLayout();
	HorizontalLayout parameters = new HorizontalLayout();
	
	VerticalLayout generalSearchParameter = new GeneralSearchParameter();
	VerticalLayout vectorParameter = new VectorParameter();
	VerticalLayout primerParameter = new PrimerParameter();
	VerticalLayout proteinConstructParameter = new ProteinConstructParameter();
	

	//SQL - THINGS
	String dbSQL="Wiesner"; 
	String tableSQL="all";
	SQLCommunicator sqlC;
	
	public SearchView(PoodledbUI poodleUI){
		//SQL
		 sqlC = new SQLCommunicator(dbSQL);
		
		
		//Layout 
		this.setDefaultComponentAlignment(Alignment.TOP_CENTER);
		this.setSpacing(true);
		
		//initialize
		generalSearchParameter = new GeneralSearchParameter();
		serverTableBar = new HorizontalLayout();
		parameters = new HorizontalLayout();
		
//		Layout:
		generalSearchParameter.addStyleName("Parameters");
		
		//Components
	
		// Search Button 
		//initialize
		searchButton = new Button("Search");
		searchButton.addStyleName("searchButton");
		//function:
		searchButton.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				sqlC.setTableQuerry(poodleUI.getServerTable().getTable());
				poodleUI.modifyLayouttoResultView();
				poodleUI.SetContentBox(new SearchResultView(sqlC));
			}
		});

		//Parameters initial:
		parameters.addComponent(generalSearchParameter);
		parameters.setSpacing(true);
		//build up:
		this.addComponent(parameters);
		this.addComponent(searchButton);
	}
	
	public HorizontalLayout getParameters(){
		return parameters;
	}
}
