package SearchForm;

import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class PrimerParameter extends VerticalLayout {
	//Grid Layout:
	GridLayout grid = new GridLayout(1,2);
	
	//Search Parameter:
	   TextField parameter1   = new TextField("RestrictionSites");
	   TextField parameter2   = new TextField("Cloning Type");

	   
	   //Rest Navigation
	   Label     displays = new Label("Primer Parameters.");

	    public PrimerParameter() {
	    	grid.setSpacing(true);
	        addComponent(displays);
	    	grid.addComponent(parameter1, 0, 0);
	        grid.addComponent(parameter2, 0, 1);
	        addComponent(grid);

	    }
}
