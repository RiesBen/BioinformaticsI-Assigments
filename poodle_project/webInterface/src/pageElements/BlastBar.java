package pageElements;

import com.poodledb.PoodledbUI;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class BlastBar extends VerticalLayout {

	/**
	 * 
	 */
	private Label title = new Label("options");
	private HorizontalLayout panel= new HorizontalLayout();
	private NativeSelect mode;
	private ServerTableSelection serverTableSelection;
	private NativeSelect ncbiServer;
	private NativeSelect program;
	private TextField querry;
	//	private PoodledbUI poodleUI;


	public BlastBar(PoodledbUI poodleUI){
		//		this.poodleUI = poodleUI;

		//initialize all Components
		serverTableSelection = new ServerTableSelection(poodleUI);
		serverTableSelection.setForNewEntryView();
		
		//layout:
		panel.setDefaultComponentAlignment(Alignment.TOP_CENTER);
		panel.setSpacing(true);
		this.setDefaultComponentAlignment(Alignment.TOP_CENTER);
		this.setDefaultComponentAlignment(Alignment.TOP_CENTER);
		this.setWidth((float) (this.panel.getWidth()*2), Unit.PERCENTAGE);
		
		//fields:
		mode= new NativeSelect("mode");
		mode.addItems("local", "internet");
		mode.setValue("local");
		mode.setNullSelectionAllowed(false);
		mode.addValueChangeListener(event -> this.modeValues((String) event.getProperty().getValue()));

		program= new NativeSelect("program");
		program.addItems("blastp", "blastn");
		program.setValue("blastn");
		program.setNullSelectionAllowed(false);

		ncbiServer= new NativeSelect("server");
		ncbiServer.addItems("swissprot", "pdb");
		ncbiServer.setValue("swissprot");
		ncbiServer.setNullSelectionAllowed(false);

		querry=new TextField("querry");
		querry.setInputPrompt("For Advanced users.");
		panel.addComponents(mode, program, serverTableSelection);
		this.addComponents(title, panel);
		
		mode.setReadOnly(true);
		program.setReadOnly(true);
	}

	public void modeValues(String modeValue){
		if(modeValue.equals("local")){
			this.removeAllComponents();
			panel.removeAllComponents();
			panel.addComponents(mode, program, serverTableSelection);
			this.addComponents(title, panel);
		}
		else if(modeValue.equals("internet")){
			this.removeAllComponents();
			panel.removeAllComponents();
			panel.addComponents(mode, program, ncbiServer);
			this.addComponents(title, panel, querry);
		}
	}

	public String getMode(){
		return (String) mode.getValue();
	}
	public String getProgram(){
		return (String) program.getValue();
	}
	public String getDBTable(){
		if(this.getMode().equals("local")){
			return serverTableSelection.getTable();
		}
		else{
			return (String) ncbiServer.getValue(); 
		}
	}
	public String getDB(){
		return serverTableSelection.getDB();
	}
	public String getQuerry(){
		if(this.getMode().equals("local")){
			return null;
		}
		else{
			return querry.getValue();
		}
	}
}
