package guis;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

import SearchForm.GeneralSearchParameter;

public class BlastView extends VerticalLayout {
	Label title = new Label("You want to BLAST it?");
	HorizontalLayout serverTableBar = new HorizontalLayout();
	TextField seq = new TextField("Sequence");
	Button blast;
	public BlastView(){
		
		//Layout
		this.setDefaultComponentAlignment(Alignment.TOP_CENTER);
		this.setDefaultComponentAlignment(Alignment.TOP_CENTER);
		this.setSpacing(true);
		this.setMargin(true);

		//Textfield:
		seq.setInputPrompt("Here Sequence");
		seq.setCursorPosition(0);
		seq.setSizeFull();
		
		//Blast Button 
				//initialize
				blast = new Button("Blast");
				blast.addStyleName("searchBlast");
				//function:
				blast.addClickListener(new Button.ClickListener() {
					public void buttonClick(ClickEvent event) {
						Label working = new Label ("still working on");
						addComponent(working);
					}
				});

		addComponent(title);
		addComponent(seq);
		addComponent(blast);

	}
}

