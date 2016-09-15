package com.mvLab.lab.account.controllers;

import com.mvLab.lab.account.WindowManager;
import com.mvLab.lab.account.catalogs.Catalog;
import com.mvLab.lab.account.documents.Document;
import com.mvLab.lab.account.windows.Header;
import com.mvLab.lab.account.windows.MV_Window;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ToolBar;

import java.util.HashMap;

public class MainController implements EventHandler {
    @FXML ToolBar innerWindowTB;
    @FXML Button reagentButton;
    private final int LINK_TEXT_LENGTH = 15;

    public void addWindowLink(Header element) {
        String linkText = element.getHeader();
        Hyperlink windowLink = new Hyperlink(linkText.length() > LINK_TEXT_LENGTH ? linkText.substring(0, LINK_TEXT_LENGTH) + "..." : linkText);
        windowLink.setId("windowLink" + WindowManager.getInstance().getInnerWindowsCount());
        windowLink.setUserData(element);
        windowLink.setOnMouseClicked(this);

        innerWindowTB.getItems().add(windowLink);
    }

    public void addWindowLink(Class elementClass, String linkText) {
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

    public void reagentButtonOnClick()  {
            WindowManager.getInstance().openReagentCatalogListForm();
    }

    public void updateWindowLink(Header element) {
        Node editNode = null;
        for (Node link : innerWindowTB.getItems()) {
            if (link.getUserData().equals(element.getClass()))
                editNode = link;
        }

        if (editNode != null) {
            String linkText = element.getHeader();
            linkText = linkText.length() > LINK_TEXT_LENGTH ? linkText.substring(0, LINK_TEXT_LENGTH) + "..." : linkText;
            ((Hyperlink) editNode).setText(linkText);
            editNode.setUserData(element);

            HashMap<Object, MV_Window> innerWindowsMap = WindowManager.getInstance().getInnerWindowsMap();
            MV_Window elementWindow = innerWindowsMap.get(element.getClass());
            if (elementWindow == null) {
                WindowManager.openErrorWindow("Error with link data!");
                return;
            }
            innerWindowsMap.remove(element.getClass());
            innerWindowsMap.put(element, elementWindow);
            elementWindow.setNewElement(false);

        }
    }

    public void admissionButtonOnClicked()  {
        WindowManager.getInstance().openReagentAdmissionForm();
    }

    public void menuOnExitClick() {
        System.exit(0);
    }

    @Override
    public void handle(Event event) {
        if (event.getSource() instanceof Hyperlink) {
            Hyperlink link = (Hyperlink) event.getSource();
            WindowManager.getInstance().showInnerWindow(link.getUserData());
        }
    }
}
