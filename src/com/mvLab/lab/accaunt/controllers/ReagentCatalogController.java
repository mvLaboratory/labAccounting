package com.mvLab.lab.accaunt.controllers;

import com.mvLab.lab.accaunt.Main;
import com.mvLab.lab.accaunt.WindowManager;
import com.mvLab.lab.accaunt.catalogs.Catalog;
import com.mvLab.lab.accaunt.windows.InternalWindow;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

import java.io.IOException;
import java.util.Iterator;


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
            //WindowManager.openReagentCatalogElementForm((Catalog) rowData);

            double mouseX = event.getSceneX();
            double mouseY = event.getSceneY();
            BorderPane windowPane;
            try {
                windowPane = FXMLLoader.load(Main.class.getResource("view/CatalogElementWindowView.fxml"));
            }catch (IOException e) {
                WindowManager.openErrorWindow("Sorry! Can't open catalog element!");
                return;
            }

//            ((GridPane)windowPane.getChildren().get(0)).getChildren().

            Iterator<Node> nodeIter = ((GridPane)windowPane.getChildren().get(0)).getChildren().iterator();
            while (nodeIter.hasNext()) {
                Node ell = nodeIter.next();
                String ellID = ell.getId();
                switch(ellID) {
//                    case "ID" : ell.setTex
                }
//                if (!ellID.contains("Lable")) {
//
//                }
            }

            WindowManager.getInstance().getMainWindow().getRootLayout().getChildren().add(InternalWindow.constructWindow(mouseX, mouseY, ((Catalog)rowData).getHeader(), windowPane));
        }
    }
}

