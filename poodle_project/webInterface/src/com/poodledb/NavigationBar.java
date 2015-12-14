package com.poodledb;

import java.io.File;

import com.vaadin.server.FileResource;
import com.vaadin.server.Resource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.*;

import guis.BlastView;
import guis.NewEntryView;
import guis.SearchView;

public class NavigationBar extends MenuBar{
	PoodledbUI poodledb;
	
	//new Entry icon
	//Integration of icon
	String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();//Change in the END!!!
	FileResource resource1 = new FileResource(new File(basepath+"/WEB-INF/img/plus_colored-small.png"));
	
	
	//Commands for Navigation Bar
	MenuBar.Command searchCommand = new MenuBar.Command() {
	    public void menuSelected(MenuItem selectedItem) {
	    	poodledb.modifyLayouttoSearchView();
	    	poodledb.SetContentBox(poodledb.getSearchView());
	    	poodledb.getServerTable().refresh();
	    }
	};
	
	MenuBar.Command newEntryCommand = new MenuBar.Command() {
	    public void menuSelected(MenuItem selectedItem) {
	    	poodledb.modifyLayouttoSearchView();
	    	poodledb.SetContentBox(new NewEntryView());
	    	poodledb.getServerTable().refresh();
			
	    }
	};
	
	MenuBar.Command blastCommand = new MenuBar.Command() {
	    public void menuSelected(MenuItem selectedItem) {
	    	poodledb.modifyLayouttoSearchView();
	    	poodledb.SetContentBox(new BlastView());
	    	poodledb.getServerTable().refresh();
    	}
	};
	

	
	public NavigationBar(PoodledbUI poodledb){		
		// Define a common menu command for all the menu items.
		this.addStyleName("valo-menu");
		this.poodledb=poodledb;
		
		MenuItem search = this.addItem("Search", searchCommand);
		MenuItem newEntry = this.addItem("New Entry", resource1, newEntryCommand);
		MenuItem blast = this.addItem("BLAST", blastCommand);
	}
}
