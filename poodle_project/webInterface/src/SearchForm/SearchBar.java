package SearchForm;

import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;

import guis.SearchResultView;

public class SearchBar extends HorizontalLayout {

	GridLayout columns = new GridLayout(4,6);
	
//	SearchButton
	Button searchButton;
	
	//Search Parameter:
	//General
	   TextField parameter1   = new TextField("ID");
	   TextField parameter2   = new TextField("box");
	   TextField parameter3   = new TextField("position");
	   TextField parameter4   = new TextField("name");
	   TextField parameter5   = new TextField("protein");
	   TextField parameter6   = new TextField("domain");
	   TextField parameter7   = new TextField("author");
	   
	//Vectors
	   TextField parameter8   = new TextField("Tags");
	   TextField parameter9   = new TextField("Ant-Biotics");
	   
	   
	   public SearchBar(){
		   columns.addComponent(parameter1, 0, 0);
		   columns.addComponent(parameter2, 0, 1);
		   columns.addComponent(parameter3, 0, 2);
		   columns.addComponent(parameter4, 0, 3);
		   
		   columns.addComponent(parameter5, 1, 0);
		   columns.addComponent(parameter6, 1, 1);
		   columns.addComponent(parameter7, 1, 2);
		   
		   columns.addComponent(parameter8, 2, 0);
		   columns.addComponent(parameter9, 2, 1);
		   
			// Search Button 
			searchButton = new Button("Search");
			searchButton.addStyleName("searchButton");
			searchButton.addClickListener(new Button.ClickListener() {
				public void buttonClick(ClickEvent event) {
					Label na = new Label("Did it again!");
					columns.addComponent(na);
					}
				});
			columns.addComponent(searchButton, 2,4);
	   }
	   
	   public Layout getLayout(){
		   return columns;
	   }
	
}
