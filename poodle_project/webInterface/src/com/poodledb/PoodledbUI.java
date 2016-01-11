package com.poodledb;

import java.io.File;
import java.io.FileReader;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.client.ui.VNotification.EventListener;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.declarative.Design;
import com.vaadin.ui.themes.ValoTheme;

import Forms.GeneralSearchParameter;
import contentViews.BlastResultView;
import contentViews.BlastView;
import contentViews.NewEntryView;
import contentViews.SearchResultView;
import contentViews.SearchView;
import pageElements.BlastBar;
import pageElements.NavigationBar;
import pageElements.ServerTableSelection;
import specificValues.BasicDBs;
import specificValues.BasicTables;
import specificValues.WiesnerDBs;
import specificValues.WiesnerTables;

@SuppressWarnings("serial")
@Theme("poodledb")
@DesignRoot

public class PoodledbUI extends UI {
	//"root box" 	
	private final VerticalLayout layout = new VerticalLayout(); // layout is the complete page, which is viewed
	
	//the page is build up by using the following elements:
	private static Image logo;
	private static MenuBar navigationMenu; // guide through the page
	private static ServerTableSelection serverTableSelection;	// select the Database (in work), and the single tables
	private static BlastBar blastBar; // for Blastview options for the Blast algorithms
	private static VerticalLayout content; // contains the different view classes.
	
	//The following classes each are used to get the content.
	private SearchView searchView;	// the class which provides the normal search context
	private NewEntryView newEntryView;	//class for newEntry 
	private BlastView blastView;	//class for new Blast run.

	//control elements
	private Boolean viewIsReduced=false;
	
	//specific value content box:
	private static BasicDBs dBs = new WiesnerDBs();
	private static BasicTables tables = new WiesnerTables();

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = PoodledbUI.class, widgetset = "com.poodledb.widgetset.PoodledbWidgetset")
	public static class Servlet extends VaadinServlet {
	}
	
	//initialization of the whole page
	@Override
	protected void init(VaadinRequest request) {
		
		//Layouts
		layout.setMargin(true);
		layout.setDefaultComponentAlignment(Alignment.TOP_CENTER);
		layout.setSpacing(true);
		layout.addStyleName("layoutStyle");

		setContent(layout);
		
		//Logo
		//Integration of Logo
		String basepath =  VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
		FileResource resource1 = new FileResource(new File(basepath+"/WEB-INF/img/logo_poodle.png"));
		logo= new Image("",resource1);
		logo.setWidth("512px");

		//MenuBar: 
		navigationMenu = new NavigationBar(this);
		navigationMenu.addStyleName("navigationMenu");

		//Selection
		serverTableSelection = new ServerTableSelection(this);
		blastBar = new BlastBar(this);
		//setup search view
		//initially start with search view
		//initial ContentContainer:
		searchView = new SearchView(this);
		newEntryView = new NewEntryView(this);
		blastView = new BlastView(this, blastBar);
		content= searchView;
		
		//whole building page, peace by peace
		this.modifyLayoutToLargeView();
	}
	
	//this method reduces the page layout to a smaller view (e.g.: used to display the search results).
	public void modifyLayoutToReducedView(){
		viewIsReduced=true;
		layout.removeAllComponents();
		layout.addComponent(navigationMenu);
		layout.addComponent(content);
		layout.setComponentAlignment(navigationMenu, Alignment.TOP_LEFT);
	}
	
	//this method enlarges the page layout to inital view (e.g.: Search start page))
	public void modifyLayoutToLargeView(){
		viewIsReduced=false;
		layout.removeAllComponents();
		layout.addComponent(logo);
		layout.addComponent(navigationMenu);
		layout.addComponent(serverTableSelection);
		layout.addComponent(content);
		layout.setDefaultComponentAlignment(Alignment.TOP_CENTER);
		content.setDefaultComponentAlignment(Alignment.TOP_CENTER);
	}
	
	
//------------------------------------------------------------------------------------------------------------------------
//Getter and Setter:
//------------------------------------------------------------------------------------------------------------------------
	public ServerTableSelection getServerTable(){
		return serverTableSelection;
	}
	
	public VerticalLayout getContentBox(){
		return content;
	}
	
	public void setContentBox(VerticalLayout view){
		this.content=view;
	}

	public SearchView getSearchView() {
		return searchView;
	}

	public void setSearchView(SearchView searchView) {
		this.searchView = searchView;
	}

	public NewEntryView getNewEntryView() {
		return newEntryView;
	}

	public void setNewEntryView(NewEntryView newEntry) {
		this.newEntryView = newEntry;
	}

	public BlastView getBlastView() {
		return blastView;
	}

	public void setBlastView(BlastView blastView) {
		this.blastView = blastView;
	}

	public BasicTables getTables() {
		return tables;
	}

	public void setTables(BasicTables tables) {
		this.tables = tables;
	}

	public BasicDBs getdBs() {
		return dBs;
	}

	public void setdBs(BasicDBs dBs) {
		this.dBs = dBs;
	}

	public Boolean getViewIsReduced() {
		return viewIsReduced;
	}

	public void setViewIsReduced(Boolean viewIsReduced) {
		this.viewIsReduced = viewIsReduced;
	}
}