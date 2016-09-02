package com.mvLab.lab.accaunt.controllers;

import com.mvLab.lab.accaunt.WindowManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ToolBar;

public class MainController {
    @FXML ToolBar innerWindowTB;
    @FXML Button reagentButton;

    public void addWindowLink() {
        innerWindowTB.getItems().add(new Hyperlink("sssssss"));
    }

    public void reagentButtonOnClick()  {
            WindowManager.getInstance().openReagentCatalogListForm();
    }

    public void menuOnExitClick() {
        System.exit(0);
    }
}
