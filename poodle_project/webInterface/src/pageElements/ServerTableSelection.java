package pageElements;

import com.poodledb.PoodledbUI;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.NativeSelect;

import contentViews.NewEntryView;
import contentViews.SearchView;
import specificValues.BasicTables;
import specificValues.WiesnerTables;

public class ServerTableSelection extends HorizontalLayout{
	private PoodledbUI poodledb;
	private NativeSelect tableSelect;
	private NativeSelect dbSelect;

	public ServerTableSelection(PoodledbUI poodledb){
		this.poodledb = poodledb;

		//Layout:
		this.setSpacing(true);

		//Button generation:
		//1. Select which Database:
		dbSelect = new NativeSelect("Select the Database");
		dbSelect.addItems(poodledb.getdBs().allOption,poodledb.getdBs().wiesnerDB, poodledb.getdBs().sprangerDB);
		dbSelect.setValue(poodledb.getdBs().wiesnerDB);
		dbSelect.setNullSelectionAllowed(false);


		//2. Select which table you want to search through
		tableSelect = new NativeSelect("Table");
		tableSelect.addItems(BasicTables.allOption, BasicTables.primerOption,  BasicTables.cloningVectorOption,  BasicTables.proteinConstructOption);
		tableSelect.setValue(BasicTables.allOption);
		tableSelect.setNullSelectionAllowed(false);


		// Handling of Selections:
		//	dbSelect.addValueChangeListener(event -> dbSQL = (String) event.getProperty().getValue()))); <----- To Do: MAKE MULTI DB HERE ABLE
		tableSelect.addValueChangeListener(event -> this.tableValues((String) event.getProperty().getValue()));
		dbSelect.setReadOnly(true);

		this.addComponent(tableSelect);
		this.addComponent(dbSelect);
	}
	
	public void setForBlastView(){
		this.removeAllComponents();
	}

	public void setForBlastViewIntern(){
		this.removeAllComponents();
		tableSelect= new NativeSelect("Table");
		tableSelect.addItems(poodledb.getTables().primerOption,  poodledb.getTables().proteinConstructOption);
		tableSelect.setValue(poodledb.getTables().primerOption);
		tableSelect.setNullSelectionAllowed(false);
		tableSelect.addValueChangeListener(event -> this.tableValues((String) event.getProperty().getValue()));
		this.addComponent(tableSelect);
		this.addComponent(dbSelect);
	}
	
	public void setForNewEntryView(){
		this.removeAllComponents();
		tableSelect= new NativeSelect("Table");
		tableSelect.addItems(poodledb.getTables().primerOption, poodledb.getTables().cloningVectorOption,  poodledb.getTables().proteinConstructOption);
		tableSelect.setValue(poodledb.getTables().primerOption);
		tableSelect.setNullSelectionAllowed(false);
		tableSelect.addValueChangeListener(event -> this.tableValues((String) event.getProperty().getValue()));
		this.addComponent(tableSelect);
		this.addComponent(dbSelect);
	}
	public void setForSearchView(){
		this.removeAllComponents();
		tableSelect= new NativeSelect("Table");
		tableSelect.addItems(BasicTables.allOption, BasicTables.primerOption,  BasicTables.cloningVectorOption,  BasicTables.proteinConstructOption);
		tableSelect.setValue(BasicTables.allOption);
		tableSelect.setNullSelectionAllowed(false);
		tableSelect.addValueChangeListener(event -> this.tableValues((String) event.getProperty().getValue()));
		this.addComponent(tableSelect);
		this.addComponent(dbSelect);
	}


	public void refresh(){
		dbSelect.setValue(poodledb.getdBs().wiesnerDB);
		tableSelect.setValue(BasicTables.allOption);
	}
	public String getTable(){
		return (String)tableSelect.getValue();
	}

	public String getDB(){
		return (String) dbSelect.getValue();
	}

	private void tableValues(String val){
		Layout view = (Layout) poodledb.getContentBox();

		if(view instanceof SearchView){
			SearchView searchView= (SearchView) view;
			switch(val){
			case WiesnerTables.allOption:
				searchView.modifyToGenParam();
				break;
			case WiesnerTables.primerOption:
				searchView.modifyToPrimerParam();
				break;
			case WiesnerTables.cloningVectorOption:
				searchView.modifyToVectorParam();
				break;
			case WiesnerTables.proteinConstructOption:
				searchView.modifyToProteinParam();
				break;
			}
		}
		if(view instanceof NewEntryView){
			NewEntryView newEntry= (NewEntryView) view;
			switch(val){
			case WiesnerTables.primerOption:
				newEntry.modifyToPrimerParamAdvanced();
				break;
			case WiesnerTables.cloningVectorOption:
				newEntry.modifyToVectorParamAdvanced();
				break;
			case WiesnerTables.proteinConstructOption:
				newEntry.modifyToProteinParamAdvanced();
				break;
			}
	    	try {
				poodledb.getNewEntryView().setAutomaticID();
			} catch (Exception e) {
				System.out.println("The ID finding process failed: "+e);
				e.printStackTrace();
			}
		}
	}
}