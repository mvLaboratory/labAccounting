package com.mvLab.lab.accaunt.Catalogs.Reagents;

import com.mvLab.lab.accaunt.Window;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ReagentCatalogElementForm extends Window {

    public ReagentCatalogElementForm() {
        super("Lab accaunting", 400, 200);
        fillCentral();
    }

    private void fillCentral() {
        Label idLabel = new Label("ID:");
        idLabel.setMinWidth(80);
        addCenterElement(idLabel, 0, 2);

        Label nameLabel = new Label("Name:");
        addCenterElement(nameLabel, 0, 4);

        Label descriptionLabel = new Label("Description:");
        addCenterElement(descriptionLabel, 0, 6);

        TextField idTxtFld = new TextField();
        idTxtFld.setPromptText("ID");
        idTxtFld.setEditable(false);
        addCenterElement(idTxtFld, 1, 2);

        TextField nameTxtFld = new TextField();
        nameTxtFld.setPromptText("Name");
        addCenterElement(nameTxtFld, 1, 4);

        TextArea deScTxtFld = new TextArea();
        deScTxtFld.setPromptText("Description");
        deScTxtFld.setMinWidth(50);
        addCenterElement(deScTxtFld, 1, 6);
    }
}
