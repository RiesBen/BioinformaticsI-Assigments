package SearchForm;

import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class PrimerParameter extends BasicParameters{




	//Rest Navigation
	Label     displays = new Label("Primer Parameters.");

	public PrimerParameter() {
		grid = new GridLayout(1,2);
		parameters = new TextField[2];

		//Search Parameter:
		parameters[0]   = new TextField("RestrictionSites");
		parameters[1]   = new TextField("Cloning Type");

		addComponent(displays);
		this.changeViewLarge();
	}
}
