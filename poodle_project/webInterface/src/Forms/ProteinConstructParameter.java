package Forms;

import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class ProteinConstructParameter extends BasicParameters{

	public ProteinConstructParameter() {
		title= new Label("Protein Construct Parameter");

		parameters = new TextField[5];
		//Search Parameter:
		parameters[0]   = new TextField("C-Tags");
		parameters[1]   = new TextField("N-Tags");
		parameters[2]   = new TextField("Mutation");
		parameters[3]   = new TextField("protein-family");
		parameters[4]   = new TextField("vector");			

		//entry Parameter:
		entryParameters = new Component[17];

		entryParameters[0] = new TextField("project");
		entryParameters[1] = new TextField("organism");
		entryParameters[2] = new TextField("protein-family");
		entryParameters[3] = new TextField("protein");
		entryParameters[4] = new TextField("domain");

		entryParameters[5]   = new TextField("mutation");
		entryParameters[6]   = new TextField("C-Term-Tags");
		entryParameters[7]   = new TextField("N-Term-bbTags");

		entryParameters[8] = new TextField("backbone vector");
		entryParameters[9] = new TextField("antibiotics");			

		entryParameters[10] = new OptionGroup("cloning types");
		entryParameters[11] = new TextField("cloning information");
		entryParameters[12] = new TextField("used primer");
		entryParameters[13] = new TextField("constructed from vector");

		entryParameters[14] = new TextField("concentration");
		entryParameters[15] = new OptionGroup("cell stocks");

		entryParameters[16] = new TextField("notes");


		((OptionGroup) entryParameters[15]).addItems("DH5A", "C+");
		((OptionGroup) entryParameters[10]).addItems("restriction", "QC", "RF");

		this.changeViewLarge();
	}
	public void changeToEntryForm(){
		this.removeAllComponents();
		GridLayout grid = new GridLayout(6,14);
		grid.setWidth(90, Unit.PERCENTAGE);
		Label label1 = new Label("protein");
		Label label2 = new Label("construct");
		Label label3 = new Label("plasmid");
		Label label4 = new Label("cloning");
		Label label5 = new Label("sample");
		Label label6 = new Label("Sequences");

		grid.addComponent(label1, 0, 0);
		grid.addComponent(entryParameters[0], 1, 1);
		grid.addComponent(entryParameters[1], 2, 1);
		grid.addComponent(entryParameters[2], 3, 1);

		grid.addComponent(entryParameters[3], 1, 2);
		grid.addComponent(entryParameters[4], 2, 2);
		grid.addComponent(label2, 0, 3);
		grid.addComponent(entryParameters[5], 1, 4);
		grid.addComponent(entryParameters[6], 2, 4);
		grid.addComponent(entryParameters[7], 3, 4);
		grid.addComponent(label3, 0, 5);
		grid.addComponent(entryParameters[8], 1, 6);
		grid.addComponent(entryParameters[9], 2, 6);
		grid.addComponent(label4, 0, 7);
		grid.addComponent(entryParameters[12], 1, 8);
		grid.addComponent(entryParameters[13], 2, 8);
		grid.addComponent(entryParameters[10], 1, 9);
		grid.addComponent(entryParameters[11], 2, 9);
		grid.addComponent(label5, 0, 10);
		grid.addComponent(entryParameters[14], 1, 11);
		grid.addComponent(entryParameters[15], 2, 11);

		grid.addComponent(entryParameters[16], 1, 13);

		grid.setSpacing(true);
		this.addComponent(grid);		
	}
}
