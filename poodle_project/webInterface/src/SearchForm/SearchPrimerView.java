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

@SuppressWarnings("serial")
@Theme("test")
@DesignRoot

public class SearchPrimerView extends VerticalLayout{
	//Such Parameter:
	   TextField parameter1   = new TextField("ID");
	   TextField parameter2   = new TextField("box");
	   TextField parameter3   = new TextField("position");
	   TextField parameter4   = new TextField("name");
	   TextField parameter5   = new TextField("protein");
	   TextField parameter6   = new TextField("domain");
	   TextField parameter7   = new TextField("Made by");
	   
	   //Rest Navigation
	   Label     displays = new Label("Please enter your Parameters.");
	   Button    click   = new Button("Search This");

	    public SearchPrimerView() {
	    		
	    	
	        addComponent(displays);
	    	addComponent(parameter1);
	        addComponent(parameter2);
	        addComponent(parameter3);
	        addComponent(parameter4);
	        addComponent(parameter5);
	        addComponent(parameter6);
	        addComponent(parameter7);
	        addComponent(click);
	        
	        // Configure it a bit
	        setSizeFull();
	        addStyleName("myview");
	    }
	}
