package contentViews;

import com.vaadin.annotations.Push;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;

import SearchForm.GeneralSearchParameter;
import pageElements.SearchBar;

public class NewEntryView extends VerticalLayout {
		Label title = new Label("You want to create a new Entry?");
		GeneralSearchParameter generalParameters = new GeneralSearchParameter();
		Button newEntryButton;
		Button importButton;
		HorizontalLayout buttons;
		
		public NewEntryView(){

			//Layout
			this.setDefaultComponentAlignment(Alignment.TOP_CENTER);
			this.setSpacing(true);

			//newEntry Button 
			//initialize
			newEntryButton = new Button("newEntry");
			newEntryButton.addStyleName("newEntry");
			//function:
			newEntryButton.addClickListener(new Button.ClickListener() {
				public void buttonClick(ClickEvent event) {
					Label working = new Label ("still working on");
					addComponent(working);
				}
			});
			//import Button 
			//initialize
			importButton = new Button("import");
			importButton.addStyleName("import");
			//function:
			importButton.addClickListener(new Button.ClickListener() {
				public void buttonClick(ClickEvent event) {
					Label working = new Label ("still working on");
					addComponent(working);
				}
			});
			
			buttons = new HorizontalLayout();
			buttons.addComponent(newEntryButton);
			buttons.addComponent(importButton);
			buttons.setSpacing(true);
			

			this.addComponent(title);
			this.addComponent(generalParameters);
			this.addComponent(buttons);
		}

		public void modifyToPrimerParamAdvanced() {
			// TODO Auto-generated method stub
			
		}

		public void modifyToVectorParamAdvanced() {
			// TODO Auto-generated method stub
			
		}

		public void modifyToProteinParamAdvanced() {
			// TODO Auto-generated method stub
			
		}
	}
