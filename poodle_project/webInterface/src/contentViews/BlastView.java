package contentViews;

import com.poodledb.PoodledbUI;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import BlastClasses.BlastSearch;

import com.vaadin.ui.Button.ClickEvent;

import Forms.BlastSeqField;
import Forms.GeneralSearchParameter;
import PackageCommunicators.BlastCommunicator;
import pageElements.BlastBar;

public class BlastView extends VerticalLayout {
	private Label title = new Label("You want to BLAST it?");
	private BlastBar blastBar;
	private BlastSeqField seq;
	private Button blast;
	private PoodledbUI poodleUI;

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
					Label working = new Label ("still working on");
					BlastCommunicator blast = new BlastCommunicator(blastBar.getMode(),blastBar.getProgram(), querry, blastBar.getDB(), blastBar.getDBTable(), blastBar.getQuerry());
					addComponent(working);
					try{
						
					}catch(Exception e){
						System.out.println("System-Error"+e);
					}
				}catch(Exception e){
					System.out.println("User-Error"+e);
				}
			}
		});

		addComponent(title);
		addComponent(blastBar);
		addComponent(seq);
		addComponent(blast);

	}
}

