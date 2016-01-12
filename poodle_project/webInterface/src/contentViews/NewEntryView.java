package contentViews;

import java.io.File;
import java.util.Collection;

import org.apache.commons.io.FileUtils;

import com.poodledb.PoodledbUI;
import com.vaadin.annotations.Push;
import com.vaadin.data.Property;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.query.OrderBy;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;

import Forms.BasicParameters;
import Forms.GeneralSearchParameter;
import Forms.PrimerParameter;
import Forms.ProteinConstructParameter;
import Forms.VectorParameter;
import PackageCommunicators.SQLCommunicator;
import pageElements.SearchBar;

public class NewEntryView extends BasicView {

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

		//Layout
		this.setDefaultComponentAlignment(Alignment.TOP_CENTER);
		this.setSpacing(true);
		this.setMargin(true);

		//title
		title.setStyleName("center");
		//Parameters
		parameters = new VerticalLayout();
		parameters.setSpacing(true);
		parameters.setDefaultComponentAlignment(Alignment.TOP_CENTER);
		parameters.setWidth(this.getWidth()/2, Unit.PERCENTAGE);

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
					
					try{
						String basepath= VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
						System.out.println(basepath+"/WEB-INF/data/Wiesner/"+poodleUI.getTables());
						File entryTable = new File(basepath+"/WEB-INF/data/Wiesner/"+poodleUI.getServerTable().getTable()+"/"+poodleUI.getServerTable().getTable()+".csv");
						String entry="";

						for(int i=0; i<parameters.getComponentCount(); i++){
							entry+=((BasicParameters) parameters.getComponent(i)).makeSimpleEntry();
						}
						System.out.println(entry);
						if(!entry.isEmpty()){
							FileUtils.writeStringToFile(entryTable, entry, true);
							entryTable = null;
						}
					}
					catch(Exception e){
						System.out.println("Creating Entry faild:"+e);
						e.printStackTrace();
					}
				}
				catch(Exception e){
					System.out.println("Field evaluation is not working! "+e);
					e.printStackTrace();
				}
			}
		});
		//import Button 
		//initialize
		importButton = new Upload("import", null);
		importButton.setReadOnly(true);
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
		buttons.setDefaultComponentAlignment(Alignment.BOTTOM_CENTER);
		buttons.setWidth(this.getWidth()/3, Unit.PERCENTAGE);

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
		boolean focused = false;
		switch (poodleUI.getServerTable().getTable()){
		case "primer":
			focused=this.generalParameter.evaluate(focused);
			this.primerParameter.evaluate(focused);
			break;
		case "vector":
			focused=this.generalParameter.evaluate(focused);
			this.vectorParameter.evaluate(focused);
			break;

		case "proteinConstruct":
			focused=this.generalParameter.evaluate(focused);
			this.proteinConstructParameter.evaluate(focused);
			break;
		}
	}

	public void setAutomaticID() throws Exception{
		String table = this.poodleUI.getServerTable().getTable();
		//initialize
		//SQL stuff
		dbSQL=poodleUI.getdBs().wiesnerDB; //<-- CHANGE HERE!
		sqlC = new SQLCommunicator(dbSQL);
		sqlC.setTableQuerry(table);
		SQLContainer data;

		if(!sqlC.hasPrimerContainer()){
			data=sqlC.getPrimerContainer();
		}
		else if(!sqlC.hasProteinContainer()){
			data=sqlC.getProteinContainer();
		}
		else if(!sqlC.hasVectorContainer()){
			data=sqlC.getVectorContainer();
		}
		else{
			throw new Exception("could not find SQL Table");
		}


		Collection<?> itemIDS= data.getItemIds();
		int numberY = -1;
		int numberX=-1;
		data.addOrderBy(new OrderBy("id", true));
		for (Object itemID : itemIDS)
		{
			Property property= data.getContainerProperty(itemID, "id");
			numberX = (int) property.getValue();
			if(numberY==-1 && numberX!=1){
				numberX=1;
				break;
			}
			if(numberY == -1){
				numberY=numberX;
				continue;
			}
			else{
				if((numberY+1)== numberX){
					numberY=numberX;
					continue;
				}
				else{
					numberX=numberY+1;
					break;
				}
			}
		}
		this.generalParameter.setID(Integer.toString(numberX));

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
