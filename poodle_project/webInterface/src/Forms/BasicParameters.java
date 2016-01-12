package Forms;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.ibm.icu.util.Calendar;
import com.vaadin.server.UserError;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public abstract class BasicParameters extends VerticalLayout {
	//Grid Layout:
	protected GridLayout grid;
	//Search Parameter:
	protected TextField[] parameters;
	protected Component[] entryParameters;
	protected Label title = new Label("unknown");
	//Rest Navigation
	Label displays = new Label("General Parameters.");
	public void fill(String[][] strings){
		for(int j=0; j< strings.length; j++){
			for(int i=0; i< parameters.length; i++){
				if(parameters[i].getDescription().equals(strings[j][0])){
					parameters[i].setValue(strings[j][1]);
				}
			}
		}
	}
	public void changeViewSmall(){
		this.removeAllComponents();
		grid= new GridLayout(3,(parameters.length/3)+2);

		int j=-1;
		for(int i=0; i<parameters.length; i++){
			if(i%3==0){
				j++;
			}
			parameters[i].setWidth("175px");
			//			parameters[i].setHeight("25px");
			grid.addComponent(parameters[i], j, i%3);
		}
		this.addComponent(grid);
	}

	public void changeViewLarge(){
		this.removeAllComponents();

		grid = new GridLayout(2,4);

		int j=-1;
		for(int i=0; i<parameters.length; i++){
			if(i%4==0){
				j++;
			}
			grid.addComponent(parameters[i], j, i%4);
		}
		grid.setSpacing(true);

		this.addComponent(title);
		this.addComponent(grid);
	}

	public void changeToEntryForm(){
		this.removeAllComponents();
	}

	public String getParameter(int x){
		return  parameters[x].getValue();
	}
	public String[][] getParamteters(){
		String[][] entry = new String[parameters.length][2];
		for (int i=0; i< entry.length; i++){
			entry[i][0] = parameters[i].getCaption();
			entry[i][1] = parameters[i].getValue();
		}
		return entry;
	}
	public void refreshTextfields(){
		for(int i=0; i<parameters.length; i++){
			parameters[i].setValue("");
		}
	}
	
	public boolean evaluate(boolean focus) throws Exception {
		boolean focused = focus;
		boolean error = false;
		for(int i=0; i< entryParameters.length; i++){
			if(entryParameters[i] instanceof TextField){
				if(((TextField) entryParameters[i]).getValue().equals("") || ((TextField) entryParameters[i]).getValue().equals(null) || ((TextField) entryParameters[i]).getValue().equals("")){
					((TextField) entryParameters[i]).setComponentError(new UserError("This Field is required!"));
					error=true;
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
						error=true;
						if(!focused){
							focused=true;
							((TextField) entryParameters[i]).focus();
						}
					}
				}
			}
			else if(entryParameters[i] instanceof TextArea){
				
			}
		}
		if(error){
			throw new Exception("one or more fields are not correct!");
		}
		return focused;
	}

	public String makeSimpleEntry() throws Exception{
		String entry="";

		for(int i=0; i<entryParameters.length; i++){
			if(entryParameters[i] instanceof DateField){
				SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
				String date = sdf.format(((DateField) entryParameters[i]).getValue()); 
				entry+="|"+date;
			}
			else if(entryParameters[i] instanceof TextField){
				if(((TextField) entryParameters[i]).getCaption().equals("id")){
					entry+=((TextField) entryParameters[i]).getValue();
				}
				entry+="|"+((TextField) entryParameters[i]).getValue();
			} 
			else if(entryParameters[i] instanceof TextArea){
				entry+="|"+((TextArea) entryParameters[i]).getValue();
			}
			else if(entryParameters[i] instanceof OptionGroup){
				if(((OptionGroup) entryParameters[i]).getCaption().equals("cloning types")){
					System.out.println(((OptionGroup) entryParameters[i]).getValue());
					if(((OptionGroup) entryParameters[i]).getValue() == null){
						entry+="|n|n|n";
					}
					else if(((OptionGroup) entryParameters[i]).getValue().equals("restriction")){
						entry+="|y|n|n";
					}
					else if(((OptionGroup) entryParameters[i]).getValue().equals("QC")){
						entry+="|n|y|n";
					}
					else if(((OptionGroup) entryParameters[i]).getValue().equals("RF")){
						entry+="|n|n|y";
					}
				}
				else if(((OptionGroup) entryParameters[i]).getCaption().equals("cell stocks")){
					System.out.println(((OptionGroup) entryParameters[i]).getValue());
					if(((OptionGroup) entryParameters[i]).getValue()==null){
						entry+="|n|n";
					}
					else if(((OptionGroup) entryParameters[i]).getValue().equals("DH5A")){
						entry+="|y|n";
					}
					else if(((OptionGroup) entryParameters[i]).getValue().equals("C+")){
						entry+="|n|y";
					}
				}
				else{
					throw new Exception("something with optiongroup!");
				}
			}
			else{
				throw new Exception("not only all components are handled! write another a more specific method for "+entryParameters[i].getClass());
			}
		}
		System.out.println(entry);
		return entry;
	}

	//------------------------------------------------------------------------------------------------------------------------
	//Getter and Setter:
	//------------------------------------------------------------------------------------------------------------------------		

	protected Component getEntryParameters(int x){
		return entryParameters[x]; 
	}
}