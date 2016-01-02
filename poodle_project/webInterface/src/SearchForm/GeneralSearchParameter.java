
package SearchForm;

import com.vaadin.ui.*;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.declarative.Design;


public class GeneralSearchParameter extends BasicParameters{
	public GeneralSearchParameter() {
		parameters = new TextField[7];

		parameters[0] = new TextField("Id");
		parameters[1] = new TextField("box");
		parameters[2] = new TextField("position");
		parameters[3] = new TextField("name");
		parameters[4] = new TextField("date");
		parameters[5] = new TextField("author");
		parameters[6]   = new TextField("protein");
		
		title = new Label("General Parameters");
		
		this.changeViewLarge();
	}
	public void changeToEntryForm(){
		this.removeAllComponents();
		GridLayout grid = new GridLayout(4,4);
		
		Label label1 = new Label("General");
		Label label2 = new Label("Location");
		
		grid.addComponent(label1, 0, 0);
		grid.addComponent(parameters[3], 1,1);
		grid.addComponent(parameters[4], 2,1);
		grid.addComponent(parameters[5], 3,1);
		
		grid.addComponent(label2, 1,2);
		grid.addComponent(parameters[0], 1,3);
		grid.addComponent(parameters[1], 2,3);
		grid.addComponent(parameters[2], 3,3);
	}
}
