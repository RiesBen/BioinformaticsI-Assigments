package SearchForm;

import com.vaadin.ui.*;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.declarative.Design;


public class VectorParameter extends BasicParameters{
	   //Rest Navigation
	   Label     displays = new Label("Vector Parameters");

	    public VectorParameter() {
	    	grid = new GridLayout(1,2);
			parameters = new TextField[2];

			//Search Parameter:
			parameters[0]   = new TextField("Tags");
			parameters[1]   = new TextField("Tev cleavage?");

	        addComponent(displays);
	        this.changeViewLarge();
	    }
	}
