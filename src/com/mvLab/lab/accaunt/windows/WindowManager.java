package com.mvLab.lab.accaunt.windows;

import com.mvLab.lab.accaunt.Main;
import com.mvLab.lab.accaunt.catalogs.Catalog;
import com.mvLab.lab.accaunt.catalogs.Reagents.ReagentCatalogElementForm;
import com.mvLab.lab.accaunt.catalogs.Reagents.ReagentCatalogListForm;
import com.mvLab.lab.accaunt.documents.reagentArrival.ReagentArrivalListForm;
import com.mvLab.lab.accaunt.documents.reagentConsumption.ReagentConsumptionListForm;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class WindowManager {
    public static WindowManager instance;
    private static Stage primaryStage;
    private static BorderPane rootLayout;
    private static ArrayList<MV_Window> windowsList = new ArrayList<MV_Window>();

    public static WindowManager getInstance() {
        if (instance == null)
            instance = new WindowManager();

        return instance;
    }

    public static void initialize(Stage primaryStage) {
        WindowManager.primaryStage = primaryStage;
    }

    private WindowManager() {
    }

    public static void setPrimaryStage(Stage primaryStage) {
        WindowManager.primaryStage = primaryStage;
    }

    public static void openMainWindow() throws IOException {
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
    }

    public static void openReagentCatalogListForm() throws IOException {
        Tab nTab = new Tab("Reagents");
        nTab.setContent(FXMLLoader.load(Main.class.getResource("view/TableView.fxml")));
        TabPane centralPane = (TabPane) rootLayout.getCenter();
        centralPane.getTabs().add(nTab);
        SingleSelectionModel<Tab> selectionModel = centralPane.getSelectionModel();
        selectionModel.select(nTab);


        ReagentCatalogListForm listForm = new ReagentCatalogListForm("Reagents", 800, 400);
        listForm.display();
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
