package com.poodledb;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.declarative.Design;

import SearchForm.SearchPrimerView;

@SuppressWarnings("serial")
@Theme("test")
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
		setContent(layout);
		Layout searchPrimerView = new SearchPrimerView();
		
		//Visuals: 
		TextField caption= new TextField("Search");
		Button button = new Button("Search for Primer");

		layout.addComponent(caption);
		button.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				searchPrimerView.addComponent(new Label("Thank you for searching"));
				setContent(searchPrimerView);
			}
		});
		layout.addComponent(button);

	}

}