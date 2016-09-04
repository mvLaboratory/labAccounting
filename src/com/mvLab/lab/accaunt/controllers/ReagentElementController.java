package com.mvLab.lab.accaunt.controllers;

import com.mvLab.lab.accaunt.WindowManager;
import com.mvLab.lab.accaunt.catalogs.Reagents.ReagentCatalogElementForm;
import com.mvLab.lab.accaunt.windows.InternalWindow;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import javax.print.attribute.standard.MediaSize;

public class ReagentElementController {
    @FXML TextField ID;
    @FXML TextField Name;
    @FXML TextArea Description;
    @FXML Label windowHeaderLbl;
    @FXML Button windowCloseButton;
    @FXML BorderPane windowHeader;

    private ReagentCatalogElementForm form;
        //implements EventHandler<ActionEvent> {

    @FXML
    private void initialize() {

    }

    public void setFields() {
        Integer ellID = form.getCatalogElement().getId();
        ID.setText("" + (ellID == null || ellID == 0 ? "" : ellID));
        Name.setText("" + form.getCatalogElement().getName());
        Description.setText("" + form.getCatalogElement().getDescription());
    }

    public void customizeWindow(InternalWindow internalWindow) {
        windowCloseButton.setText("X");
        //windowHeaderLbl.setText(form.getCatalogElement().getHeader());
        setHeader();

        internalWindow.makeDragable(windowHeader);
        internalWindow.makeDragable(windowHeaderLbl);
    }

    public void setHeader() {
        windowHeaderLbl.setText(form.getCatalogElement().getHeader());
    }

    public void save() {
        form.getCatalogElement().setName(Name.getText());
        form.getCatalogElement().setDescription(Description.getText());
        form.save();
    }

    @FXML
    public void closeButtonOnClicked (Event event) {
       form.closeWindow();
    }

    @FXML
    public void windowCloseButtonOnClicked (Event event) {
        form.closeWindow();
    }

    @FXML
    public void windowHideButtonOnClicked  (Event event) {
        form.hide();
    }

    @FXML
    public void saveButtonOnClicked (Event event) {
        save();
    }

    @FXML
    public void okButtonOnClick (Event event) {
        save();
        form.closeWindow();
    }

    public void setForm(ReagentCatalogElementForm form) {
        this.form = form;
    }

//        if (event.getSource() instanceof Button) {
//            Button source = (Button) event.getSource();
//            String buttonID = source.idProperty().getValue();
//            if (buttonID.equals("close")) {
//                System.out.println("sss");
//            //    close();
//            }
//            else if (buttonID.equals("ok")) {
////                saveCatalogElement();
////                close();
//            }
//            else if (buttonID.equals("save")) {
////                saveCatalogElement();
////                catalogElement.readElement();
////                fillElements(catalogElement.getElementFields());
//            }
//        }
 //   }

}
