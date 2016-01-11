package contentViews;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.google.gwt.json.client.*;

public class BlastResultView extends BasicView{

	private String resultPrimer;
	private String resultVector;
	private String resultProtein;
	private String blastResult;
	
	private Label title = new Label("Result of the BLAST");
	
	public BlastResultView(String[] blastResult){

		this.setResultPrimer(blastResult[0]);
		this.setResultVector(blastResult[1]);
		this.setResultProtein(blastResult[2]);
		this.setBlastResult(blastResult[3]);
		
		title = new Label("Result of the BLAST");
		
		JSONArray result = new JSONArray();
		
		this.setSizeFull();
		this.addComponent(title);	
	}
	
	
	
	

	public String getResultPrimer() {
		return resultPrimer;
	}

	public void setResultPrimer(String resultPrimer) {
		this.resultPrimer = resultPrimer;
	}

	public String getResultVector() {
		return resultVector;
	}

	public void setResultVector(String resultVector) {
		this.resultVector = resultVector;
	}

	public String getResultProtein() {
		return resultProtein;
	}

	public void setResultProtein(String resultProtein) {
		this.resultProtein = resultProtein;
	}

	public String getBlastResult() {
		return blastResult;
	}

	public void setBlastResult(String blastResult) {
		this.blastResult = blastResult;
	}
}
