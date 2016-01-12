package Forms;

import com.vaadin.ui.*;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.declarative.Design;


public class VectorParameter extends BasicParameters{
	    public VectorParameter() {
			parameters = new TextField[2];

			//Search Parameter:
			parameters[0]   = new TextField("Tags");
			parameters[1]   = new TextField("Tev cleavage?");

			
			//entry Parameter:
			entryParameters = new Component[17];
			
			entryParameters[0]   = new TextField("C-Term-Tags");
			entryParameters[1]   = new TextField("N-Term-Tags");
			entryParameters[2]   = new TextField("Construct cleavage?");
			
			entryParameters[3]   = new TextField("MCS");
			entryParameters[4]   = new TextField("antibiotics");
			
			entryParameters[5] = new TextField("fw seq primer");
			entryParameters[6] = new TextField("rv seq primer");
			
			entryParameters[7] = new TextField("vector map");
			entryParameters[7] = new TextField("size");
			entryParameters[8] = new TextField("concentration");
			entryParameters[9] = new OptionGroup("cell stock");
			
			entryParameters[10] = new TextField("notes");
			
			((OptionGroup) entryParameters[9]).addItems("DH5A", "C+");
			
			title = new Label("Vector Parameters");
	        this.changeViewLarge();
	    }
	    public void changeToEntryForm(){
			this.removeAllComponents();
			GridLayout grid = new GridLayout(4,11);
			grid.setWidth(90, Unit.PERCENTAGE);
			Label label1 = new Label("plasmid");
			Label label2 = new Label("cloning");
			Label label3 = new Label("sequencing");
			Label label4 = new Label("sample");

			
			grid.addComponent(label1, 0, 0);
			grid.addComponent(entryParameters[0], 1, 1);
			grid.addComponent(entryParameters[1], 2, 1);
			grid.addComponent(entryParameters[2], 3, 1);	
			
			grid.addComponent(entryParameters[7], 1, 2);
			grid.addComponent(entryParameters[4], 2, 2);
			
			grid.addComponent(label2, 0, 3);
			grid.addComponent(entryParameters[3], 1, 4);
			
			grid.addComponent(label3, 0, 5);
			grid.addComponent(entryParameters[5], 1, 6);
			grid.addComponent(entryParameters[6], 2, 6);
			
			grid.addComponent(label4, 0, 7);
			grid.addComponent(entryParameters[8], 1, 8);
			grid.addComponent(entryParameters[9], 2, 8);
			
			grid.addComponent(entryParameters[10], 1, 10);
			
			grid.setSpacing(true);
			this.addComponent(grid);		
		}
	}
