package com.mvLab.account.catalogs.reagents;

import com.mvLab.account.Main;
import com.mvLab.account.WindowManager;
import com.mvLab.account.catalogs.Catalog;
import com.mvLab.account.catalogs.CatalogListForm;
import com.mvLab.account.controllers.catalogs.ReagentCatalogController;
import com.mvLab.account.windows.InternalWindow;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

import java.io.IOException;

public class ReagentCatalogSelectionForm extends CatalogListForm {
    private TableView<ReagentCatalog> reagentTable;
    private Catalog presentRowData;
    private BorderPane rootLayout;
    private Tab formTab;
    private SingleSelectionModel tabSelectionModel;
    private TableView<ReagentCatalog> reagentTableView;
    private ReagentCatalogController catalogController;

    public ReagentCatalogSelectionForm(BorderPane rootLayout) {
        this.rootLayout = rootLayout;
    }

    public void display() throws IOException {
//        formTab = new Tab("Reagents");
//        formTab.setOnClosed(event -> {
//            WindowManager.getInstance().closeReagentCatalogListForm();
//        });
        BorderPane tabView = FXMLLoader.load(Main.class.getResource("/view/catalog/ReagentCatalogSelectionForm.fxml"));

        reagentTableView = (TableView) tabView.getCenter();
        catalogController = new ReagentCatalogController<>();
        catalogController.setTable(reagentTableView);
        reagentTableView.setRowFactory(catalogController);
        reagentTableView.setItems(ReagentCatalog.getCatalogData());

        for (TableColumn coll : reagentTableView.getColumns()) {
            String collName = coll.getText();

            if (collName.equals("Precursor")) {
                coll.setCellValueFactory(cellValue -> new SimpleBooleanProperty(((ReagentCatalog)((TableColumn.CellDataFeatures)cellValue).getValue()).isPrecursor()));

                coll.setCellFactory(new Callback<TableColumn<ReagentCatalog, Boolean>, TableCell<ReagentCatalog, Boolean>>() {
                    @Override
                    public TableCell<ReagentCatalog, Boolean> call(TableColumn<ReagentCatalog, Boolean> tableColumn) {
                        return new CheckBoxTableCell<ReagentCatalog, Boolean>();
                    }
                });

            }
            else {
                coll.setCellValueFactory(new PropertyValueFactory(collName.toLowerCase()));
            }
        }
//        formTab.setContent(tabView);
//
//        TabPane centralPane = (TabPane) rootLayout.getCenter();
//        centralPane.getTabs().add(formTab);
//        tabSelectionModel = centralPane.getSelectionModel();
//        tabSelectionModel.select(formTab);

        InternalWindow formWindow = InternalWindow.constructWindow(100, 100, tabView);

//        ReagentElementController controller = loader.getController();
//        elementController.setForm(this);
//        elementController.setFields();
//        elementController.customizeWindow(formWindow);

        WindowManager.getInstance().getMainWindow().getRootLayout().getChildren().add(formWindow);
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
