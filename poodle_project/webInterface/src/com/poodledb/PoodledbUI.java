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
import guis.NewEntryView;
import guis.SearchResultView;
import guis.SearchView;

@SuppressWarnings("serial")
@Theme("poodledb")
@DesignRoot
public class PoodledbUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = PoodledbUI.class)
	public static class Servlet extends VaadinServlet {
	}
	
	@Override
	protected void init(VaadinRequest request) {
		//Layouts
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		layout.setDefaultComponentAlignment(Alignment.TOP_CENTER);
		layout.setSpacing(true);
		layout.addStyleName("layoutStyle");

		setContent(layout);

		//setup search view
		//initially start with search view

		
		//initial ContentContainer:
		VerticalLayout content= new SearchView();
		content.setDefaultComponentAlignment(Alignment.TOP_CENTER);
		//MenuBar: 
		MenuBar navigationMenu = new NavigationBar(content);
		navigationMenu.addStyleName("navigationMenu");

		
		//whole Layout
		layout.addComponent(navigationMenu);
		layout.addComponent(content);
		layout.setComponentAlignment(navigationMenu, Alignment.TOP_LEFT);
		
	}

}