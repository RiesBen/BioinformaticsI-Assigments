package contentViews;

import com.vaadin.annotations.Push;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;

import SearchForm.GeneralSearchParameter;
import SearchForm.PrimerParameter;
import SearchForm.ProteinConstructParameter;
import SearchForm.VectorParameter;
import pageElements.SearchBar;

public class NewEntryView extends VerticalLayout {
		private Label title = new Label("You want to create a new Entry?");
		
		private Button newEntryButton;
		private Button importButton;
		private HorizontalLayout buttons;
		
		private VerticalLayout parameters;
		private GeneralSearchParameter generalSearchParameter = new GeneralSearchParameter();
		private VectorParameter vectorParameter = new VectorParameter();
		private PrimerParameter primerParameter = new PrimerParameter();
		private ProteinConstructParameter proteinConstructParameter = new ProteinConstructParameter();
		
		public NewEntryView(){

			//Layout
			this.setDefaultComponentAlignment(Alignment.TOP_CENTER);
			this.setSpacing(true);

			//Parameters
			parameters = new VerticalLayout();
			parameters.setSpacing(true);
			
			//Buttons:
			//newEntry Button 
			//initialize
			newEntryButton = new Button("newEntry");

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
			
			
			//build up:
			this.addComponent(title);
			this.addComponent(generalSearchParameter);
			this.addComponent(parameters);
			this.addComponent(buttons);
			this.modifyToPrimerParamAdvanced();
		}

		public void modifyToPrimerParamAdvanced() {
			parameters.removeAllComponents();
			this.getPrimerParameter().changeToEntryForm();
			this.getGeneralSearchParameter().changeToEntryForm();
			parameters.addComponent(this.getGeneralSearchParameter());
			parameters.addComponent(this.getPrimerParameter());
			
		}

		public void modifyToVectorParamAdvanced() {
			parameters.removeAllComponents();
			parameters.addComponent(this.getGeneralSearchParameter());
			parameters.addComponent(this.getVectorParameter());
		}

		public void modifyToProteinParamAdvanced() {
			parameters.removeAllComponents();
			parameters.addComponent(this.getGeneralSearchParameter());
			parameters.addComponent(this.getProteinConstructParameter());
		}

		public GeneralSearchParameter getGeneralSearchParameter() {
			return generalSearchParameter;
		}

		public void setGeneralSearchParameter(GeneralSearchParameter generalSearchParameter) {
			this.generalSearchParameter = generalSearchParameter;
		}

		public VectorParameter getVectorParameter() {
			return vectorParameter;
		}

		public void setVectorParameter(VectorParameter vectorParameter) {
			this.vectorParameter = vectorParameter;
		}

		public PrimerParameter getPrimerParameter() {
			return primerParameter;
		}

		public void setPrimerParameter(PrimerParameter primerParameter) {
			this.primerParameter = primerParameter;
		}

		public ProteinConstructParameter getProteinConstructParameter() {
			return proteinConstructParameter;
		}

		public void setProteinConstructParameter(ProteinConstructParameter proteinConstructParameter) {
			this.proteinConstructParameter = proteinConstructParameter;
		}
	}
