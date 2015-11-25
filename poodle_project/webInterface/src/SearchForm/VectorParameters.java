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


public class VectorParameters extends VerticalLayout{
	//Grid Layout:
	GridLayout grid = new GridLayout(2,1);
	
	//Search Parameter:
	   TextField parameter1   = new TextField("Tags");
	   TextField parameter2   = new TextField("Ant-Biotics");

	   
	   //Rest Navigation
	   Label     displays = new Label("Vector Parameters.");

	    public VectorParameters() {
	        addComponent(displays);
	    	grid.addComponent(parameter1, 1, 0);
	        grid.addComponent(parameter2, 2, 0);
	        addComponent(grid);

	    }
	}
