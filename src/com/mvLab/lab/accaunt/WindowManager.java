package com.mvLab.lab.accaunt;

import com.mvLab.lab.accaunt.catalogs.Catalog;
import com.mvLab.lab.accaunt.catalogs.Reagents.ReagentCatalogElementForm;
import com.mvLab.lab.accaunt.catalogs.Reagents.ReagentCatalogListForm;
import com.mvLab.lab.accaunt.documents.reagentArrival.ReagentArrivalListForm;
import com.mvLab.lab.accaunt.documents.reagentConsumption.ReagentConsumptionListForm;
import com.mvLab.lab.accaunt.windows.ErrorWindow;
import com.mvLab.lab.accaunt.windows.MV_Window;
import com.mvLab.lab.accaunt.windows.MainWindow;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class WindowManager {
    public static WindowManager instance;

    private Stage primaryStage;
    private MainWindow mainWindow;
    private HashMap<String, MV_Window> windowsmap = new HashMap<>();

    private WindowManager() {

    }

    public static WindowManager getInstance() {
        if (instance == null)
            instance = new WindowManager();

        return instance;
    }

    public static void initialize(Stage primaryStage) {
        if (instance == null)
            instance = new WindowManager();
        instance.setPrimaryStage(primaryStage);
    }

    public void openMainWindow() {
        mainWindow = new MainWindow(primaryStage);
        try {
            mainWindow.display();
        }
        catch (IOException e) {
            openErrorWindow("Sorry! Can't open main window.");
            // TODO: handle exception
        }
//        rootLayout = FXMLLoader.load(Main.class.getResource("view/MainView.fxml"));
//        primaryStage.setTitle("Laboratory accountant");
//        primaryStage.setScene(new Scene(rootLayout));
//        primaryStage.setMaximized(true);
//        primaryStage.show();
//
//        Tab nTab = new Tab("Main");
//        nTab.setContent(FXMLLoader.load(Main.class.getResource("view/ReportView.fxml")));
//        TabPane centralPane = new TabPane();
//        centralPane.getTabs().add(nTab);
//        rootLayout.setCenter(centralPane);
//        nTab.setClosable(false);
    }

    public boolean isInitialized() {
        return mainWindow != null;
    }

    public void openReagentCatalogListForm() {
        if (!isInitialized()) {
            openErrorWindow("Main window is not initialized yet.");
            return;
        }

        ReagentCatalogListForm reagentCatalogListForm = (ReagentCatalogListForm) windowsmap.get("reagentCatalogList");
        if (windowsmap.get("reagentCatalogList") == null) {
            reagentCatalogListForm = new ReagentCatalogListForm(mainWindow.getRootLayout());

            try {
                reagentCatalogListForm.display();
                windowsmap.put("reagentCatalogList", reagentCatalogListForm);
            } catch (IOException e) {
                openErrorWindow("Sorry! Can't open reagent catalog list form!");
            }
        }
        else {
            reagentCatalogListForm.update();
            reagentCatalogListForm.activate();
        }
    }

    public static void openReagentCatalogElementForm() {
        ReagentCatalogElementForm listForm = new ReagentCatalogElementForm();
        try {
            listForm.display();
        }
        catch (IOException e) {
            openErrorWindow("Sorry! Can't open catalog element form.");
        }

    }

    public static void openReagentArrivalListForm() {
        ReagentArrivalListForm docForm = new ReagentArrivalListForm("Reagent arrival", 800, 400);
        try {
            docForm.display();
        }
        catch (IOException e) {
            openErrorWindow("Sorry! Can't open document list form.");
        }
    }

    public static void openReagentConsumptionListForm() {
        ReagentConsumptionListForm docForm = new ReagentConsumptionListForm("Reagent consumption", 800, 400);
        try {
            docForm.display();
        }
        catch (IOException e) {
            openErrorWindow("Sorry! Can't open document list form.");
        }
    }

    public static void openReagentCatalogElementForm(Catalog element) {
        ReagentCatalogElementForm listForm = new ReagentCatalogElementForm();
        listForm.setCatalogElement(element);
        listForm.elementToForm();
        try {
            listForm.display();
        }
        catch (IOException e) {
            openErrorWindow("Sorry! Can't open catalog element form.");
        }
    }

    public static void openErrorWindow(String text) {
        MV_Window errWin = new ErrorWindow("Error!", text, 100, 200);
        try {
            errWin.display();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}
