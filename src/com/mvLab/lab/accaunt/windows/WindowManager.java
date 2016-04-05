package com.mvLab.lab.accaunt.windows;

import com.mvLab.lab.accaunt.catalogs.Catalog;
import com.mvLab.lab.accaunt.catalogs.Reagents.ReagentCatalogElementForm;
import com.mvLab.lab.accaunt.catalogs.Reagents.ReagentCatalogListForm;
import javafx.stage.Stage;

import java.util.ArrayList;

public class WindowManager {
    public static WindowManager instance;
    private static Stage primaryStage;
    private static ArrayList<Window> windowsList = new ArrayList<Window>();

    public static WindowManager getInstance() {
        if (instance == null)
            instance = new WindowManager();

        return instance;
    }

    private WindowManager() {
    }

    public static void setPrimaryStage(Stage primaryStage) {
        WindowManager.primaryStage = primaryStage;
    }

    public static void openMainWindow() {
        MainWindow mainWindow = new MainWindow(primaryStage, "Lab accounting", 800, 500);
        mainWindow.display();
    }

    public static void openReagentCatalogListForm() {
        ReagentCatalogListForm listForm = new ReagentCatalogListForm("Reagents", 800, 400);
        listForm.display();
    }

    public static void openReagentCatalogElementForm() {
        ReagentCatalogElementForm listForm = new ReagentCatalogElementForm();
        listForm.display();
    }

    public static void openReagentCatalogElementForm(Catalog element) {
        ReagentCatalogElementForm listForm = new ReagentCatalogElementForm();
        listForm.setCatalogElement(element);
        listForm.elementToForm();
        listForm.display();
    }

    public static void openErrorWindow(String text) {
        Window errWin = new ErrorWindow("Error!", text, 100, 200);
        errWin.display();
    }
}
