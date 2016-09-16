package com.mvLab.lab.account.controllers;

import com.mvLab.lab.account.catalogs.reagents.ReagentCatalog;
import com.mvLab.lab.account.catalogs.reagents.ReagentCatalogElementForm;
import com.mvLab.lab.account.documents.reagentAdmission.ReagentAdmission;
import com.mvLab.lab.account.documents.reagentAdmission.ReagentAdmissionElementForm;
import com.mvLab.lab.account.documents.reagentAdmission.ReagentAdmissionTablePartRow;
import com.mvLab.lab.account.utils.ControlTableCell;
import com.mvLab.lab.account.windows.InternalWindow;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

public class ReagentAdmissionDocumentController implements EventHandler<MouseEvent>, Callback<TableView<ReagentAdmissionTablePartRow>, TableRow<ReagentAdmissionTablePartRow>> {
    @FXML TextField number;
    @FXML DatePicker date;
    @FXML TableView<ReagentAdmissionTablePartRow> reagentTable;
    @FXML TableColumn quantity;
    @FXML TableColumn price;
    @FXML TableColumn sum;
    @FXML TableColumn reagent;

    @FXML Label windowHeaderLbl;
    @FXML Button windowCloseButton;
    @FXML BorderPane windowHeader;

    private ReagentAdmissionElementForm form;
    //public ReagentAdmissionTablePartRow editedRow;

    @FXML
    private void initialize() {

    }

    public void setFields() {
        if (date.getValue() == null) {
            date.setValue(LocalDate.now());
        }

        number.setText("" + form.getDocument().getNumber());

//        form.getDocument().getRowSet().add(new ReagentAdmissionTablePartRow(1, form.getDocument(), 1, new ReagentCatalog(2, "reagent", ""), 3, 4, 5));
        for (ReagentAdmissionTablePartRow tableRow : form.getDocument().getRowSet()) {
            reagentTable.getItems().add(tableRow);
        }
        reagentTable.setEditable(true);

        quantity.setCellValueFactory(new PropertyValueFactory<ReagentAdmissionTablePartRow, Double>("quantity"));
        quantity.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        quantity.setOnEditCommit(
                new EventHandler<CellEditEvent<ReagentAdmissionTablePartRow, Double>>() {
                    @Override
                    public void handle(CellEditEvent<ReagentAdmissionTablePartRow, Double> t) {
                       ( t.getTableView().getItems().get(t.getTablePosition().getRow())
                        ).setQuantity(t.getNewValue());
                    }
                }
        );

        sum.setCellValueFactory(new PropertyValueFactory<ReagentAdmissionTablePartRow, Double>("sum"));
        sum.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        sum.setOnEditCommit(
                new EventHandler<CellEditEvent<ReagentAdmissionTablePartRow, Double>>() {
                    @Override
                    public void handle(CellEditEvent<ReagentAdmissionTablePartRow, Double> t) {
                        ( t.getTableView().getItems().get(t.getTablePosition().getRow())
                        ).setSum(t.getNewValue());
                    }
                }
        );

        price.setCellValueFactory(new PropertyValueFactory<ReagentAdmissionTablePartRow, Double>("price"));
        price.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        price.setOnEditCommit(
                new EventHandler<CellEditEvent<ReagentAdmissionTablePartRow, Double>>() {
                    @Override
                    public void handle(CellEditEvent<ReagentAdmissionTablePartRow, Double> t) {
                        ( t.getTableView().getItems().get(t.getTablePosition().getRow())
                        ).setPrice(t.getNewValue());
                    }
                }
        );

        ReagentAdmissionDocumentController thisController = this;
        reagent.setCellValueFactory(new PropertyValueFactory<ReagentAdmissionTablePartRow, ReagentCatalog>("reagent"));
        reagent.setCellFactory( new Callback<TableColumn<String, String>, TableCell<String, String>>() {
            @Override
            public TableCell<String, String> call(TableColumn<String, String> arg0) {
                return new ControlTableCell<String, String>(thisController, true);
            }
        });
        reagent.setOnEditCommit(
                new EventHandler<CellEditEvent<ReagentAdmissionTablePartRow, ReagentCatalog>>() {
                    @Override
                    public void handle(CellEditEvent<ReagentAdmissionTablePartRow, ReagentCatalog> t) {
                        ( t.getTableView().getItems().get(t.getTablePosition().getRow())
                        ).setReagent(t.getNewValue());
                    }
                }
        );
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
        ReagentAdmissionTablePartRow newRowElement = new ReagentAdmissionTablePartRow();
        newRowElement.setRowNumber((form.getDocument().getRowSet().size() + 1));
        form.getDocument().getRowSet().add(newRowElement);
        reagentTable.getItems().add(newRowElement);
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
//        if (event.getClickCount() == 2) {
//            editedRow = ((TableRow<ReagentAdmissionTablePartRow>) event.getSource()).getItem();
//        }
//        else {
//            System.out.println("2");
//        }
    }

    @Override
    public TableRow call(TableView param) {
        TableRow<ReagentAdmissionTablePartRow> row = new TableRow<>();
        row.setOnMouseClicked(this);
        return row;
    }

}
