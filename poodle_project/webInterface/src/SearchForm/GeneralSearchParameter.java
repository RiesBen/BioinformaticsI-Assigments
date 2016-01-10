package SearchForm;

import com.vaadin.ui.*;

import java.util.Date;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Property;
import com.vaadin.server.UserError;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Field.ValueChangeEvent;
import com.vaadin.ui.declarative.Design;

import sqlClasses.SQLCommunicator;


public class GeneralSearchParameter extends BasicParameters{
	private DateField date;

	public GeneralSearchParameter() {
		parameters = new TextField[7];
		entryParameters = new TextField[5];

		title = new Label("General Parameters");

		//Parameters for a Search request!
		parameters[0] = new TextField("Id");
		parameters[1] = new TextField("box");
		parameters[2] = new TextField("position");
		parameters[3] = new TextField("name");
		parameters[4] = new TextField("date");
		parameters[5] = new TextField("author");
		parameters[6] = new TextField("protein");

		//Parameters for a New Entry
		entryParameters[0] = new TextField("Id");
		entryParameters[1] = new TextField("box");
		entryParameters[2] = new TextField("position");
		entryParameters[3] = new TextField("name");
		entryParameters[4] = new TextField("author");
		date = new DateField("date");


		
		// some adjustification for parameter fields:
		this.fillID();
		entryParameters[0].setEnabled(false);
		
		((TextField) entryParameters[1]).setNullSettingAllowed(false);
		
		date.setValue(new java.util.Date());
		date.setImmediate(true);
		date.setValidationVisible(true);

		
		this.changeViewLarge();         
	}

	public void changeToEntryForm(SQLCommunicator sqlC){
		this.removeAllComponents();
		GridLayout grid = new GridLayout(4,4);
		grid.setSpacing(true);

		Label label1 = new Label("General");
		Label label2 = new Label("Location");

		grid.addComponent(label1, 0, 0);
		grid.addComponent(entryParameters[3], 1,1);
		grid.addComponent(date, 2,1);
		grid.addComponent(entryParameters[4], 3,1);

		grid.addComponent(label2, 1,2);
		grid.addComponent(entryParameters[0], 1,3);
		grid.addComponent(entryParameters[1], 2,3);
		grid.addComponent(entryParameters[2], 3,3);

		this.addComponent(grid);
	}

	private void fillID() {
		((TextField) entryParameters[0]).setValue("xxx");
	}

	public void evaluate() throws Exception {
		for(int i=0; i< entryParameters.length; i++){
			if(((TextField) entryParameters[i]).getValue().equals("") || ((TextField) entryParameters[i]).getValue().equals(null) || ((TextField) entryParameters[i]).getValue().equals("")){
//				((TextField) entryParameters[i]).setRequiredError("is required!");
				((TextField) entryParameters[i]).setComponentError(new UserError("This Field is required!"));
			}
			else{
				if(((TextField) entryParameters[i]).isValid()){
					((TextField) entryParameters[i]).setComponentError(null);
					continue;
				}
				else{
					((TextField) entryParameters[i]).setConversionError("OH OH");
					
					
					
				}
			}
		}
	}
}