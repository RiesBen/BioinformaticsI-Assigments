package pageElements;

import java.io.File;

import com.poodledb.PoodledbUI;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.MenuBar;

import contentViews.SearchView;

public class NavigationBar extends MenuBar{
	private PoodledbUI poodledb;
	private MenuItem search;
	private MenuItem newEntry;
	private MenuItem blast;
	
	//new Entry icon
	//Integration of icon
	String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();//Change in the END!!!
	FileResource resource1 = new FileResource(new File(basepath+"/WEB-INF/img/plus_colored-small.png"));
	
	
	//Commands for Navigation Bar
	MenuBar.Command searchCommand = new MenuBar.Command() {
	    public void menuSelected(MenuItem selectedItem) {
	    	if(poodledb.getViewIsReduced()){
	    		poodledb.getSearchView().refreshParameters();
	    	}
	    	poodledb.SetContentBox(poodledb.getSearchView());
	    	poodledb.modifyLayoutToLargeView();
	    	poodledb.getServerTable().setForSearchView();
	    }
	};
	
	MenuBar.Command newEntryCommand = new MenuBar.Command() {
	    public void menuSelected(MenuItem selectedItem) {
	    	poodledb.SetContentBox(poodledb.getNewEntryView());
	    	poodledb.modifyLayoutToLargeView();
	    	poodledb.getServerTable().setForNewEntryView();
	    }
	};
	
	MenuBar.Command blastCommand = new MenuBar.Command() {
	    public void menuSelected(MenuItem selectedItem) {
	    	poodledb.SetContentBox(poodledb.getBlastView());
	    	poodledb.modifyLayoutToLargeView();
	    	poodledb.getServerTable().setForBlastView();
    	}
	};
	
	public NavigationBar(PoodledbUI poodledb){		
		// Define a common menu command for all the menu items.
		this.addStyleName("valo-menu");
		this.poodledb=poodledb;
		
		//link commands to buttons
		search = this.addItem("Search", searchCommand);
		newEntry = this.addItem("New Entry", resource1, newEntryCommand);
		blast = this.addItem("BLAST", blastCommand);
	}
}
