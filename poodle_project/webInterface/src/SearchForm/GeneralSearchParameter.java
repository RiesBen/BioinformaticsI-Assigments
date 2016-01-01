
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
	//Rest Navigation
	Label displays = new Label("General Parameters.");

	public GeneralSearchParameter() {
		parameters = new TextField[7];

		parameters[0] = new TextField("Id");
		parameters[1] = new TextField("box");
		parameters[2] = new TextField("position");
		parameters[3] = new TextField("name");
		parameters[4] = new TextField("date");
		parameters[5] = new TextField("author");
		parameters[6]   = new TextField("protein");
		
		setDefaultComponentAlignment(Alignment.TOP_CENTER);
		
		addComponent(displays);
		this.changeViewLarge();

	}
}
