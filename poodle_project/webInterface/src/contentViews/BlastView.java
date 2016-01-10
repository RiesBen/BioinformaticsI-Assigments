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
import com.vaadin.ui.Button.ClickEvent;

import Forms.BlastSeqField;
import Forms.GeneralSearchParameter;

public class BlastView extends VerticalLayout {
	private Label title = new Label("You want to BLAST it?");
	//	private HorizontalLayout serverTableBar = new HorizontalLayout();
	private BlastSeqField seq;
	private Button blast;
	private PoodledbUI poodleUI;

	public BlastView(PoodledbUI pooldeUI){
		//initialization
		this.poodleUI = poodleUI;
		seq = new BlastSeqField();
		
		//Layout
		this.setDefaultComponentAlignment(Alignment.TOP_CENTER);
		this.setSpacing(true);
		this.setMargin(true);

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
		addComponent(seq);
		addComponent(blast);

	}
}

