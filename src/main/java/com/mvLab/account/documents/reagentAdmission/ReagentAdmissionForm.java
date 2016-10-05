package com.mvLab.account.documents.reagentAdmission;

import com.mvLab.account.Main;
import com.mvLab.account.WindowManager;
import com.mvLab.account.controllers.documents.admission.ReagentAdmissionController;
import com.mvLab.account.windows.MV_Window;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;


import java.io.IOException;

public class ReagentAdmissionForm extends MV_Window {
    private Tab formTab;
    private BorderPane rootLayout;
    private SingleSelectionModel tabSelectionModel;
    private TableView<ReagentAdmission> reagentTableView;
    private ReagentAdmissionController admissionController;

    public ReagentAdmissionForm(BorderPane rootLayout) {
        this.rootLayout = rootLayout;
    }

    public void display() throws IOException {
        formTab = new Tab("Admission");
        formTab.setOnClosed(event -> {
            WindowManager.getInstance().closeReagentAdmissionForm();
        });
        BorderPane tabView = FXMLLoader.load(Main.class.getResource("/view/documents/admission/ReagentAdmissionForm.fxml"));

        reagentTableView = (TableView) tabView.getCenter();
        admissionController = new ReagentAdmissionController<>();
        admissionController.setTable(reagentTableView);
        reagentTableView.setRowFactory(admissionController);
        reagentTableView.setItems(ReagentAdmission.getDocumentData());

        for (TableColumn coll : reagentTableView.getColumns()) {
            String collName = coll.getText();
            if (collName.equals("V")) {
                coll.setCellValueFactory(cellValue -> new SimpleBooleanProperty(((ReagentAdmission)((TableColumn.CellDataFeatures)cellValue).getValue()).isPosted()));

                coll.setCellFactory(new Callback<TableColumn<ReagentAdmission, Boolean>, TableCell<ReagentAdmission, Boolean>>() {
                    @Override
                    public TableCell<ReagentAdmission, Boolean> call(TableColumn<ReagentAdmission, Boolean> tableColumn) {
                        return new CheckBoxTableCell<ReagentAdmission, Boolean>();
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

        reagentTableView.setItems(ReagentAdmission.getDocumentData());
        reagentTableView.refresh();
    }

    public void selectRow(ReagentAdmission document, boolean scrollToRow) {
        if  (admissionController == null) {
            WindowManager.openErrorWindow("Catalog for is not initialized!");
            return;
        }
        admissionController.selectRow(document, scrollToRow);
    }
}
