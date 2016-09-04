package com.mvLab.lab.accaunt.controllers;

import com.mvLab.lab.accaunt.WindowManager;
import com.mvLab.lab.accaunt.catalogs.Catalog;
import com.mvLab.lab.accaunt.catalogs.Reagents.ReagentCatalog;
import com.mvLab.lab.accaunt.windows.MV_Window;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ToolBar;

import java.util.HashMap;
import java.util.Map;

public class MainController implements EventHandler {
    @FXML ToolBar innerWindowTB;
    @FXML Button reagentButton;

    public void addCatalogWindowLink(Catalog catalogElement) {
        final int LINK_TEXT_LENGTH = 17;
        String linkText = catalogElement.getHeader();
        Hyperlink windowLink = new Hyperlink(linkText.length() > LINK_TEXT_LENGTH ? linkText.substring(0, LINK_TEXT_LENGTH) + "..." : linkText);
        windowLink.setId("windowLink" + WindowManager.getInstance().getInnerWindowsCount());
        windowLink.setUserData(catalogElement);
        windowLink.setOnMouseClicked(this);

        innerWindowTB.getItems().add(windowLink);
    }

    public void addCatalogWindowLink(Class elementClass, String linkText) {
        Hyperlink windowLink = new Hyperlink(linkText);
        windowLink.setId("newWindowLink");
        windowLink.setUserData(elementClass);
        windowLink.setOnMouseClicked(this);

        innerWindowTB.getItems().add(windowLink);
    }

    public void removeCatalogWindowLink(Object catalogElement) {
        Node delNode = null;
        for (Node link : innerWindowTB.getItems()) {
            if (link.getUserData().equals(catalogElement))
                delNode = link;
        }

        if (delNode != null)
            innerWindowTB.getItems().remove(delNode);
    }

    public void updateCatalogWindowLinks() {
        final int LINK_TEXT_LENGTH = 17;
        innerWindowTB.getItems().remove(0, innerWindowTB.getItems().size());

        HashMap<Object, MV_Window> innerWindowsMap = WindowManager.getInstance().getInnerWindowsMap();
        for (Map.Entry<Object, MV_Window> elements : innerWindowsMap.entrySet()) {
            Catalog element = (Catalog) elements.getKey();
//            if (element instanceof Class)
//                String linkText = element.getHeader();
//                linkText = linkText.length() > LINK_TEXT_LENGTH ? linkText.substring(0, LINK_TEXT_LENGTH) + "..." : linkText;

            //Hyperlink windowLink = new Hyperlink(linkText);
//            windowLink.setId("windowLink" + WindowManager.getInstance().getInnerWindowsCount());
//            windowLink.setUserData(element);
//            windowLink.setOnMouseClicked(this);
//
//            innerWindowTB.getItems().add(windowLink);
        }
    }

    public void reagentButtonOnClick()  {
            WindowManager.getInstance().openReagentCatalogListForm();
    }

    public void menuOnExitClick() {
        System.exit(0);
    }

    @Override
    public void handle(Event event) {
        if (event.getSource() instanceof Hyperlink) {
            Hyperlink link = (Hyperlink) event.getSource();
            WindowManager.getInstance().showInnerWindow(link.getUserData());
//            WindowManager.getInstance().getInnerWindowsFromTB(data);
//
//            String linkID = link.getId();
//            int linkIndex = Integer.parseInt(linkID.substring(10));
//
//            WindowManager.getInstance().showInnerWindow(linkIndex - 1);
        }
    }
}
