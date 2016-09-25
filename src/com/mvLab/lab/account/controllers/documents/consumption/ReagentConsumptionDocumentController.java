package com.mvLab.lab.account.controllers.documents.consumption;

import com.mvLab.lab.account.catalogs.reagents.ReagentCatalog;
import com.mvLab.lab.account.documents.reagentAdmission.ReagentAdmissionElementForm;
import com.mvLab.lab.account.documents.reagentAdmission.ReagentAdmissionTablePartRow;
import com.mvLab.lab.account.documents.reagentConsumption.ReagentConsumption;
import com.mvLab.lab.account.documents.reagentConsumption.ReagentConsumptionDocumentForm;
import com.mvLab.lab.account.documents.reagentConsumption.ReagentConsumptionTablePartRow;
import com.mvLab.lab.account.windows.InternalWindow;
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

public class ReagentConsumptionDocumentController implements EventHandler<MouseEvent>, Callback<TableView<ReagentConsumptionTablePartRow>, TableRow<ReagentConsumptionTablePartRow>> {
    @FXML TextField number;
    @FXML DatePicker date;
    @FXML TextField supplier;
    @FXML TableView<ReagentConsumptionTablePartRow> reagentTable;
    @FXML TableColumn quantity;
    @FXML TableColumn price;
    @FXML TableColumn sum;
    @FXML TableColumn reagent;
    @FXML Label windowHeaderLbl;
    @FXML Button windowCloseButton;
    @FXML BorderPane windowHeader;

    private ReagentConsumptionDocumentForm form;

    @FXML
    private void initialize() {

    }

    public void setFields() {
        if (date.getValue() == null) {
            date.setValue(LocalDate.now());
        }

        if  (form.getDocument() != null && form.getDocument().getNumber() != null) {
            number.setText("" + form.getDocument().getNumber());
        }

        updateRows();
        reagentTable.setEditable(true);

        ReagentConsumptionDocumentController thisController = this;

        quantity.setCellValueFactory(new PropertyValueFactory<ReagentConsumptionTablePartRow, Double>("quantity"));
        quantity.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        quantity.setOnEditCommit(
                new EventHandler<CellEditEvent<ReagentConsumptionTablePartRow, Double>>() {
                    @Override
                    public void handle(CellEditEvent<ReagentConsumptionTablePartRow, Double> t) {
                        ( t.getTableView().getItems().get(t.getTablePosition().getRow())
                        ).setQuantity(t.getNewValue());
                    }
                }
        );

        sum.setCellValueFactory(new PropertyValueFactory<ReagentConsumptionTablePartRow, Double>("sum"));
        sum.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        sum.setOnEditCommit(
                new EventHandler<CellEditEvent<ReagentConsumptionTablePartRow, Double>>() {
                    @Override
                    public void handle(CellEditEvent<ReagentConsumptionTablePartRow, Double> t) {
                        ( t.getTableView().getItems().get(t.getTablePosition().getRow())
                        ).setSum(t.getNewValue());
                    }
                }
        );

       price.setCellValueFactory(new PropertyValueFactory<ReagentConsumptionTablePartRow, Double>("price"));
       price.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
       price.setOnEditCommit(
                new EventHandler<CellEditEvent<ReagentConsumptionTablePartRow, Double>>() {
                    @Override
                    public void handle(CellEditEvent<ReagentConsumptionTablePartRow, Double> t) {
                        ( t.getTableView().getItems().get(t.getTablePosition().getRow())
                        ).setPrice(t.getNewValue());
                    }
                }
        );

        reagent.setEditable(true);
        reagent.setCellValueFactory(new PropertyValueFactory<ReagentConsumptionTablePartRow, ReagentCatalog>("reagent"));
        reagent.setCellFactory(ComboBoxTableCell.forTableColumn(ReagentCatalog.getCatalogData()));
        reagent.setOnEditCommit(
                new EventHandler<CellEditEvent<ReagentConsumptionTablePartRow, ReagentCatalog>>() {
                    @Override
                    public void handle(CellEditEvent<ReagentConsumptionTablePartRow, ReagentCatalog> t) {
                        ( t.getTableView().getItems().get(t.getTablePosition().getRow())
                        ).setReagent(t.getNewValue());
                    }
                }
        );
    }

    public void customizeWindow(InternalWindow internalWindow) {
        windowCloseButton.setText("X");
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
        form.save();
    }

    public void post() {
        form.getDocument().setPosted(true);
        form.getDocument().post();
        save();
    }

    @FXML
    public void newRowButtonOnClick(Event event) {
        ReagentConsumptionTablePartRow newRowElement = new ReagentConsumptionTablePartRow();
        newRowElement.setDocument(form.getDocument());
        newRowElement.setRowNumber((form.getDocument().getRowSet().size() + 1));
        form.getDocument().getRowSet().add(newRowElement);
        updateRows();
    }

    @FXML
    public void rowDelButtonOnClick(Event event) {
        ReagentConsumptionTablePartRow selectedRow = reagentTable.getSelectionModel().getSelectedItem();
        form.getDocument().getRowSet().remove(selectedRow);
        form.getDocument().getDelRowSet().add(selectedRow);
        updateRows();
    }

    public void updateRows() {
        reagentTable.getItems().clear();
        int rowNum = 0;
        for (ReagentConsumptionTablePartRow row : form.getDocument().getRowSet()) {
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
        post();
        form.closeWindow();
    }

    @FXML
    public void postButtonOnClick (Event event) {
        save();
        post();
    }

    public void setForm(ReagentConsumptionDocumentForm form) {
        this.form = form;
    }


    @Override
    public void handle(MouseEvent event) {

    }

    @Override
    public TableRow call(TableView param) {
        TableRow<ReagentConsumptionTablePartRow> row = new TableRow<>();
        row.setOnMouseClicked(this);
        return row;
    }

}
