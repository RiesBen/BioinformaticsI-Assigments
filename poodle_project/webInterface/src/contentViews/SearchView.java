package contentViews;

import com.poodledb.PoodledbUI;
import com.vaadin.annotations.Push;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;

import Forms.GeneralSearchParameter;
import Forms.PrimerParameter;
import Forms.ProteinConstructParameter;
import Forms.VectorParameter;
import PackageCommunicators.SQLCommunicator;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

@Push
public class SearchView extends BasicView{
	
	private Button searchButton;
	private HorizontalLayout parameters;
	
	private GeneralSearchParameter generalSearchParameter = new GeneralSearchParameter();
	private VectorParameter vectorParameter = new VectorParameter();
	private PrimerParameter primerParameter = new PrimerParameter();
	private ProteinConstructParameter proteinConstructParameter = new ProteinConstructParameter();

	//SQL - THINGS
	private String dbSQL; 
	private SQLCommunicator sqlC;

	public SearchView(PoodledbUI poodleUI){
		//initialize
		//SQL stuff
		dbSQL=poodleUI.getdBs().wiesnerDB; //<-- CHANGE HERE!
		sqlC = new SQLCommunicator(dbSQL);

		//Layout 
		this.setDefaultComponentAlignment(Alignment.TOP_CENTER);
		this.setSpacing(true);
		
		
		//Components:
		//initialize
		parameters = new HorizontalLayout();
		parameters.setSpacing(true);
				
		// Search Button 
		//initialize
		searchButton = new Button("Search");
		
		//function:
		searchButton.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				sqlC.setTableQuerry(poodleUI.getServerTable().getTable());
				poodleUI.setContentBox(new SearchResultView( sqlC, generalSearchParameter, primerParameter, vectorParameter, proteinConstructParameter));
				poodleUI.modifyLayoutToReducedView();
			}
		});

		//build up:
		this.addComponent(parameters);
		this.addComponent(searchButton);
		this.modifyToGenParam();
		this.setComponentAlignment(searchButton, Alignment.TOP_CENTER);
	}

	public void modifyToGenParam(){
		parameters.removeAllComponents();
		parameters.addComponent(this.getGeneralSearchParameter());
	}
	public void modifyToPrimerParam(){
		parameters.removeAllComponents();
		parameters.addComponent(this.getGeneralSearchParameter());
		parameters.addComponent(this.getPrimerParameter());
	}
	public void modifyToVectorParam(){
		parameters.removeAllComponents();
		parameters.addComponent(this.getGeneralSearchParameter());
		parameters.addComponent(this.getVectorParameter());
	}
	public void modifyToProteinParam(){
		parameters.removeAllComponents();
		parameters.addComponent(this.getGeneralSearchParameter());
		parameters.addComponent(this.getProteinConstructParameter());
	}
	public void refreshParameters(){
		generalSearchParameter.changeViewLarge();
		generalSearchParameter.refreshTextfields();
		
		vectorParameter.changeViewLarge();
		vectorParameter.refreshTextfields();
		
		primerParameter.changeViewLarge();
		primerParameter.refreshTextfields();
		
		proteinConstructParameter.changeViewLarge();
		proteinConstructParameter.refreshTextfields();
	}
	
	
	
//------------------------------------------------------------------------------------------------------------------------
//Getter and Setter:
//------------------------------------------------------------------------------------------------------------------------
	public GeneralSearchParameter getGeneralSearchParameter(){
		return generalSearchParameter;
	}
	public  VectorParameter getVectorParameter(){
		return vectorParameter;
	}
	public PrimerParameter getPrimerParameter(){
		return primerParameter;
	}
	public ProteinConstructParameter getProteinConstructParameter(){
		return proteinConstructParameter;
	}
	public HorizontalLayout getParameters(){
		return parameters;
	}


}
