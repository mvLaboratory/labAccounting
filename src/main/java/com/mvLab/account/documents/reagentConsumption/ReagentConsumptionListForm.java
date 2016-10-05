package com.mvLab.account.documents.reagentConsumption;

import com.mvLab.account.Main;
import com.mvLab.account.WindowManager;
import com.mvLab.account.controllers.documents.consumption.ReagentConsumptionListController;
import com.mvLab.account.windows.MV_Window;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

import java.io.IOException;

public class ReagentConsumptionListForm extends MV_Window {
    private Tab formTab;
    private BorderPane rootLayout;
    private SingleSelectionModel tabSelectionModel;
    private TableView<ReagentConsumption> reagentTableView;
    private ReagentConsumptionListController consumptionController;

    public ReagentConsumptionListForm(BorderPane rootLayout) {
        this.rootLayout = rootLayout;
    }

    public void display() throws IOException {
        formTab = new Tab("Consumption");
        formTab.setOnClosed(event -> {
            WindowManager.getInstance().closeReagentConsumptionListForm();
        });
        BorderPane tabView = FXMLLoader.load(Main.class.getResource("/view/documents/consumption/ReagentConsumptionListForm.fxml"));

        reagentTableView = (TableView) tabView.getCenter();
        consumptionController = new ReagentConsumptionListController();
        consumptionController.setTable(reagentTableView);
        reagentTableView.setRowFactory(consumptionController);
        reagentTableView.setItems(ReagentConsumption.getDocumentData());

        for (TableColumn coll : reagentTableView.getColumns()) {
            String collName = coll.getText();
            if (collName.equals("V")) {
                coll.setCellValueFactory(cellValue -> new SimpleBooleanProperty(((ReagentConsumption)((TableColumn.CellDataFeatures)cellValue).getValue()).isPosted()));

                coll.setCellFactory(new Callback<TableColumn<ReagentConsumption, Boolean>, TableCell<ReagentConsumption, Boolean>>() {
                    @Override
                    public TableCell<ReagentConsumption, Boolean> call(TableColumn<ReagentConsumption, Boolean> tableColumn) {
                        return new CheckBoxTableCell<ReagentConsumption, Boolean>();
                    }
                });
            }
            else {
                coll.setCellValueFactory(new PropertyValueFactory(collName.toLowerCase()));
            }
        }


        formTab.setContent(tabView);

        TabPane centralPane = (TabPane) rootLayout.getCenter();
        centralPane.getTabs().add(formTab);
        tabSelectionModel = centralPane.getSelectionModel();
        tabSelectionModel.select(formTab);
    }

    public void activate() {
        tabSelectionModel.select(formTab);
    }

    public void update() {
        reagentTableView.setItems(ReagentConsumption.getDocumentData());
        reagentTableView.refresh();
    }

    public void selectRow(ReagentConsumption document, boolean scrollToRow) {
        if  (consumptionController == null) {
            WindowManager.openErrorWindow("Catalog for is not initialized!");
            return;
        }
        consumptionController.selectRow(document, scrollToRow);
    }
}
