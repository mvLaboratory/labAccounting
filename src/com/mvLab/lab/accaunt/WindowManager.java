package com.mvLab.lab.accaunt;

import com.mvLab.lab.accaunt.catalogs.Catalog;
import com.mvLab.lab.accaunt.catalogs.Reagents.ReagentCatalog;
import com.mvLab.lab.accaunt.catalogs.Reagents.ReagentCatalogElementForm;
import com.mvLab.lab.accaunt.catalogs.Reagents.ReagentCatalogListForm;
import com.mvLab.lab.accaunt.documents.reagentArrival.ReagentArrivalListForm;
import com.mvLab.lab.accaunt.documents.reagentConsumption.ReagentConsumptionListForm;
import com.mvLab.lab.accaunt.windows.ErrorWindow;
import com.mvLab.lab.accaunt.windows.InternalWindow;
import com.mvLab.lab.accaunt.windows.MV_Window;
import com.mvLab.lab.accaunt.windows.MainWindow;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class WindowManager {
    public static WindowManager instance;

    private Stage primaryStage;
    private MainWindow mainWindow;
    private HashMap<String, MV_Window> windowsmap = new HashMap<>();
    //private ArrayList<MV_Window> innerWindowsList = new ArrayList<>();// TODO: 02.09.2016 delete
    private HashMap<Object, MV_Window> innerWindowsMap = new HashMap<>();

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
        if (reagentCatalogListForm == null) {
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

    public void closeReagentCatalogListForm() {
        if (!isInitialized()) {
            openErrorWindow("Main window is not initialized yet.");
            return;
        }

        if (! (windowsmap.get("reagentCatalogList") == null)) {
            windowsmap.remove("reagentCatalogList");
        }
    }

    public void updateReagentCatalogListForm() {
        if (!isInitialized()) {
            openErrorWindow("Main window is not initialized yet.");
            return;
        }

        ReagentCatalogListForm reagentCatalogListForm = (ReagentCatalogListForm) windowsmap.get("reagentCatalogList");
        if (! (reagentCatalogListForm == null)) {
            reagentCatalogListForm.update();
            reagentCatalogListForm.activate();
        }
    }

    public void updateReagentCatalogListForm(ReagentCatalog element, boolean scrollToRow) {
        if (!isInitialized()) {
            openErrorWindow("Main window is not initialized yet.");
            return;
        }

        ReagentCatalogListForm reagentCatalogListForm = (ReagentCatalogListForm) windowsmap.get("reagentCatalogList");
        if (! (reagentCatalogListForm == null)) {
            reagentCatalogListForm.update();
            reagentCatalogListForm.activate();
            reagentCatalogListForm.selectRow(element, scrollToRow);
        }
    }

    public void updateReagentCatalogListForm(ReagentCatalog element) {
        if (!isInitialized()) {
            openErrorWindow("Main window is not initialized yet.");
            return;
        }

        ReagentCatalogListForm reagentCatalogListForm = (ReagentCatalogListForm) windowsmap.get("reagentCatalogList");
        if (! (reagentCatalogListForm == null)) {
            reagentCatalogListForm.update();
            reagentCatalogListForm.activate();
            reagentCatalogListForm.selectRow(element, false);
        }
    }

    public void openReagentCatalogElementForm() {

        try {
            if (innerWindowsMap.containsKey(ReagentCatalog.class)) {
                ((ReagentCatalogElementForm) innerWindowsMap.get(ReagentCatalog.class)).show();
            }
            else {
                ReagentCatalogElementForm ellForm = new ReagentCatalogElementForm();
                ellForm.display();
                innerWindowsMap.put(ReagentCatalog.class, ellForm);
                mainWindow.getController().addCatalogWindowLink(ReagentCatalog.class, "new Reagent");
            }
        }
        catch (IOException e) {
            openErrorWindow("Sorry! Can't open catalog element form.");
        }

    }

    public void openReagentCatalogElementForm(ReagentCatalog reagentElement, double posX, double posY) {
//        ReagentCatalogElementForm listForm = new ReagentCatalogElementForm();
//        listForm.setCatalogElement(element);
//        listForm.elementToForm();
        ReagentCatalogElementForm ellForm = new ReagentCatalogElementForm(reagentElement, posX, posY);
        try {
            ellForm.display();
            //innerWindowsList.add(ellForm);// TODO: 02.09.2016  delete
            innerWindowsMap.put(reagentElement, ellForm);
            mainWindow.getController().addCatalogWindowLink(reagentElement);

//            innerWindowsmap.put("1", ellForm);
//            BorderPane borderPane = getMainWindow().getRootLayout();
//            borderPane.
        }
        catch (IOException e) {
            openErrorWindow("Sorry! Can't open catalog element form.");
        }
   }

    public void openReagentArrivalListForm() {
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

    public void closeCatalogWindow(InternalWindow window) {
        getMainWindow().getRootLayout().getChildren().remove(window);
    }

    public void closeCatalogWindow(InternalWindow window, String formName) {
        getMainWindow().getRootLayout().getChildren().remove(window);
        if  (formName.equals("ReagentList")) {
            updateReagentCatalogListForm();
        }
    }

    public void showInnerWindow(Object data) {
        MV_Window innerWindow = innerWindowsMap.get(data);
        ((ReagentCatalogElementForm)innerWindow).show();
    }

    public void closeCatalogWindow(InternalWindow window, Catalog element, String formName) {
        getMainWindow().getRootLayout().getChildren().remove(window);
        innerWindowsMap.remove(element);
        getMainWindow().getController().removeCatalogWindowLink(element);
        if  (formName.equals("ReagentList")) {
            updateReagentCatalogListForm((ReagentCatalog) element);
        }

        //getMainWindow().getController().updateCatalogWindowLinks();
    }

    public void closeNewCatalogWindow(InternalWindow window, Catalog element, String formName) {
        getMainWindow().getRootLayout().getChildren().remove(window);
        innerWindowsMap.remove(element.getClass());
        getMainWindow().getController().removeCatalogWindowLink(element.getClass());
        if  (formName.equals("ReagentList")) {
            updateReagentCatalogListForm((ReagentCatalog) element, true);
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

    public double getStageWidth() {
        return primaryStage.getWidth();
    }

    public double getStageHeight() {
        return primaryStage.getHeight();
    }

    public MainWindow getMainWindow() {
        return mainWindow;
    }

    public int getInnerWindowsCount() {
        return innerWindowsMap.size();
    }

    public HashMap<Object, MV_Window> getInnerWindowsMap() {
        return innerWindowsMap;
    }

    public MV_Window getInnerWindowsFromTB(Object data) {// TODO: 02.09.2016 delete
        return innerWindowsMap.get(data);
    }
}
