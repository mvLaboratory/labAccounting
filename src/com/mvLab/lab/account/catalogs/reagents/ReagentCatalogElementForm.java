package com.mvLab.lab.account.catalogs.reagents;

import com.mvLab.lab.account.Main;
import com.mvLab.lab.account.WindowManager;
import com.mvLab.lab.account.catalogs.CatalogElementForm;
import com.mvLab.lab.account.controllers.ReagentElementController;
import com.mvLab.lab.account.windows.InternalWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class ReagentCatalogElementForm extends CatalogElementForm {

    private ReagentCatalog reagentElement;
    private double posX, posY;
    private InternalWindow formWindow;
    private BorderPane windowPane;
    private FXMLLoader loader;
    private ReagentElementController elementController;

    {
        loader = new FXMLLoader(Main.class.getResource("/view/ReagentCatalogElementForm.fxml"));

        try {
            //windowPane = FXMLLoader.load(Main.class.getResource("view/ReagentCatalogElementForm.fxml"));
            windowPane = loader.load();
            elementController = loader.getController();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ReagentCatalogElementForm() {
        this.reagentElement = new ReagentCatalog();
        this.posX = (WindowManager.getInstance().getStageWidth() / 2) - (windowPane.getPrefWidth() / 2) + 50;
        this.posY = WindowManager.getInstance().getStageHeight() / 4;

        this.posX += (WindowManager.getInstance().getInnerWindowsCount() * 20);
        this.posY += (WindowManager.getInstance().getInnerWindowsCount() * 20);

        newElement = true;
    }

    public ReagentCatalogElementForm(ReagentCatalog reagentElement, double posX, double posY) {
        this.reagentElement = reagentElement;
//        this.posX = posX;
//        this.posY = posY;
        this.posX = (WindowManager.getInstance().getStageWidth() / 2) - (windowPane.getPrefWidth() / 2) + 50;
        this.posY = WindowManager.getInstance().getStageHeight() / 4;

        this.posX += (WindowManager.getInstance().getInnerWindowsCount() * 20);
        this.posY += (WindowManager.getInstance().getInnerWindowsCount() * 20);

//        super(ReagentCatalog.class, "Reagent", 450, 250);
//        fillCentral();
    }

    @Override
    public void display() throws IOException {
        if (windowPane == null) {
            WindowManager.openErrorWindow("Sorry! Can't open catalog element!");
            return;
        }

//        Iterator<Node> nodeIter = ((GridPane)windowPane.getChildren().get(0)).getChildren().iterator();
//        while (nodeIter.hasNext()) {
//            Node ell = nodeIter.next();
//            String ellID = ell.getId();
//            switch(ellID) {
////                case "ID": ((TextField) ell).setText("" + reagentElement.getId());
////                    break;
//                case "Name": ((TextField) ell).setText("" + reagentElement.getName());
//                    break;
//                case "Description": ((TextArea) ell).setText("" + reagentElement.getDescription());
//                    break;
//            }
//        }

        formWindow = InternalWindow.constructWindow(posX, posY, windowPane);

        //ReagentElementController controller = loader.getController();
        elementController.setForm(this);
        elementController.setFields();
        elementController.customizeWindow(formWindow);

        WindowManager.getInstance().getMainWindow().getRootLayout().getChildren().add(formWindow);


    }

    public void hide() {
        if (windowPane == null) {
            WindowManager.openErrorWindow("Sorry! Can't open catalog element!");
            return;
        }

        windowPane.setVisible(false);
    }

    public void show() {
        if (windowPane == null) {
            WindowManager.openErrorWindow("Sorry! Can't open catalog element!");
            return;
        }

        windowPane.setVisible(true);
        formWindow.focus();

    }

    public void save() {
        reagentElement.save();
        reagentElement.readElement();
        elementController.setFields();
        WindowManager.getInstance().getMainWindow().getController().updateCatalogWindowLink(reagentElement);
        elementController.setHeader();
        //elementController = loader.getController();
    }

    public void closeWindow() {
        if (newElement) {
            WindowManager.getInstance().closeNewCatalogWindow(formWindow, reagentElement, "ReagentList");
        }
        else
        {
            WindowManager.getInstance().closeCatalogWindow(formWindow, reagentElement, "ReagentList");
        }
        //WindowManager.getInstance().updateReagentCatalogListForm();
    }

    @Override
    public ReagentCatalog getCatalogElement() {
        return reagentElement;
    }



    //    private final void fillCentral() {
//        Label idLabel = new Label("ID:");
//        idLabel.setMinWidth(80);
//        addCenterElement(idLabel, 0, 0);
//
//        Label nameLabel = new Label("Name:");
//        addCenterElement(nameLabel, 0, 2);
//
//        Label descriptionLabel = new Label("Description:");
//        addCenterElement(descriptionLabel, 0, 4);
//
//        TextField idTxtFld = new TextField();
//        idTxtFld.setId("id");
//        idTxtFld.setPromptText("ID");
//        idTxtFld.setMaxWidth(130);
//        idTxtFld.setEditable(false);
//        addCenterElement(idTxtFld, 1, 0);
//
//        TextField nameTxtFld = new TextField();
//        nameTxtFld.setId("Name");
//        nameTxtFld.setPromptText("Name");
//        addCenterElement(nameTxtFld, 1, 2);
//
//        TextArea deScTxtFld = new TextArea();
//        deScTxtFld.setId("Description");
//        deScTxtFld.setPromptText("Description");
//        deScTxtFld.setMinSize(50, 50);
//        addCenterElement(deScTxtFld, 1, 4);
//    }
}
