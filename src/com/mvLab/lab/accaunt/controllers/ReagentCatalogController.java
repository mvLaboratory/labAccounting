package com.mvLab.lab.accaunt.controllers;

import com.mvLab.lab.accaunt.WindowManager;
import com.mvLab.lab.accaunt.catalogs.Reagents.ReagentCatalog;
import javafx.event.EventHandler;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class ReagentCatalogController<Type> implements EventHandler<MouseEvent>, Callback<TableView<Type>, TableRow<Type>> {
    private TableRow<Type> row;

    @Override
    public TableRow<Type> call(TableView<Type> param) {
        TableRow<Type> row = new TableRow();
        row.setOnMouseClicked(this);
        return row;
    }

    @Override
    public void handle(MouseEvent event) {
        if (event.getClickCount() == 2) {
            ReagentCatalog rowData = ((TableRow<ReagentCatalog>) event.getSource()).getItem();
            //WindowManager.openReagentCatalogElementForm((Catalog) rowData);

            double mouseX = event.getSceneX();
            double mouseY = event.getSceneY();
            WindowManager.getInstance().openReagentCatalogElementForm(rowData, mouseX, mouseY);
        }
    }
}

