package SearchForm;

import com.vaadin.ui.*;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.declarative.Design;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;


public class GeneralSearchParameter extends VerticalLayout{
	//Grid Layout:
	GridLayout grid = new GridLayout(2,4);
	
	//Search Parameter:
	   TextField parameter1   = new TextField("ID");
	   TextField parameter2   = new TextField("box");
	   TextField parameter3   = new TextField("position");
	   TextField parameter4   = new TextField("name");
	   TextField parameter5   = new TextField("protein");
	   TextField parameter6   = new TextField("domain");
	   TextField parameter7   = new TextField("author");
	   
	   //Rest Navigation
	   Label     displays = new Label("General Parameters.");

	    public GeneralSearchParameter() {
	    		
	    	
	        addComponent(displays);
	    	grid.addComponent(parameter1, 0, 0);
	        grid.addComponent(parameter2, 0, 1);
	        grid.addComponent(parameter3, 0, 2);
	        grid.addComponent(parameter4, 0, 3);
	        grid.addComponent(parameter5, 1, 0);
	        grid.addComponent(parameter6, 1, 1);
	        grid.addComponent(parameter7, 1, 2);
	        addComponent(grid);

	    }
	}
