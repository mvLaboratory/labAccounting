package com.mvLab.lab.accaunt.controllers;

import com.mvLab.lab.accaunt.WindowManager;
import com.mvLab.lab.accaunt.catalogs.Reagents.ReagentCatalog;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class ReagentCatalogController<Type> implements EventHandler<MouseEvent>, Callback<TableView<Type>, TableRow<Type>> {
    private TableView table;

    private TableRow<Type> row;

    @FXML
    public void addButtonClick() {
        WindowManager.getInstance().openReagentCatalogElementForm();
//        ReagentCatalog element =
//                table.getSelectionModel().select(element);
    }

    public void selectRow(ReagentCatalog element, boolean scrollToRow) {
        table.getSelectionModel().select(element);
        int rowIndex = table.getSelectionModel().getSelectedIndex();
        //table.getSelectionModel().getSelectedIndex();
        table.getFocusModel().focus(rowIndex);
        if (scrollToRow)
            table.scrollTo(rowIndex);
    }

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

    public void setTable(TableView table) {
        this.table = table;
    }
}

