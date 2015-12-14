package pageElements;

import com.vaadin.annotations.Theme;
import com.vaadin.server.BrowserWindowOpener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Label;
import com.vaadin.ui.*;

@Theme("valo")
public class popup extends UI {
    @Override
    protected void init(VaadinRequest request) {
        getPage().setTitle("Popup Window");
        
        // Have some content for it
        VerticalLayout content = new VerticalLayout();
        Label label =
           new Label("I just popped up to say hi!");
        label.setSizeUndefined();
        content.addComponent(label);
        content.setComponentAlignment(label,
        		Alignment.MIDDLE_CENTER);
        content.setSizeFull();
        setContent(content);
	    }
	}

//BrowserWindowOpener opener = new BrowserWindowOpener(popup.class);
//opener.setWindowName("_blank");
//opener.setFeatures("height=200,width=300,resizable");
//opener.extend(searchButton);