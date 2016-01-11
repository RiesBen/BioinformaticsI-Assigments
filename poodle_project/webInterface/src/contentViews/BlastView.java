package contentViews;

import com.poodledb.PoodledbUI;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import BlastClasses.BlastSearch;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;

import Forms.BlastSeqField;
import Forms.GeneralSearchParameter;
import PackageCommunicators.BlastCommunicator;
import pageElements.BlastBar;

public class BlastView extends BasicView {
	private Label title = new Label("You want to BLAST it?");
	private BlastBar blastBar;
	private BlastSeqField seq;
	private Button blast;
	private PoodledbUI poodleUI;
	private ProgressBar loadBar;
	private Label working;

	public BlastView(PoodledbUI pooldeUI, BlastBar blastBar){
		//initialization
		this.poodleUI = poodleUI;
		seq = new BlastSeqField();
		this.blastBar = blastBar;
		
		//Layout
		this.setDefaultComponentAlignment(Alignment.TOP_CENTER);
		this.setSpacing(true);
		this.setMargin(true);

		title.setStyleName("center");
		//Blast Button 
		//initialize
		blast = new Button("Blast");
		blast.addStyleName("searchBlast");
		//LAyout
		//function:
		blast.addClickListener(new Button.ClickListener() {

			public void buttonClick(ClickEvent event) {
				try{
					
					String querry= seq.evaluateSequence();
					
					getBlastView().removeComponent(blast);
					loadBar = new ProgressBar();
					loadBar.setIndeterminate(true);
					addComponent(loadBar);
					
					working = new Label ("Loading");
					working.addStyleName("center");
					addComponent(working);
					
					BlastCommunicator blastC = new BlastCommunicator(blastBar.getMode(),blastBar.getProgram(), querry, blastBar.getDB(), blastBar.getDBTable(), blastBar.getQuerry());
					
					poodleUI.SetContentBox(new BlastResultView(blastC.getResult()));
					poodleUI.modifyLayoutToReducedView();
					try{
					}catch(Exception e){
						System.out.println("System-Error"+e);
					}
				}catch(Exception e){
					System.out.println("User-Error"+e);
					getBlastView().refresh();
				}
			}
		});

		this.addComponent(title);
		this.addComponent(blastBar);
		this.addComponent(seq);
		this.addComponent(blast);

	}
	public void refresh(){
		getBlastView().removeAllComponents();
		this.addComponent(title);
		this.addComponent(blastBar);
		this.addComponent(seq);
		this.addComponent(blast);
	}
	public BlastView getBlastView(){
		return this;
	}
}

