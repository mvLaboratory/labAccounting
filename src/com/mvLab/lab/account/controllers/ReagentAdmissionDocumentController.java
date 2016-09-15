package com.mvLab.lab.account.controllers;

import com.mvLab.lab.account.catalogs.reagents.ReagentCatalog;
import com.mvLab.lab.account.catalogs.reagents.ReagentCatalogElementForm;
import com.mvLab.lab.account.documents.reagentAdmission.ReagentAdmission;
import com.mvLab.lab.account.documents.reagentAdmission.ReagentAdmissionElementForm;
import com.mvLab.lab.account.documents.reagentAdmission.ReagentAdmissionTablePartRow;
import com.mvLab.lab.account.windows.InternalWindow;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

public class ReagentAdmissionDocumentController implements EventHandler<MouseEvent>, Callback<TableView<ReagentAdmissionTablePartRow>, TableRow<ReagentAdmissionTablePartRow>> {
    @FXML TextField number;
    @FXML DatePicker date;
    @FXML TableView<ReagentAdmissionTablePartRow> reagentTable;

    @FXML Label windowHeaderLbl;
    @FXML Button windowCloseButton;
    @FXML BorderPane windowHeader;

    private ReagentAdmissionElementForm form;

    @FXML
    private void initialize() {

    }

    public void setFields() {
        if (date.getValue() == null) {
            date.setValue(LocalDate.now());
        }

//        Integer ellID = form.getCatalogElement().getId();
//        ID.setText("" + (ellID == null || ellID == 0 ? "" : ellID));
//        Name.setText("" + form.getCatalogElement().getName());
//        Description.setText("" + form.getCatalogElement().getDescription());
//        Precursor.setSelected(form.getCatalogElement().isPrecursor());
//        form.getDocument().getRowSet().add(new ReagentAdmissionTablePartRow(1, form.getDocument(), 1, new ReagentCatalog(2, "reagent", ""), 3, 4, 5));
        for (ReagentAdmissionTablePartRow tableRow : form.getDocument().getRowSet()) {
            reagentTable.getItems().add(tableRow);
        }
    }

    public void customizeWindow(InternalWindow internalWindow) {
        windowCloseButton.setText("X");
        //windowHeaderLbl.setText(form.getCatalogElement().getHeader());
        setHeader();

        internalWindow.makeDragable(windowHeader);
        internalWindow.makeDragable(windowHeaderLbl);

        reagentTable.setRowFactory(this);
        for (TableColumn coll : reagentTable.getColumns()) {
            String collName = coll.getId();
            coll.setCellValueFactory(new PropertyValueFactory(collName));
        }
    }

    public void setHeader() {
        windowHeaderLbl.setText(form.getDocument().getHeader());
    }

    public void save() {
        form.getDocument().setDate(Date.from(date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
//        reagentTable.getItems()
//        form.getCatalogElement().setDescription(Description.getText());
//        form.getCatalogElement().setPrecursor(Precursor.isSelected());
        form.save();
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


    @Override
    public void handle(MouseEvent event) {
        if (event.getClickCount() == 2) {

        }
    }

    @Override
    public TableRow call(TableView param) {
        TableRow<ReagentAdmissionTablePartRow> row = new TableRow<>();
        row.setOnMouseClicked(this);
        return row;
    }
}
