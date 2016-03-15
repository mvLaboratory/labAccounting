package com.mvLab.lab.accaunt.catalogs.Reagents;

import com.mvLab.lab.accaunt.DB_Manager;
import com.mvLab.lab.accaunt.catalogs.Catalog;
import com.mvLab.lab.accaunt.catalogs.CatalogListForm;
import com.mvLab.lab.accaunt.windows.WindowManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.HashMap;

public class ReagentCatalogListForm extends CatalogListForm {
    private static DB_Manager labDB;
    private static TableView<ReagentCatalog> reagentTable;
    private static Stage window = new Stage();

    public ReagentCatalogListForm(String title, int windowWidth, int windowHeight) {
        super(title, windowWidth, windowHeight);
        fillElements();
    }

    private void fillElements() {
        Button ButtonAdd = new Button("Add");
        ButtonAdd.setMinWidth(60);
        ButtonAdd.setId("AddReagent");
        addTopElement(ButtonAdd);
        ButtonAdd.setOnAction(new ReagentCatalogListFormActionHandler());

        Button ButtonEdit = new Button("Edit");
        ButtonEdit.setMinWidth(60);
        ButtonEdit.setId("EditReagent");
        addTopElement(ButtonEdit);
        ButtonEdit.setOnAction(new ReagentCatalogListFormActionHandler());

        TableColumn<ReagentCatalog, Integer> idColumn = new TableColumn<ReagentCatalog, Integer>("ID");
        idColumn.setMinWidth(50);
        idColumn.setCellValueFactory(new PropertyValueFactory<ReagentCatalog, Integer>("id"));

        TableColumn<ReagentCatalog, String> nameColumn = new TableColumn<ReagentCatalog, String>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<ReagentCatalog, String>("name"));

        TableColumn<ReagentCatalog, String> descColumn = new TableColumn<ReagentCatalog, String>("Description");
        descColumn.setMinWidth(400);
        descColumn.setCellValueFactory(new PropertyValueFactory<ReagentCatalog, String>("description"));

        TableColumn<ReagentCatalog, String> uuidColumn = new TableColumn<ReagentCatalog, String>("Description");
        uuidColumn.setVisible(false);
        uuidColumn.setMinWidth(200);
        uuidColumn.setCellValueFactory(new PropertyValueFactory<ReagentCatalog, String>("uuid"));

        reagentTable = new TableView<ReagentCatalog>();
        reagentTable.setItems(getCatalogData());
        reagentTable.getColumns().addAll(idColumn, nameColumn, descColumn, uuidColumn);

        reagentTable.setRowFactory(new ReagentCatalogDblClickListener());
        reagentTable.autosize();

        addCenterElement(reagentTable, 0, 0);
    }

//    public ReagentCatalogListForm(DB_Manager labDB) {
//        this.labDB = labDB;
//    }

    public static void display_() {
        BorderPane mainLayout = new BorderPane();

        //Command panel+++
        HBox commandPanel = new HBox();

        //ADD button
        Button ButtonAdd = new Button("Add");
        ButtonAdd.setMinWidth(60);
        ButtonAdd.setId("AddReagent");
        ButtonAdd.setOnAction(new ReagentCatalogListFormActionHandler());
        //--

        //Edit button
        Button ButtonEdit = new Button("Edit");
        ButtonEdit.setMinWidth(60);
        ButtonEdit.setId("EditReagent");
        ButtonEdit.setOnAction(new ReagentCatalogListFormActionHandler());
        //--

        commandPanel.getChildren().addAll(ButtonAdd, ButtonEdit);

        mainLayout.setTop(commandPanel);
        //---

        //Table++++
        TableColumn<ReagentCatalog, Integer> idColumn = new TableColumn<ReagentCatalog, Integer>("ID");
        idColumn.setMinWidth(50);
        idColumn.setCellValueFactory(new PropertyValueFactory<ReagentCatalog, Integer>("id"));

        TableColumn<ReagentCatalog, String> nameColumn = new TableColumn<ReagentCatalog, String>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<ReagentCatalog, String>("name"));

        TableColumn<ReagentCatalog, String> descColumn = new TableColumn<ReagentCatalog, String>("Description");
        descColumn.setMinWidth(400);
        descColumn.setCellValueFactory(new PropertyValueFactory<ReagentCatalog, String>("description"));

        TableColumn<ReagentCatalog, String> uuidColumn = new TableColumn<ReagentCatalog, String>("Description");
        uuidColumn.setVisible(false);
        uuidColumn.setMinWidth(200);
        uuidColumn.setCellValueFactory(new PropertyValueFactory<ReagentCatalog, String>("uuid"));

        reagentTable = new TableView<ReagentCatalog>();
        reagentTable.setItems(getCatalogData());
        reagentTable.getColumns().addAll(idColumn, nameColumn, descColumn, uuidColumn);

        reagentTable.setRowFactory(new ReagentCatalogDblClickListener());
        //reagentTable.getSelectionModel().selectedItemProperty().addListener(new ReagentCatalogListFormActionHandler());
        //Table---

        window.setTitle("Lab accaunting");
        window.setMinWidth(300);

        VBox layout = new VBox();
        layout.getChildren().addAll(reagentTable);

        mainLayout.setCenter(layout);

        Scene scene = new Scene(mainLayout, 800, 400);
        window.setScene(scene);
        window.show();
    }

    public static void update() {
        reagentTable.setItems(getCatalogData());
    }

    private static ObservableList<ReagentCatalog> getCatalogData() {
        ObservableList<ReagentCatalog> catalogData = FXCollections.observableArrayList();
        ArrayList<HashMap<String, Object>> catalogElements = labDB.ReadReagentCatalog();
        for (HashMap<String, Object> element : catalogElements) {
            catalogData.add(new ReagentCatalog((Integer) element.get("id"), (String)element.get("name"), (String)element.get("description"), (String)element.get("uuid")));
        }
        return catalogData;
    }

    public static DB_Manager getLabDB() {
        return labDB;
    }

}
