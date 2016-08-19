package com.mvLab.lab.accaunt.catalogs.Reagents;

import com.mvLab.lab.accaunt.DB_Manager;
import com.mvLab.lab.accaunt.catalogs.Catalog;
import com.mvLab.lab.accaunt.catalogs.CatalogListForm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import java.util.ArrayList;
import java.util.HashMap;

public class ReagentCatalogListForm extends CatalogListForm {
    private static TableView<ReagentCatalog> reagentTable;
    private static Catalog presentRowData;

    private ReagentCatalogListForm() {

    }

    //    public ReagentCatalogListForm(String title, int windowWidth, int windowHeight) {
//        super(title, windowWidth, windowHeight);
//    }

    public void display() {

    }

    public static void update() {
        reagentTable.setItems(getCatalogData());
    }

    public static ObservableList<ReagentCatalog> getCatalogData() {
        ObservableList<ReagentCatalog> catalogData = FXCollections.observableArrayList();
        ArrayList<HashMap<String, Object>> catalogElements = DB_Manager.ReadReagentCatalog();
        for (HashMap<String, Object> element : catalogElements) {
            catalogData.add(new ReagentCatalog((Integer) element.get("id"), (String)element.get("name"), (String)element.get("description"), (String)element.get("uuid")));
        }
        return catalogData;
    }

//    public static Catalog getPresentRowData() {
//        presentRowData = reagentTable.getSelectionModel().getSelectedItem();
//        return presentRowData;
//    }
}
