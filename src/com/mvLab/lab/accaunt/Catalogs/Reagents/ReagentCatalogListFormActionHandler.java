package com.mvLab.lab.accaunt.catalogs.Reagents;

import com.mvLab.lab.accaunt.WindowManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ReagentCatalogListFormActionHandler<Catalog> implements EventHandler<ActionEvent>, ChangeListener<Catalog> {

    @Override
    public void handle(ActionEvent event) {
//        if (event.getSource() instanceof Button) {
//            Button source = (Button) event.getSource();
//            String buttonID = source.idProperty().getValue();
//            if (buttonID == "AddReagent") {
//                WindowManager.openReagentCatalogElementForm();
//                ReagentCatalogListForm.update();
//            }
//            else if (buttonID == "EditReagent") {
//                WindowManager.openReagentCatalogElementForm(ReagentCatalogListForm.getPresentRowData());
//            }
//        }
    }

    @Override
    public void changed(ObservableValue<? extends Catalog> observable, Catalog oldValue, Catalog newValue) {
        if (oldValue == newValue)
            WindowManager.openErrorWindow("selected");
    }
}
