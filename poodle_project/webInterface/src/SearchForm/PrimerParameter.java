package SearchForm;

import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class PrimerParameter extends BasicParameters{

	//Rest Navigation
	Label     displays = new Label("Primer Parameters.");

	public PrimerParameter() {
		parameters = new TextField[6];
		Component[] entryParameters = new Component[13];
		
		//Search Parameter:
		parameters[0] = new TextField("protein");
		parameters[1] = new TextField("domain");
		parameters[2] = new TextField("cloningtype");
		parameters[3] = new TextField("project");
		parameters[5] = new TextField("restrictionSite");
		parameters[4] = new TextField("organismmmmmmmmmmmmmmmm");
		
		entryParameters[0] = new TextField("restriction site");
		entryParameters[1] = new TextField("direction");
		entryParameters[2] = new OptionGroup("cloning types");
		((OptionGroup) entryParameters[2]).addItems("classic", "QC", "RF");
		entryParameters[3] = new TextField("melting temperatur");
		entryParameters[4] = new TextField("concentration");
		entryParameters[5] = new TextField("primer Sequence");
		entryParameters[6] = new TextField("protein sequence");
		entryParameters[7] = new TextField("notes");

		
		
		addComponent(displays);
		this.changeViewLarge();
	}
}
