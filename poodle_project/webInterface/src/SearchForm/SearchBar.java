package SearchForm;

import com.vaadin.ui.*;

public class SearchBar extends HorizontalLayout {

	GridLayout colums = new GridLayout(4,6);
	
	
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
		   colums.addComponent(parameter1, 0, 0);
		   colums.addComponent(parameter2, 0, 1);
		   colums.addComponent(parameter3, 0, 2);
		   colums.addComponent(parameter4, 0, 3);
		   
		   colums.addComponent(parameter5, 1, 0);
		   colums.addComponent(parameter6, 1, 1);
		   colums.addComponent(parameter7, 1, 2);
		   
		   colums.addComponent(parameter8, 2, 0);
		   colums.addComponent(parameter9, 2, 1);
	   }
	
}
