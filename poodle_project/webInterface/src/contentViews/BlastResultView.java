package contentViews;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.user.client.Window;
import com.poodledb.PoodledbUI;
import com.vaadin.client.UIDL.XML;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.server.BrowserWindowOpener;
import com.vaadin.server.FileResource;
import com.vaadin.server.Resource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.client.ui.window.*;

public class BlastResultView extends BasicView{


	private String resultPrimer;
	private String resultVector;
	private String resultProtein;
	private String resultBlast;

	private Label title = new Label("Result of the BLAST");

	public BlastResultView(String[] blastResult, PoodledbUI poodleUI){

		this.setResultPrimer(blastResult[0]);
		this.setResultVector(blastResult[1]);
		this.setResultProtein(blastResult[2]);
		this.setBlastResult(blastResult[3]);
		
		String basepath= VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
		String qfPath = "/WEB-INF/tmp/";

		File page = new File(basepath+qfPath+"BlastResult.html");
		try {
			FileUtils.writeStringToFile(page, "", false);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(blastResult[0]!=null){
			try {
				FileUtils.writeStringToFile(page, resultPrimer, true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(blastResult[1]!=null){
			try {
				FileUtils.writeStringToFile(page, resultVector, true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(blastResult[2]!=null){
			try {
				FileUtils.writeStringToFile(page, resultProtein, true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(blastResult[3]!=null){
			try {
				FileUtils.writeStringToFile(page, resultBlast, true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		title = new Label("Result of the BLAST");
		title = new Label(resultPrimer+this.resultProtein+this.resultVector+this.resultBlast);
		poodleUI.getPage().open("file:///"+basepath+qfPath+"BlastResult.html", "_parent");
		
//		System.out.println(resultPrimer);
//		JsonParser jsonParser = new JsonParser();
//		JsonObject ar = jsonParser.parse(resultPrimer).getAsJsonObject();
//		System.out.println(ar.toString());
//		System.out.println("yourResult: "+ar.isJsonArray()+"or"+ar.isJsonObject()+"or"+ar.isJsonPrimitive()+" blabla"+ar.entrySet());
//
//		

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
		return resultBlast;
	}

	public void setBlastResult(String blastResult) {
		this.resultBlast = blastResult;
	}
}
