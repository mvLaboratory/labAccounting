package com.mvLab.lab.accaunt.Catalogs.Reagents;

import com.mvLab.lab.accaunt.Catalogs.CatalogForm;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ReagentCatalogElementForm extends CatalogForm {

    public ReagentCatalogElementForm() {
        super("Reagent", 450, 250);
        fillCentral();
    }

    private void fillCentral() {
        Label idLabel = new Label("ID:");
        idLabel.setMinWidth(80);
        addCenterElement(idLabel, 0, 0);

        Label nameLabel = new Label("Name:");
        addCenterElement(nameLabel, 0, 2);

        Label descriptionLabel = new Label("Description:");
        addCenterElement(descriptionLabel, 0, 4);

        TextField idTxtFld = new TextField();
        idTxtFld.setPromptText("ID");
        idTxtFld.setMaxWidth(130);
        idTxtFld.setEditable(false);
        addCenterElement(idTxtFld, 1, 0);

        TextField nameTxtFld = new TextField();
        nameTxtFld.setPromptText("Name");
        addCenterElement(nameTxtFld, 1, 2);

        TextArea deScTxtFld = new TextArea();
        deScTxtFld.setPromptText("Description");
        deScTxtFld.setMinSize(50, 50);
        addCenterElement(deScTxtFld, 1, 4);
    }
}
