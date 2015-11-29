package com.poodledb;

import java.io.File;

import com.vaadin.server.FileResource;
import com.vaadin.server.Resource;
import com.vaadin.ui.*;

import guis.BlastView;
import guis.NewEntryView;
import guis.SearchView;

public class NavigationBar extends MenuBar{
	VerticalLayout content;
	
	//Commands for Navigation Bar
	MenuBar.Command searchCommand = new MenuBar.Command() {
	    public void menuSelected(MenuItem selectedItem) {
	        System.out.println("Ordered a " +
	                           selectedItem.getText() +
	                           " from menu.");
	        content.removeAllComponents();
			content.addComponent(new SearchView());
	    }
	};
	
	MenuBar.Command newEntryCommand = new MenuBar.Command() {
	    public void menuSelected(MenuItem selectedItem) {
	        System.out.println("Ordered a " +
	                           selectedItem.getText() +
	                           " from menu.");
	        content.removeAllComponents();
			content.addComponent(new NewEntryView());
			
	    }
	};
	
	MenuBar.Command blastCommand = new MenuBar.Command() {
	    public void menuSelected(MenuItem selectedItem) {
	        System.out.println("Ordered a " +
	                           selectedItem.getText() +
	                           " from menu.");
	        content.removeAllComponents();
			content.addComponent(new BlastView());
    	}
	};
	//new Entry icon
	//Integration of icon
	String basepath = "/home/benjamin_schroeder/BioinformaticsI-Assigments/poodle_project/webInterface/WebContent/";//Change in the END!!!
	FileResource resource1 = new FileResource(new File(basepath+"/WEB-INF/img/plus_colored-small.png"));
	
	public NavigationBar(VerticalLayout content){		
		// Define a common menu command for all the menu items.
		this.content=content;
		MenuItem search = this.addItem("Search", searchCommand);
		MenuItem newEntry = this.addItem("New Entry", resource1, newEntryCommand);
		MenuItem blast = this.addItem("BLAST", blastCommand);
	}
}
