package Forms;

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

import PackageCommunicators.SQLCommunicator;


public class GeneralSearchParameter extends BasicParameters{

	public GeneralSearchParameter() {
		parameters = new TextField[7];
		entryParameters = new Component[6];

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

		entryParameters[0] = new TextField("id");
		entryParameters[1]= new DateField("date");
		entryParameters[2] = new TextField("name");
		entryParameters[3] = new TextField("author");



		entryParameters[4] = new TextField("box");
		entryParameters[5] = new TextField("position");


		// some adjustification for parameter fields:
		entryParameters[0].setReadOnly(true);

		((DateField)entryParameters[1]).setValue(new Date());
		((DateField)entryParameters[1]).setImmediate(true);
		((DateField)entryParameters[1]).setValidationVisible(true);

		this.changeViewLarge();         
	}

	public void changeToEntryForm(SQLCommunicator sqlC){
		this.removeAllComponents();
		GridLayout grid = new GridLayout(4,4);
		grid.setSpacing(true);
		grid.setWidth(90, Unit.PERCENTAGE);

		Label label1 = new Label("General");
		Label label2 = new Label("Location");

		grid.addComponent(label1, 0, 0);
		grid.addComponent(entryParameters[0], 1,1);
		grid.addComponent(entryParameters[1], 2,1);
		grid.addComponent(entryParameters[2], 3,1);

		grid.addComponent(label2, 1,2);
		grid.addComponent(entryParameters[3], 1,3);
		grid.addComponent(entryParameters[4], 2,3);
		grid.addComponent(entryParameters[5], 3,3);
		this.addComponent(grid);
	}

	public boolean evaluate(boolean focus) throws Exception {
		boolean focused = focus;
		for(int i=0; i< entryParameters.length; i++){
			if(entryParameters[i] instanceof TextField){
				if(((TextField) entryParameters[i]).getValue().equals("") || ((TextField) entryParameters[i]).getValue().equals(null) || ((TextField) entryParameters[i]).getValue().equals("")){
					((TextField) entryParameters[i]).setComponentError(new UserError("This Field is required!"));
					if(!focused){
						focused=true;
						((TextField) entryParameters[i]).focus();
					}
				}
				else{
					if(((TextField) entryParameters[i]).isValid()){
						((TextField) entryParameters[i]).setComponentError(null);
						continue;
					}
					else{
						((TextField) entryParameters[i]).setConversionError("OH OH");
						if(!focused){
							focused=true;
							((TextField) entryParameters[i]).focus();
						}
					}
				}
			}
		}
		return focused;
	}

	public void setID(String x){
		entryParameters[0].setReadOnly(false);
		((TextField) entryParameters[0]).setValue(x);
		entryParameters[0].setReadOnly(true);
	}
}