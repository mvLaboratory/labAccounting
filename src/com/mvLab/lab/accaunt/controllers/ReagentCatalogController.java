package com.mvLab.lab.accaunt.controllers;

import com.mvLab.lab.accaunt.catalogs.Catalog;
import com.mvLab.lab.accaunt.WindowManager;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import jfxtras.labs.scene.control.window.CloseIcon;
import jfxtras.labs.scene.control.window.MinimizeIcon;
import jfxtras.labs.scene.control.window.Window;



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


            Window w = new Window("My Window#");
            w.setLayoutX(10);
            w.setLayoutY(10);
            // define the initial window size
            w.setPrefSize(300, 200);
            // either to the left
            w.getLeftIcons().add(new CloseIcon(w));
            // .. or to the right
            w.getRightIcons().add(new MinimizeIcon(w));
            w.getContentPane().getChildren().add(new Label("Content... \nof the window#"));
            // add the window to the canvas
            w.setStyle("view/DarkTheme.css");
            //WindowManager.getInstance().getMainWindow().getPrimaryStage()


            //WindowManager.openReagentCatalogElementForm((Catalog) rowData);


        }
    }
}

