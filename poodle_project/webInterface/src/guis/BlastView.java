package guis;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import SearchForm.GeneralSearchParameter;

public class BlastView extends VerticalLayout {
	Label title = new Label("You want to BLAST it?");
	HorizontalLayout serverTableBar = new HorizontalLayout();
	TextField seq = new TextField("Sequence");
	
	public BlastView(){
		
		//Layout
		this.setDefaultComponentAlignment(Alignment.TOP_CENTER);
		this.setSpacing(true);
		this.setMargin(true);
		//Selection Buttons (for table
		//Which server or Table??
		serverTableBar.setSpacing(true);
		//Select which Database:
		NativeSelect dbSelect = new NativeSelect("Select the Database");
		dbSelect.addItems("All","Sprangers", "Wiesner");
		dbSelect.setValue("Wiesner");
		dbSelect.setNullSelectionAllowed(false);
		//Select which table you want to search through
		NativeSelect tableSelect = new NativeSelect("Pre-Select");
		tableSelect.addItems("Primer & Vector", "Vector", "Protein-Construct");
		tableSelect.setValue("Primer & Vector");
		tableSelect.setNullSelectionAllowed(false);
		// Handling of Selections:
//		dbSelect.addValueChangeListener(event -> dbSQL = (String) event.getProperty().getValue())));
//		tableSelect.addValueChangeListener(event -> this.tableValues((String) event.getProperty().getValue()));

		//Textfield:
		seq.setInputPrompt("Here Sequence");
		seq.setCursorPosition(0);
		seq.setSizeFull();

		
		serverTableBar.addComponent(dbSelect);
		serverTableBar.addComponent(tableSelect);
	
		addComponent(title);
		addComponent(serverTableBar);
		addComponent(seq);
		

	}
}

