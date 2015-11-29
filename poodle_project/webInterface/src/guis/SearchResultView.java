package guis;

import com.poodledb.NavigationBar;
import com.vaadin.annotations.Push;
import com.vaadin.ui.*;

import SearchForm.SearchBar;

@Push
public class SearchResultView extends VerticalLayout{
	VerticalLayout root = new VerticalLayout();
	GridLayout grid = new GridLayout(3,1);
	SearchBar searchBar = new SearchBar();
	
	public SearchResultView(){
		grid.addComponent(searchBar.getLayout(), 1, 0);
		
		root.addComponent(grid);
	}

	public VerticalLayout getLayout() {
		return root;
	}
	
}
