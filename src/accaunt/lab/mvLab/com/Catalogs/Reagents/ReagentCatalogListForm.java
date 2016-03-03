package accaunt.lab.mvLab.com.Catalogs.Reagents;

import accaunt.lab.mvLab.com.DB_Helper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

public class ReagentCatalogListForm {
    private static DB_Helper labDB;
    private static TableView<ReagentCatalog> reagentTable;
    private static Stage window = new Stage();

    public ReagentCatalogListForm(DB_Helper labDB) {
        this.labDB = labDB;
    }

    public static void display() {
        TableColumn<ReagentCatalog, Integer> idColumn = new TableColumn<ReagentCatalog, Integer>("ID");
        idColumn.setMinWidth(50);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<ReagentCatalog, String> nameColumn = new TableColumn<ReagentCatalog, String>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<ReagentCatalog, String> descColumn = new TableColumn<ReagentCatalog, String>("Description");
        descColumn.setMinWidth(400);
        descColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        reagentTable = new TableView<>();
        reagentTable.setItems(getCatalogData());
        reagentTable.getColumns().addAll(idColumn, nameColumn, descColumn);

        window.setTitle("Lab accaunting");
        window.setMinWidth(300);

        VBox layout = new VBox();
        layout.getChildren().addAll(reagentTable);

        Scene scene = new Scene(layout, 800, 400);
        window.setScene(scene);
        window.show();
    }

    private static ObservableList<ReagentCatalog> getCatalogData() {
        ObservableList<ReagentCatalog> catalogData = FXCollections.observableArrayList();
        ArrayList<HashMap<String, Object>> catalogElements = labDB.ReadReagentCatalog();
        for (HashMap<String, Object> element : catalogElements) {
            catalogData.add(new ReagentCatalog((int) element.get("id"), (String)element.get("name"), (String)element.get("description")));
        }
        return catalogData;
    }
}
