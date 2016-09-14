package com.mvLab.lab.account.documents.reagentAdmission;

import com.mvLab.lab.account.Main;
import com.mvLab.lab.account.WindowManager;
import com.mvLab.lab.account.controllers.ReagentAdmissionController;
import com.mvLab.lab.account.windows.MV_Window;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;


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
        BorderPane tabView = FXMLLoader.load(Main.class.getResource("/view/ReagentAdmissionForm.fxml"));

        reagentTableView = (TableView) tabView.getCenter();
        admissionController = new ReagentAdmissionController<>();
        admissionController.setTable(reagentTableView);
        reagentTableView.setRowFactory(admissionController);
        reagentTableView.setItems(ReagentAdmission.getDocumentData());

        for (TableColumn coll : reagentTableView.getColumns()) {
            String collName = coll.getText();
            coll.setCellValueFactory(new PropertyValueFactory(collName.toLowerCase()));
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
