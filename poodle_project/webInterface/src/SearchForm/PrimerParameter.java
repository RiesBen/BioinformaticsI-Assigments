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
		parameters[0]   = new TextField("protein");
		parameters[1]   = new TextField("domain");
		parameters[1]   = new TextField("cloningtype");
		parameters[1]   = new TextField("project");
		parameters[1]   = new TextField("restrictionSite");
		parameters[1]   = new TextField("direction");
		parameters[1]   = new TextField("organism");

		addComponent(displays);
		this.changeViewLarge();
	}
}
