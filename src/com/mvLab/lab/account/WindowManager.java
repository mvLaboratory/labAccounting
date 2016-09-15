package com.mvLab.lab.account;

import com.mvLab.lab.account.catalogs.Catalog;
import com.mvLab.lab.account.catalogs.reagents.ReagentCatalog;
import com.mvLab.lab.account.catalogs.reagents.ReagentCatalogElementForm;
import com.mvLab.lab.account.catalogs.reagents.ReagentCatalogListForm;
import com.mvLab.lab.account.documents.Document;
import com.mvLab.lab.account.documents.reagentAdmission.ReagentAdmission;
import com.mvLab.lab.account.documents.reagentAdmission.ReagentAdmissionElementForm;
import com.mvLab.lab.account.documents.reagentAdmission.ReagentAdmissionForm;
import com.mvLab.lab.account.windows.*;
import com.mvLab.lab.account.windows.interfaces.Showable;
import javafx.stage.Stage;

import java.io.IOException;
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
            e.printStackTrace();
            openErrorWindow("Sorry! Can't open main window.");
            // TODO: handle exception
        }
//        rootLayout = FXMLLoader.load(Main.class.getResource("view/MainForm.fxml"));
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

    //******Reagent catalog+++
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

    public void closeCatalogWindow(InternalWindow window, Catalog element, String formName) {
        getMainWindow().getRootLayout().getChildren().remove(window);
        innerWindowsMap.remove(element);
        getMainWindow().getController().removeCatalogWindowLink(element);
        if  (formName.equals("ReagentList")) {
            updateReagentCatalogListForm((ReagentCatalog) element);
        }
    }

    public void closeNewCatalogWindow(InternalWindow window, Catalog element, String formName) {
        getMainWindow().getRootLayout().getChildren().remove(window);
        innerWindowsMap.remove(element.getClass());
        getMainWindow().getController().removeCatalogWindowLink(element.getClass());
        if  (formName.equals("ReagentList")) {
            updateReagentCatalogListForm((ReagentCatalog) element, true);
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
                mainWindow.getController().addWindowLink(ReagentCatalog.class, "new Reagent");
            }
        }
        catch (IOException e) {
            openErrorWindow("Sorry! Can't open catalog element form.");
        }

    }

    public void openReagentCatalogElementForm(ReagentCatalog reagentElement, double posX, double posY) {
        ReagentCatalogElementForm ellForm = new ReagentCatalogElementForm(reagentElement, posX, posY);
        try {
            if (innerWindowsMap.containsKey(reagentElement)) {
                ((ReagentCatalogElementForm) innerWindowsMap.get(reagentElement)).show();
            }
            else {
                ellForm.display();
                innerWindowsMap.put(reagentElement, ellForm);
                mainWindow.getController().addWindowLink(reagentElement);
            }
        }
        catch (IOException e) {
            openErrorWindow("Sorry! Can't open catalog element form.");
        }
   }
    //******Reagent catalog---

    //******Reagent admission+++
    public void openReagentAdmissionForm() {
        if (!isInitialized()) {
            openErrorWindow("Main window is not initialized yet.");
            return;
        }

        ReagentAdmissionForm reagentAdmissionForm = (ReagentAdmissionForm) windowsmap.get("reagentAdmissionForm");
        if (reagentAdmissionForm == null) {
            reagentAdmissionForm = new ReagentAdmissionForm(mainWindow.getRootLayout());

            try {
                reagentAdmissionForm.display();
                windowsmap.put("reagentAdmissionForm", reagentAdmissionForm);
            } catch (IOException e) {
                openErrorWindow("Sorry! Can't open reagent admission list form!");
            }
        }
        else {
            reagentAdmissionForm.update();
            reagentAdmissionForm.activate();
        }
    }

    public void closeReagentAdmissionForm() {
        if (!isInitialized()) {
            openErrorWindow("Main window is not initialized yet.");
            return;
        }

        if (! (windowsmap.get("reagentAdmissionForm") == null)) {
            windowsmap.remove("reagentAdmissionForm");
        }
    }

    public void updateReagentAdmissionForm() {
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

    public void updateReagentAdmissionForm(ReagentAdmission document, boolean scrollToRow) {
        if (!isInitialized()) {
            openErrorWindow("Main window is not initialized yet.");
            return;
        }

        ReagentAdmissionForm reagentAdmissionForm = (ReagentAdmissionForm) windowsmap.get("reagentAdmissionForm");
        if (! (reagentAdmissionForm == null)) {
            reagentAdmissionForm.update();
            reagentAdmissionForm.activate();
            reagentAdmissionForm.selectRow(document, scrollToRow);
        }
    }

    public void updateReagentAdmissionForm(ReagentAdmission document) {
        if (!isInitialized()) {
            openErrorWindow("Main window is not initialized yet.");
            return;
        }

        ReagentAdmissionForm reagentAdmissionForm = (ReagentAdmissionForm) windowsmap.get("reagentAdmissionForm");
        if (! (reagentAdmissionForm == null)) {
            reagentAdmissionForm.update();
            reagentAdmissionForm.activate();
            reagentAdmissionForm.selectRow(document, false);
        }
    }

    public void closeAdmissionWindow(InternalWindow window, Document document, String formName) {
        getMainWindow().getRootLayout().getChildren().remove(window);
        innerWindowsMap.remove(document);
        getMainWindow().getController().removeCatalogWindowLink(document);
        if  (formName.equals("ReagentAdmissionList")) {
            updateReagentAdmissionForm((ReagentAdmission) document);
        }
    }

    public void closeNewAdmissionWindow(InternalWindow window, Document document, String formName) {
        getMainWindow().getRootLayout().getChildren().remove(window);
        innerWindowsMap.remove(document.getClass());
        getMainWindow().getController().removeCatalogWindowLink(document.getClass());
        if  (formName.equals("ReagentList")) {
            updateReagentAdmissionForm((ReagentAdmission) document, true);
        }
    }

    public void openReagentAdmissionElementForm() {

        try {
            if (innerWindowsMap.containsKey(ReagentAdmission.class)) {
               ((ReagentAdmissionElementForm) innerWindowsMap.get(ReagentAdmission.class)).show();
            }
            else {
                ReagentAdmissionElementForm ellForm = new ReagentAdmissionElementForm();
                ellForm.display();
                innerWindowsMap.put(ReagentAdmission.class, ellForm);
                mainWindow.getController().addWindowLink(ReagentCatalog.class, "new Admission");
            }
        }
        catch (IOException e) {
            openErrorWindow("Sorry! Can't open document element form.");
        }

    }

    public void openReagentAdmissionElementForm(ReagentAdmission document, double posX, double posY) {
        ReagentAdmissionElementForm docForm = new ReagentAdmissionElementForm(document, posX, posY);
        try {
            if (innerWindowsMap.containsKey(document)) {
                ((ReagentAdmissionElementForm) innerWindowsMap.get(document)).show();
            }
            else {
                docForm.display();
                innerWindowsMap.put(document, docForm);
                mainWindow.getController().addWindowLink(document);
            }
        }
        catch (IOException e) {
            openErrorWindow("Sorry! Can't open catalog element form.");
        }
    }
    //******Reagent admission---

    public static void openReagentConsumptionListForm() {
//        ReagentConsumptionListForm docForm = new ReagentConsumptionListForm("Reagent consumption", 800, 400);
//        try {
//            docForm.display();
//        }
//        catch (IOException e) {
//            openErrorWindow("Sorry! Can't open document list form.");
//        }
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
        Showable innerWindow = (Showable) innerWindowsMap.get(data);
        innerWindow.show();
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
