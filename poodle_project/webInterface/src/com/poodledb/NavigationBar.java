package com.poodledb;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.*;

public class NavigationBar extends MenuBar{
	//MenuBar: 
	MenuBar barMenu;
	
	public NavigationBar(){
		barMenu = new MenuBar();
		MenuItem search = barMenu.addItem("Search", null, null);
		MenuItem newEntry = barMenu.addItem("New Entry", null, null);


	}
}
