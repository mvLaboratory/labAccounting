package com.mvLab.account.controllers.documents.admission;

import com.mvLab.account.catalogs.reagents.ReagentCatalog;
import com.mvLab.account.documents.reagentAdmission.ReagentAdmissionElementForm;
import com.mvLab.account.documents.reagentAdmission.ReagentAdmissionTablePartRow;
import com.mvLab.account.windows.InternalWindow;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.ComboBoxTableCell;
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
    @FXML TextField supplier;
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
        if (form.getDocument().getDate() != null)
            date.setValue(form.getDocument().getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

        if (date.getValue() == null) {
            date.setValue(LocalDate.now());
        }

        if  (form.getDocument() != null && form.getDocument().getNumber() != null) {
            number.setText("" + form.getDocument().getNumber());
        }

        supplier.setText(form.getDocument().getSupplier());

//        form.getDocument().getRowSet().add(new ReagentAdmissionTablePartRow(1, form.getDocument(), 1, new ReagentCatalog(2, "reagent", ""), 3, 4, 5));
//        reagentTable.getItems().clear();
//        for (ReagentAdmissionTablePartRow tableRow : form.getDocument().getRowSet()) {
//            reagentTable.getItems().add(tableRow);
//        }
        updateRows();
        reagentTable.setEditable(true);

        ReagentAdmissionDocumentController thisController = this;

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



        reagent.setEditable(true);
        reagent.setCellValueFactory(new PropertyValueFactory<ReagentAdmissionTablePartRow, ReagentCatalog>("reagent"));
        reagent.setCellFactory(ComboBoxTableCell.forTableColumn(ReagentCatalog.getCatalogData()));
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
        form.getDocument().setSupplier(supplier.getText());
//        reagentTable.getItems()
//        form.getCatalogElement().setDescription(Description.getText());
//        form.getCatalogElement().setPrecursor(Precursor.isSelected());
        form.save();
    }

    public void post() {
        form.getDocument().setPosted(true);
        form.getDocument().post();
        save();
    }

    @FXML
    public void newRowButtonOnClick(Event event) {
        ReagentAdmissionTablePartRow newRowElement = new ReagentAdmissionTablePartRow();
        newRowElement.setDocument(form.getDocument());
        newRowElement.setRowNumber((form.getDocument().getRowSet().size() + 1));
        form.getDocument().getRowSet().add(newRowElement);
        //reagentTable.getItems().add(newRowElement);
        updateRows();
    }

    @FXML
    public void rowDelButtonOnClick(Event event) {
        ReagentAdmissionTablePartRow selectedRow = reagentTable.getSelectionModel().getSelectedItem();
        //reagentTable.getItems().remove(selectedRow);
        form.getDocument().getRowSet().remove(selectedRow);
        form.getDocument().getDelRowSet().add(selectedRow);
        updateRows();
    }

    public void updateRows() {
        reagentTable.getItems().clear();
        int rowNum = 0;
        for (ReagentAdmissionTablePartRow row : form.getDocument().getRowSet()) {
            row.setRowNumber(++rowNum);
            reagentTable.getItems().add(row);
        }
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
       // if (form.getDocument().isPosted()) {
            post();
        //}
        form.closeWindow();
    }

    @FXML
    public void postButtonOnClick (Event event) {
        save();
        post();
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
