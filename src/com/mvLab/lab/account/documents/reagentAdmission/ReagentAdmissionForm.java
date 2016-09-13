package com.mvLab.lab.account.documents.reagentAdmission;

import com.mvLab.lab.account.Main;
import com.mvLab.lab.account.WindowManager;
import com.mvLab.lab.account.catalogs.reagents.ReagentCatalog;
import com.mvLab.lab.account.controllers.ReagentCatalogController;
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
    private TableView<ReagentCatalog> reagentTableView;
    private ReagentCatalogController catalogController;

    public ReagentAdmissionForm(BorderPane rootLayout) {
        this.rootLayout = rootLayout;
    }

    public void display() throws IOException {
        formTab = new Tab("Admission");
        formTab.setOnClosed(event -> {
            WindowManager.getInstance().closeReagentCatalogListForm();
        });
        BorderPane tabView = FXMLLoader.load(Main.class.getResource("/view/ReagentAdmissionDocumentForm.fxml"));

        reagentTableView = (TableView) tabView.getCenter();
        catalogController = new ReagentCatalogController<>();
        catalogController.setTable(reagentTableView);
        reagentTableView.setRowFactory(catalogController);
        reagentTableView.setItems(ReagentCatalog.getCatalogData());

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

        reagentTableView.setItems(ReagentCatalog.getCatalogData());
        reagentTableView.refresh();
    }

    public void selectRow(ReagentCatalog element, boolean scrollToRow) {
        if  (catalogController == null) {
            WindowManager.openErrorWindow("Catalog for is not initialized!");
            return;
        }
        catalogController.selectRow(element, scrollToRow);
    }
}
