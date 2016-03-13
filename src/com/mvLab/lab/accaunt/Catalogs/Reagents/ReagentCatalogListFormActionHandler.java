package com.mvLab.lab.accaunt.catalogs.Reagents;

import com.mvLab.lab.accaunt.catalogs.Catalog;
import com.mvLab.lab.accaunt.windows.WindowManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class ReagentCatalogListFormActionHandler implements EventHandler<ActionEvent>, ChangeListener<Catalog>, Callback<TableView<Catalog>, TableRow<Catalog>> {

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

    @Override
    public void changed(ObservableValue<? extends Catalog> observable, Catalog oldValue, Catalog newValue) {
        if (oldValue == newValue)
            WindowManager.openErrorWindow("selected");
    }

    @Override
    public TableRow<Catalog> call(TableView<Catalog> param) {
        return null;
    }
}
