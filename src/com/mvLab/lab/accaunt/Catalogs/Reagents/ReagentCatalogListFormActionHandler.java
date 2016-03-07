package com.mvLab.lab.accaunt.Catalogs.Reagents;

import com.mvLab.lab.accaunt.WindowManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ReagentCatalogListFormActionHandler implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() instanceof Button) {
            Button source = (Button) event.getSource();
            String buttonID = source.idProperty().getValue();
            if (buttonID == "AddReagent") {
                WindowManager.openReagentCatalogElementForm();
                ReagentCatalogListForm.update();
            }
        }
    }
}
