package com.poodledb;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.NativeSelect;

import SearchForm.GeneralSearchParameter;
import SearchForm.PrimerParameter;
import SearchForm.ProteinConstructParameter;
import SearchForm.VectorParameter;
import guis.SearchView;

public class ServerTableSelection extends HorizontalLayout{
	PoodledbUI poodledb;
	NativeSelect tableSelect;
	NativeSelect dbSelect;
	
	public ServerTableSelection(PoodledbUI poodledb){
		this.poodledb = poodledb;

		//Selection Buttons (for table
		//Which server or Table??
		this.setSpacing(true);
		//Select which Database:
		dbSelect = new NativeSelect("Select the Database");
		dbSelect.addItems("all","Sprangers", "Wiesner");
		dbSelect.setValue("Wiesner");
		dbSelect.setNullSelectionAllowed(false);
		//Select which table you want to search through
		tableSelect = new NativeSelect("Pre-Select");
		tableSelect.addItems("all", "Primer", "cloningVectors", "proteinConstructs");
		tableSelect.setValue("all");
		tableSelect.setNullSelectionAllowed(false);
		// Handling of Selections:
		//	dbSelect.addValueChangeListener(event -> dbSQL = (String) event.getProperty().getValue())));
		tableSelect.addValueChangeListener(event -> this.tableValues((String) event.getProperty().getValue()));
		
		this.addComponent(tableSelect);
		this.addComponent(dbSelect);
	}
	
	public String getTable(){
		return (String)tableSelect.getValue();
	}

	private void tableValues(String val){
		Layout view = (Layout) poodledb.getContentBox();
		HorizontalLayout parameters;
		
		if(view instanceof SearchView){
			parameters= ((SearchView) view).getParameters();
			switch(val){
			case "all":
				parameters.removeAllComponents();
				parameters.addComponent(new GeneralSearchParameter());
				break;
			case "Primer":
				parameters.removeAllComponents();
				parameters.addComponent(new GeneralSearchParameter());
				parameters.addComponent(new PrimerParameter());
				break;
			case "cloningVectors":
				parameters.removeAllComponents();
				parameters.addComponent(new GeneralSearchParameter());
				parameters.addComponent(new VectorParameter());
				break;
			case "proteinConstructs":
				parameters.removeAllComponents();
				parameters.addComponent(new GeneralSearchParameter());
				parameters.addComponent(new ProteinConstructParameter());
				break;
			}
		}
	}
}