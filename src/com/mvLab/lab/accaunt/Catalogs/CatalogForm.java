package com.mvLab.lab.accaunt.Catalogs;

import com.mvLab.lab.accaunt.Window;
import javafx.scene.control.Button;

public class CatalogForm extends Window {

    public CatalogForm(String title, int windowWidth, int windowHeight) {
        super(title, windowWidth, windowHeight);
        addBottomCommandPanel();
    }

    public void addBottomCommandPanel() {
        Button btnOK = new Button("OK");
        btnOK.setMinWidth(50);
        btnOK.setDefaultButton(true);
        addBottomElement(btnOK);

        Button btnSave = new Button("Save");
        btnSave.setMinWidth(50);
        addBottomElement(btnSave);

        Button btnClose = new Button("Close");
        btnClose.setCancelButton(true);
        btnClose.setMinWidth(50);
        addBottomElement(btnClose);
    }
}
