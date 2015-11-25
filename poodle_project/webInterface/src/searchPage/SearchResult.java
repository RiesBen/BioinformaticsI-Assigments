package searchPage;

import com.poodledb.NavigationBar;
import com.vaadin.ui.*;

import SearchForm.SearchBar;

public class SearchResult extends VerticalLayout{
	
	GridLayout grid = new GridLayout(3,1);
	
	MenuBar navigationBar = new NavigationBar();
	HorizontalLayout searchBar = new SearchBar();
	
	public SearchResult(){
		grid.addComponent(navigationBar, 0, 0);
		grid.addComponent(searchBar, 1, 0);
		
		
	}
	
}
