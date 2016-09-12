package com.mvLab.lab.accaunt.catalogs.Reagents;

import com.mvLab.lab.accaunt.Main;
import com.mvLab.lab.accaunt.WindowManager;
import com.mvLab.lab.accaunt.catalogs.Catalog;
import com.mvLab.lab.accaunt.catalogs.CatalogListForm;
import com.mvLab.lab.accaunt.controllers.ReagentCatalogController;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.io.ObjectInput;

public class ReagentCatalogListForm extends CatalogListForm {
    private TableView<ReagentCatalog> reagentTable;
    private Catalog presentRowData;
    private BorderPane rootLayout;
    private Tab formTab;
    private SingleSelectionModel tabSelectionModel;
    private TableView<ReagentCatalog> reagentTableView;
    private ReagentCatalogController catalogController;

    public ReagentCatalogListForm(BorderPane rootLayout) {
        this.rootLayout = rootLayout;
    }

    public void display() throws IOException {
        formTab = new Tab("Reagents");
        formTab.setOnClosed(event -> {
            WindowManager.getInstance().closeReagentCatalogListForm();
        });
        BorderPane tabView = FXMLLoader.load(Main.class.getResource("/view/ReagentCatalogForm.fxml"));

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

    //    public ReagentCatalogListForm(String title, int windowWidth, int windowHeight) {
//        super(title, windowWidth, windowHeight);
//    }



//    public static ObservableList<ReagentCatalog> getCatalogData() {
//        ObservableList<ReagentCatalog> catalogData = FXCollections.observableArrayList();
//        ArrayList<HashMap<String, Object>> catalogElements = DB_Manager.ReadReagentCatalog();
//        for (HashMap<String, Object> element : catalogElements) {
//            catalogData.add(new ReagentCatalog((Integer) element.get("id"), (String)element.get("name"), (String)element.get("description"), (String)element.get("uuid")));
//        }
//        return catalogData;
//    }

//    public static Catalog getPresentRowData() {
//        presentRowData = reagentTable.getSelectionModel().getSelectedItem();
//        return presentRowData;
//    }
}
