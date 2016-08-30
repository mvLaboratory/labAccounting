package com.mvLab.lab.accaunt.catalogs.Reagents;

import com.mvLab.lab.accaunt.Main;
import com.mvLab.lab.accaunt.WindowManager;
import com.mvLab.lab.accaunt.catalogs.Catalog;
import com.mvLab.lab.accaunt.catalogs.CatalogElementForm;
import com.mvLab.lab.accaunt.windows.InternalWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.Iterator;

public class ReagentCatalogElementForm extends CatalogElementForm {
    private ReagentCatalog reagentElement;
    private double posX, posY;

    public ReagentCatalogElementForm(ReagentCatalog reagentElement, double posX, double posY) {
        this.reagentElement = reagentElement;
        this.posX = posX;
        this.posY = posY;

//        super(ReagentCatalog.class, "Reagent", 450, 250);
//        fillCentral();
    }

    @Override
    public void display() throws IOException {
        BorderPane windowPane;
        try {
            windowPane = FXMLLoader.load(Main.class.getResource("view/CatalogElementWindowView.fxml"));
        }catch (IOException e) {
            WindowManager.openErrorWindow("Sorry! Can't open catalog element!");
            return;
        }

        Iterator<Node> nodeIter = ((GridPane)windowPane.getChildren().get(0)).getChildren().iterator();
        while (nodeIter.hasNext()) {
            Node ell = nodeIter.next();
            String ellID = ell.getId();
            switch(ellID) {
                case "ID": ((TextField) ell).setText("" + reagentElement.getId());
                    break;
                case "Name": ((TextField) ell).setText("" + reagentElement.getName());
                    break;
                case "Description": ((TextArea) ell).setText("" + reagentElement.getDescription());
                    break;
            }
        }

        WindowManager.getInstance().getMainWindow().getRootLayout().getChildren().add(InternalWindow.constructWindow(posX, posY, reagentElement.getHeader(), windowPane));
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
