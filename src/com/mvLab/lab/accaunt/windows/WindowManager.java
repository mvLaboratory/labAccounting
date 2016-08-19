package com.mvLab.lab.accaunt.windows;

import com.mvLab.lab.accaunt.Main;
import com.mvLab.lab.accaunt.catalogs.Catalog;
import com.mvLab.lab.accaunt.catalogs.Reagents.ReagentCatalog;
import com.mvLab.lab.accaunt.catalogs.Reagents.ReagentCatalogDblClickListener;
import com.mvLab.lab.accaunt.catalogs.Reagents.ReagentCatalogElementForm;
import com.mvLab.lab.accaunt.catalogs.Reagents.ReagentCatalogListForm;
import com.mvLab.lab.accaunt.documents.reagentArrival.ReagentArrivalListForm;
import com.mvLab.lab.accaunt.documents.reagentConsumption.ReagentConsumptionListForm;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class WindowManager {
    public static WindowManager instance;

    private static Stage primaryStage;
    private static BorderPane rootLayout;
    private static ArrayList<MV_Window> windowsList = new ArrayList<MV_Window>();

    private WindowManager() {

    }

    public static WindowManager getInstance() {
        if (instance == null)
            instance = new WindowManager();

        return instance;
    }

    public static WindowManager initialize(Stage primaryStage) {
        WindowManager.primaryStage = primaryStage;

        if (instance == null)
            instance = new WindowManager();

        return instance;
    }



    public static void setPrimaryStage(Stage primaryStage) {
        WindowManager.primaryStage = primaryStage;
    }

    public void openMainWindow() throws IOException {
        rootLayout = FXMLLoader.load(Main.class.getResource("view/MainView.fxml"));
        primaryStage.setTitle("Laboratory accountant");
        primaryStage.setScene(new Scene(rootLayout));
        primaryStage.setMaximized(true);
        primaryStage.show();

        Tab nTab = new Tab("Main");
        nTab.setContent(FXMLLoader.load(Main.class.getResource("view/ReportView.fxml")));
        TabPane centralPane = new TabPane();
        centralPane.getTabs().add(nTab);
        rootLayout.setCenter(centralPane);
        nTab.setClosable(false);
    }

    public static void openReagentCatalogListForm() throws IOException {
        Tab nTab = new Tab("Reagents");
        BorderPane tabView = FXMLLoader.load(Main.class.getResource("view/TableView.fxml"));

        TableView<ReagentCatalog> reagentTableView = (TableView) tabView.getCenter();
        reagentTableView.setRowFactory(new ReagentCatalogDblClickListener());
        reagentTableView.setItems(ReagentCatalog.getCatalogData());

        for (TableColumn coll : reagentTableView.getColumns()) {
            String collName = coll.getText();
            coll.setCellValueFactory(new PropertyValueFactory(collName.toLowerCase()));
        }
        nTab.setContent(tabView);

        TabPane centralPane = (TabPane) rootLayout.getCenter();
        centralPane.getTabs().add(nTab);
        SingleSelectionModel<Tab> selectionModel = centralPane.getSelectionModel();
        selectionModel.select(nTab);
    }

    public static void openReagentCatalogElementForm() {
        ReagentCatalogElementForm listForm = new ReagentCatalogElementForm();
        listForm.display();
    }

    public static void openReagentArrivalListForm() {
        ReagentArrivalListForm docForm = new ReagentArrivalListForm("Reagent arrival", 800, 400);
        docForm.display();
    }

    public static void openReagentConsumptionListForm() {
        ReagentConsumptionListForm docForm = new ReagentConsumptionListForm("Reagent consumption", 800, 400);
        docForm.display();
    }

    public static void openReagentCatalogElementForm(Catalog element) {
        ReagentCatalogElementForm listForm = new ReagentCatalogElementForm();
        listForm.setCatalogElement(element);
        listForm.elementToForm();
        listForm.display();
    }

    public static void openErrorWindow(String text) {
        MV_Window errWin = new ErrorWindow("Error!", text, 100, 200);
        errWin.display();
    }
}
