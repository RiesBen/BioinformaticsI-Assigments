package com.poodledb;

import java.io.File;
import java.io.FileReader;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.declarative.Design;
import com.vaadin.ui.themes.ValoTheme;
import SearchForm.GeneralSearchParameter;
import searchPage.SearchResult;

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
		
		
		
		//MenuBar: 

		MenuBar navigationMenu = new MenuBar();
		MenuItem search = navigationMenu.addItem("Search", null, null);
		MenuItem newEntry = navigationMenu.addItem("New Entry", null, null);
		navigationMenu.addStyleName("navigationMenu");
		
		//Integration of Logo
		String basepath = "/home/benjamin_schroeder/BioinformaticsI-Assigments/poodle_project/webInterface/WebContent/";//Change in the END!!!
		FileResource resource1 = new FileResource(new File(basepath+"/WEB-INF/img/logo_poodle.png"));
		Image logo= new Image("",resource1);
		logo.setWidth("256px");
		
		
		// Search Button 
		Button searchButton = new Button("Search");
		searchButton.addStyleName("searchButton");
		searchButton.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				Layout search = new SearchResult();
				search.addComponent(new Label("Thank you for searching"));
				setContent(search);
			}
		});
		
		
		//Which server or Table??
		HorizontalLayout serverTableBar = new HorizontalLayout();
		serverTableBar.setSpacing(true);
		//Select which Database:
		NativeSelect dbSelect = new NativeSelect("Select the Database");
		dbSelect.addItems("All","Sprangers", "Wiesner");
		dbSelect.setValue("Wiesner");
		//Select which table you want to search through
		NativeSelect tableSelect = new NativeSelect("Pre-Select");
		tableSelect.addItems("All", "Primer", "Vectors", "Protein-Construct");
		tableSelect.setValue("All");
		
		//PARAMETERS:
		HorizontalLayout parameters = new HorizontalLayout();
		
		//General Search Parameters:
		VerticalLayout generalSearchParameter = new GeneralSearchParameter();
		generalSearchParameter.addStyleName("Parameters");
		
		//Vector Search Parameters
		
		//Primer Search Parameters
		
		//STICK everything together
		//Server and table selection
		serverTableBar.addComponent(tableSelect);
		serverTableBar.addComponent(dbSelect);
		
		//Parameters:
		parameters.addComponent(generalSearchParameter);
		
		//whole Layout
		layout.addComponent(navigationMenu);
		layout.addComponent(logo);
		layout.addComponent(serverTableBar);
		layout.addComponent(parameters);
		layout.addComponent(searchButton);
		
	}

}