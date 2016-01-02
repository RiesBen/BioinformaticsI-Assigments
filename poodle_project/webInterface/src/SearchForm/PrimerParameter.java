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
		title = new Label("Primer Parameters");
		parameters = new TextField[5];
		entryParameters = new Component[13];
		
		//Search Parameter:
		parameters[0] = new TextField("project");
		parameters[1] = new TextField("organism");
		parameters[2] = new TextField("protein");
		parameters[3] = new TextField("domain");
		parameters[4] = new TextField("restrictionSite");
		
		entryParameters[0] = new OptionGroup("cloning types");
		entryParameters[1] = new TextField("restriction sites");
		entryParameters[2] = new TextField("direction");
		
		entryParameters[3] = new TextField("melting temperatur");
		entryParameters[4] = new TextField("concentration");
		
		entryParameters[5] = new TextField("primer Sequence");
		entryParameters[6] = new TextField("protein sequence");
		
		entryParameters[7] = new TextField("notes");

		((OptionGroup) entryParameters[0]).addItems("classic", "QC", "RF");
		
		addComponent(displays);
		this.changeViewLarge();
	}
	public void changeToEntryForm(){
		this.removeAllComponents();
		GridLayout grid = new GridLayout(4,12);
		Label label1 = new Label("organisatory");
		Label label2 = new Label("biology");
		Label label3 = new Label("cloning");
		Label label4 = new Label("specifications");
		Label label5 = new Label("sequences");
		Label label6 = new Label("rest");
		
		grid.addComponent(label1, 0, 0);
		grid.addComponent(parameters[0], 1, 1);
		grid.addComponent(parameters[1], 2, 1);
		grid.addComponent(parameters[2], 3, 1);
		
		grid.addComponent(label2, 0, 2);
		grid.addComponent(parameters[3], 1, 3);
		grid.addComponent(parameters[4], 2, 3);
		
		grid.addComponent(label3, 0, 4);
		grid.addComponent(entryParameters[0], 1, 5);
		grid.addComponent(entryParameters[1], 2, 5);
		grid.addComponent(entryParameters[2], 3, 5);
		
		grid.addComponent(label4, 0, 6);
		grid.addComponent(entryParameters[3], 1, 7);
		grid.addComponent(entryParameters[4], 2, 7);

		grid.addComponent(label5, 0, 8);
		grid.addComponent(entryParameters[5], 1, 9);
		grid.addComponent(entryParameters[6], 2, 9);
		
		grid.addComponent(entryParameters[7], 1, 11);
		
		grid.setSpacing(true);
		this.addComponent(grid);		
	}
}
