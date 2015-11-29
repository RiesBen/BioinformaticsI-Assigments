package guis;

import com.vaadin.annotations.Push;
import com.vaadin.ui.*;

import SearchForm.GeneralSearchParameter;
import SearchForm.SearchBar;

public class NewEntryView extends VerticalLayout {
		VerticalLayout root = new VerticalLayout();
		Label title = new Label("You want to create a new Entry?");
		GeneralSearchParameter generalParameters = new GeneralSearchParameter();
		HorizontalLayout serverTableBar = new HorizontalLayout();
		
		public NewEntryView(){

			//Layout
			root.setDefaultComponentAlignment(Alignment.TOP_CENTER);
			
			//Selection Buttons (for table
			//Which server or Table??
			serverTableBar.setSpacing(true);
			//Select which Database:
			NativeSelect dbSelect = new NativeSelect("Select the Database");
			dbSelect.addItems("Both","Sprangers", "Wiesner");
			dbSelect.setValue("Wiesner");
			//Select which table you want to search through
			NativeSelect tableSelect = new NativeSelect("Pre-Select");
			tableSelect.addItems("Primer", "Vector", "Protein-Construct");
			tableSelect.setValue("Primer");
			
			// Handling of Selections:
//			dbSelect.addValueChangeListener(event -> dbSQL = (String) event.getProperty().getValue())));
//			tableSelect.addValueChangeListener(event -> this.tableValues((String) event.getProperty().getValue()));

			serverTableBar.addComponent(tableSelect);
			serverTableBar.addComponent(dbSelect);
			
			root.addComponent(title);
			root.addComponent(serverTableBar);
			root.addComponent(generalParameters);
			
			
			addComponent(root);
		}
	}
