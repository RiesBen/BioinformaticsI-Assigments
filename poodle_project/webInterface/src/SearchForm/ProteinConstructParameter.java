package SearchForm;

import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class ProteinConstructParameter extends BasicParameters{
	   //Rest Navigation
	   Label     displays = new Label("Protein Construct Parameter");

	    public ProteinConstructParameter() {
	    	grid = new GridLayout(1,2);
			parameters = new TextField[2];

			//Search Parameter:
			parameters[0]   = new TextField("C-Tags");
			parameters[1]   = new TextField("N-Tags");
			parameters[1]   = new TextField("Mutation");
			parameters[1]   = new TextField("protein-family");
			parameters[1]   = new TextField("vector");

	        addComponent(displays);
	        this.changeViewLarge();

	    }
}
