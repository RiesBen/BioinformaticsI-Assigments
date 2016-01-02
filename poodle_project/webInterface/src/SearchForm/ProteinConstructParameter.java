package SearchForm;

import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class ProteinConstructParameter extends BasicParameters{

	    public ProteinConstructParameter() {
			parameters = new TextField[5];
			//Search Parameter:
			parameters[0]   = new TextField("C-Tags");
			parameters[1]   = new TextField("N-Tags");
			parameters[2]   = new TextField("Mutation");
			parameters[3]   = new TextField("protein-family");
			parameters[4]   = new TextField("vector");

			title= new Label("Protein Construct Parameter");
	        this.changeViewLarge();

	    }
}
