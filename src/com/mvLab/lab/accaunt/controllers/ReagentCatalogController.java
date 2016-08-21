package com.mvLab.lab.accaunt.controllers;

import com.mvLab.lab.accaunt.catalogs.Catalog;
import com.mvLab.lab.accaunt.WindowManager;
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
            Type rowData = ((TableRow<Type>) event.getSource()).getItem();
            WindowManager.openReagentCatalogElementForm((Catalog) rowData);
        }
    }
}

