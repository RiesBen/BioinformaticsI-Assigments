package pageElements;

import com.poodledb.PoodledbUI;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.NativeSelect;

import SearchForm.GeneralSearchParameter;
import SearchForm.PrimerParameter;
import SearchForm.ProteinConstructParameter;
import SearchForm.VectorParameter;
import contentViews.SearchView;
import specificValues.WiesnerTables;

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
		tableSelect.addItems("all", "primer", "cloningVectors", "proteinConstructs");
		tableSelect.setValue("all");
		tableSelect.setNullSelectionAllowed(false);
		// Handling of Selections:
		//	dbSelect.addValueChangeListener(event -> dbSQL = (String) event.getProperty().getValue())));
		tableSelect.addValueChangeListener(event -> this.tableValues((String) event.getProperty().getValue()));
		
		this.addComponent(tableSelect);
		this.addComponent(dbSelect);
	}
	public void refresh(){
		dbSelect.setValue("Wiesner");
		tableSelect.setValue("all");
		tableSelect.addValueChangeListener(event -> this.tableValues((String) event.getProperty().getValue()));
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
			case WiesnerTables.allOption:
				parameters.removeAllComponents();
				parameters.addComponent(((SearchView) poodledb.getContent()).getGeneralSearchParameter());
				break;
			case WiesnerTables.primerOption:
				parameters.removeAllComponents();
				parameters.addComponent(((SearchView) poodledb.getContent()).getGeneralSearchParameter());
				parameters.addComponent(((SearchView) poodledb.getContent()).getPrimerParameter());
				break;
			case WiesnerTables.cloningVectorOption:
				parameters.removeAllComponents();
				parameters.addComponent(((SearchView) poodledb.getContent()).getGeneralSearchParameter());
				parameters.addComponent(((SearchView) poodledb.getContent()).getVectorParameter());
				break;
			case WiesnerTables.proteinConstructOption:
				parameters.removeAllComponents();
				parameters.addComponent(((SearchView) poodledb.getContent()).getGeneralSearchParameter());
				parameters.addComponent(((SearchView) poodledb.getContent()).getProteinConstructParameter());
				break;
			}
		}
	}
}