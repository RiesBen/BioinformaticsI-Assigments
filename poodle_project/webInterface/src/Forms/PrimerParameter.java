package Forms;

import com.vaadin.server.UserError;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Alignment;
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
		
		entryParameters[0] = new TextField("project");
		entryParameters[1] = new TextField("designed for");
		
		entryParameters[2] = new TextField("organism");
		entryParameters[3] = new TextField("protein");
		entryParameters[4] = new TextField("domain");
		
		entryParameters[5] = new OptionGroup("cloning types");
		entryParameters[6] = new TextField("cloning information");
		
		entryParameters[7] = new TextField("direction");
		entryParameters[8] = new TextField("melting temperatur");
		entryParameters[9] = new TextField("concentration");
	
		entryParameters[10] = new TextField("primer Sequence");
		entryParameters[11] = new TextField("protein sequence");

		
		entryParameters[12] = new TextField("notes");

		((OptionGroup) entryParameters[5]).addItems("restriction", "QC", "RF");
		
		addComponent(displays);
		this.changeViewLarge();
	}
	public void changeToEntryForm(){
		this.removeAllComponents();
		GridLayout grid = new GridLayout(4,12);
		grid.setDefaultComponentAlignment(Alignment.TOP_CENTER);
		grid.setWidth(90, Unit.PERCENTAGE);
		
		Label label1 = new Label("biology");
		Label label2 = new Label("cloning");
		Label label4 = new Label("specifications");
		Label label5 = new Label("sequences");
		Label label6 = new Label("rest");
		
		grid.addComponent(label1, 0, 0);
		grid.addComponent(entryParameters[0], 1, 1);
		grid.addComponent(entryParameters[1], 2, 1);

		grid.addComponent(entryParameters[2], 1, 2);
		grid.addComponent(entryParameters[3], 2, 2);
		grid.addComponent(entryParameters[4], 3, 2);
		
		grid.addComponent(label2, 0, 3);
		grid.addComponent(entryParameters[5], 1, 4);
		grid.addComponent(entryParameters[6], 2, 4);

		
		grid.addComponent(label4, 0, 5);
		grid.addComponent(entryParameters[7], 1, 6);
		grid.addComponent(entryParameters[8], 2, 6);
		grid.addComponent(entryParameters[9], 3, 6);

		grid.addComponent(label5, 0, 7);
		grid.addComponent(entryParameters[10], 1, 8);
		grid.addComponent(entryParameters[11], 2, 8);
		
		grid.addComponent(entryParameters[12], 1, 10);
		
		grid.setSpacing(true);
		this.addComponent(grid);		
	}
}
