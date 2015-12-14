package contentViews;

import java.io.File;

import com.gargoylesoftware.htmlunit.javascript.host.Selection;
import com.poodledb.PoodledbUI;
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
import pageElements.popup;
import sqlClasses.SQLCommunicator;

@Push
public class SearchView extends VerticalLayout {
	Button searchButton;
	
	HorizontalLayout parameters;
	private GeneralSearchParameter generalSearchParameter = new GeneralSearchParameter();
	private VectorParameter vectorParameter = new VectorParameter();
	private PrimerParameter primerParameter = new PrimerParameter();
	private ProteinConstructParameter proteinConstructParameter = new ProteinConstructParameter();

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
		parameters = new HorizontalLayout();
		
		//Layout:		
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
				poodleUI.SetContentBox(new SearchResultView(sqlC, generalSearchParameter, primerParameter, vectorParameter, proteinConstructParameter));
			}
		});

		//Parameters initial:
		parameters.addComponent(generalSearchParameter);
		parameters.setSpacing(true);
		
		
		
		
		//build up:
		this.addComponent(parameters);
		this.addComponent(searchButton);
	}
	
	public GeneralSearchParameter getGeneralSearchParameter(){
		return generalSearchParameter;
	}
	public  VectorParameter getVectorParameter(){
		return vectorParameter;
	}
	public PrimerParameter getPrimerParameter(){
		return primerParameter;
	}
	public ProteinConstructParameter getProteinConstructParameter(){
		return proteinConstructParameter;
	}
	public HorizontalLayout getParameters(){
		return parameters;
	}
	
	
}
