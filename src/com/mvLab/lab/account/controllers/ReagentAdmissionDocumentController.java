package com.mvLab.lab.account.controllers;

import com.mvLab.lab.account.catalogs.reagents.ReagentCatalog;
import com.mvLab.lab.account.catalogs.reagents.ReagentCatalogElementForm;
import com.mvLab.lab.account.documents.reagentAdmission.ReagentAdmission;
import com.mvLab.lab.account.documents.reagentAdmission.ReagentAdmissionElementForm;
import com.mvLab.lab.account.documents.reagentAdmission.ReagentAdmissionTablePartRow;
import com.mvLab.lab.account.windows.InternalWindow;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.sql.Date;
import java.time.ZoneId;

public class ReagentAdmissionDocumentController {
    @FXML TextField number;
    @FXML DatePicker date;
    @FXML TableView reagentTable;

    @FXML Label windowHeaderLbl;
    @FXML Button windowCloseButton;
    @FXML BorderPane windowHeader;

    private ReagentAdmissionElementForm form;

    @FXML
    private void initialize() {

    }

    public void setFields() {
//        Integer ellID = form.getCatalogElement().getId();
//        ID.setText("" + (ellID == null || ellID == 0 ? "" : ellID));
//        Name.setText("" + form.getCatalogElement().getName());
//        Description.setText("" + form.getCatalogElement().getDescription());
//        Precursor.setSelected(form.getCatalogElement().isPrecursor());
    }

    public void customizeWindow(InternalWindow internalWindow) {
        windowCloseButton.setText("X");
        //windowHeaderLbl.setText(form.getCatalogElement().getHeader());
        setHeader();

        internalWindow.makeDragable(windowHeader);
        internalWindow.makeDragable(windowHeaderLbl);

        //reagentTable.getItems().add(new ReagentAdmissionTablePartRow());
    }

    public void setHeader() {
        windowHeaderLbl.setText(form.getDocument().getHeader());
    }

    public void save() {
        form.getDocument().setDate(Date.from(date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
//        reagentTable.getItems()
//        form.getCatalogElement().setDescription(Description.getText());
//        form.getCatalogElement().setPrecursor(Precursor.isSelected());
//        form.save();
    }

    @FXML
    public void newRowButtonOnClick(Event event) {
//        ObservableList lst = reagentTable.getItems();
//        lst.add(new ReagentAdmissionTablePartRow(1, new ReagentAdmission(), 1, new ReagentCatalog(), 1, 1, 1));
//        reagentTable.setItems(lst);
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

    public void setForm(ReagentAdmissionElementForm form) {
        this.form = form;
    }


}
