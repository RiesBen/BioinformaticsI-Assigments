package contentViews;

import com.poodledb.PoodledbUI;
import com.vaadin.annotations.Push;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;

import SearchForm.GeneralSearchParameter;
import SearchForm.PrimerParameter;
import SearchForm.ProteinConstructParameter;
import SearchForm.VectorParameter;
import pageElements.SearchBar;
import sqlClasses.SQLCommunicator;

public class NewEntryView extends VerticalLayout {
		
		//SQL-Stuff for preBuffering of Textfields
		private String dbSQL; 
		private SQLCommunicator sqlC;
		
		private PoodledbUI poodleUI;
	
		private Label title = new Label("You want to create a new Entry?");
		
		private Button newEntryButton;
		private Upload importButton;
		private VerticalLayout buttons;
		
		private VerticalLayout parameters;
		private GeneralSearchParameter generalParameter = new GeneralSearchParameter();
		private VectorParameter vectorParameter = new VectorParameter();
		private PrimerParameter primerParameter = new PrimerParameter();
		private ProteinConstructParameter proteinConstructParameter = new ProteinConstructParameter();
		
		public NewEntryView(PoodledbUI poodleUI){
			this.poodleUI= poodleUI;
			
			//initialize
			//SQL stuff
			dbSQL=poodleUI.getdBs().wiesnerDB; //<-- CHANGE HERE!
			sqlC = new SQLCommunicator(dbSQL);

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
					try{
						fieldValdiation();
					}
					catch(Exception e){
						Label error = new Label ("Fields are not correct!");
						addComponent(error);
						System.out.println(e);
					}
				}
			});
			//import Button 
			//initialize
			importButton = new Upload("import", null);
			//function:
//			importButton.addClickListener(new Button.ClickListener() {
//				public void buttonClick(ClickEvent event) {
//					Label working = new Label ("still working on");
//					addComponent(working);
//				}
//			});
			
			buttons = new VerticalLayout();
			buttons.addComponent(importButton);
			buttons.addComponent(newEntryButton);
			buttons.setSpacing(true);
			
			
			//build up:
			this.addComponent(title);
			this.addComponent(generalParameter);
			this.addComponent(parameters);
			this.addComponent(buttons);
			this.modifyToPrimerParamAdvanced();
		}

		public void modifyToPrimerParamAdvanced() {
			parameters.removeAllComponents();
			this.getPrimerParameter().changeToEntryForm();
			this.getGeneralParameter().changeToEntryForm(sqlC);
			parameters.addComponent(this.getGeneralParameter());
			parameters.addComponent(this.getPrimerParameter());
			
		}

		public void modifyToVectorParamAdvanced() {
			parameters.removeAllComponents();
			this.getVectorParameter().changeToEntryForm();
			this.getGeneralParameter().changeToEntryForm(sqlC);
			parameters.addComponent(this.getGeneralParameter());
			parameters.addComponent(this.getVectorParameter());
		}

		public void modifyToProteinParamAdvanced() {
			parameters.removeAllComponents();
			this.getProteinConstructParameter().changeToEntryForm();
			this.getGeneralParameter().changeToEntryForm(sqlC);
			parameters.addComponent(this.getGeneralParameter());
			parameters.addComponent(this.getProteinConstructParameter());
		}
		
		
//		Evaluate the fields for correctness:
		private void fieldValdiation() throws Exception{
			switch (poodleUI.getServerTable().getTable()){
				case "primer":
					this.generalParameter.evaluate();
					break;
				case "vector":
					break;

				case "proteinConstruct":
					break;
			}
		}
		
		//------------------------------------------------------------------------------------------------------------------------
		//Getter and Setter:
		//------------------------------------------------------------------------------------------------------------------------		

		public GeneralSearchParameter getGeneralParameter() {
			return generalParameter;
		}

		public void setgeneralParameter(GeneralSearchParameter generalParameter) {
			this.generalParameter = generalParameter;
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
