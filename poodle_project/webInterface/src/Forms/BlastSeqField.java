package Forms;

import com.vaadin.server.Sizeable.Unit;
import com.vaadin.server.UserError;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.TextArea;

public class BlastSeqField extends BasicParameters{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TextArea seq = new TextArea("Sequence");

	public BlastSeqField(){

		//Layout:
		this.setDefaultComponentAlignment(Alignment.TOP_CENTER);
		this.setWidth((float) (this.getWidth()*0.75), Unit.PERCENTAGE);
		
		//Textfield Settings
		seq.setInputPrompt("Here Sequence");
		seq.setSizeFull();
		seq.setWordwrap(true);
		seq.setWidth((float) (this.getWidth()*0.75), Unit.PERCENTAGE);

		//adding to layer
		this.addComponent(seq);
	}


	public String evaluateSequence() throws Exception{
		if(seq.getValue()==""){
			seq.setComponentError(new UserError("This field is required!"));
			throw new Exception("value Missing");
		}
		else{
			return seq.getValue();
		}
	}

	//------------------------------------------------------------------------------------------------------------------------
	//Getter and Setter:
	//------------------------------------------------------------------------------------------------------------------------		

	public void setSeq(TextArea seq){
		this.seq=seq;
	}

	public TextArea getSeq(){
		return seq;
	}
}
