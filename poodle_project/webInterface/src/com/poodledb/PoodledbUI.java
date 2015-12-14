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

import SearchForm.GeneralSearchParameter;
import contentViews.BlastView;
import contentViews.NewEntryView;
import contentViews.SearchResultView;
import contentViews.SearchView;
import pageElements.NavigationBar;
import pageElements.ServerTableSelection;

@SuppressWarnings("serial")
@Theme("poodledb")
@DesignRoot
public class PoodledbUI extends UI {
	
	private Image logo;
	private final VerticalLayout layout = new VerticalLayout();
	private MenuBar navigationMenu;
	private static ServerTableSelection serverTableSelection;
	private static VerticalLayout content;
	
	private SearchView searchView;
	private NewEntryView newEntryView;
	private BlastView blastView;
	
	
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = PoodledbUI.class)
	public static class Servlet extends VaadinServlet {
	}
	
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

		//setup search view
		//initially start with search view
		//initial ContentContainer:
		searchView = new SearchView(this);
		newEntryView  = new NewEntryView();
		blastView = new BlastView();
		content= new SearchView(this);
		
		//MenuBar: 
		navigationMenu = new NavigationBar(this);
		navigationMenu.addStyleName("navigationMenu");

		//Selection
		serverTableSelection = new ServerTableSelection(this);
		
		//whole Layout
		layout.addComponent(logo);
		layout.addComponent(navigationMenu);
		layout.addComponent(serverTableSelection);
		layout.addComponent(content);
	}
	
	public void modifyLayouttoResultView(){
		layout.removeComponent(logo);
		layout.removeComponent(serverTableSelection);
		layout.setComponentAlignment(navigationMenu, Alignment.TOP_LEFT);
	}
	public void modifyLayouttoSearchView(){
		layout.removeAllComponents();
		layout.addComponent(logo);
		layout.addComponent(navigationMenu);
		layout.addComponent(serverTableSelection);
		layout.addComponent(content);
	}
	
	public Layout getContentBox(){
		return content;
	}
	
	public void SetContentBox(Component view){
		content.removeAllComponents();
		content.addComponent(view);
	}
	public ServerTableSelection getServerTable(){
		return serverTableSelection;
	}

	public SearchView getSearchView() {
		return searchView;
	}

	public void setSearchView(SearchView searchView) {
		this.searchView = searchView;
	}

	public NewEntryView getNewEntry() {
		return newEntryView;
	}

	public void setNewEntry(NewEntryView newEntry) {
		this.newEntryView = newEntry;
	}

	public BlastView getBlastView() {
		return blastView;
	}

	public void setBlastView(BlastView blastView) {
		this.blastView = blastView;
	}
}