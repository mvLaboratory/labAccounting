package com.mvLab.lab.account.utils;


import com.mvLab.lab.account.WindowManager;
import com.mvLab.lab.account.catalogs.reagents.ReagentCatalogSelectionForm;
import com.mvLab.lab.account.controllers.ReagentAdmissionDocumentController;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class ControlTableCell<S,T> extends TableCell<S,T> implements EventHandler {
//    MV_Window parentWindow;
    ReagentAdmissionDocumentController controller;
    HBox controlBox = new HBox();
    Button selectBtn = new Button("...");
    TextField textField = new TextField();

    public ControlTableCell(ReagentAdmissionDocumentController controller, boolean selectable){
        this.controller = controller;

        controlBox.getHgrow(textField);
        controlBox.getChildren().add(textField);
        if (selectable) {
            controlBox.getChildren().add(selectBtn);

            selectBtn.setOnMouseClicked(this);
        }


    }

    @Override
    protected void updateItem(T item, boolean empty) {
//        TableView table = getTableView();
//        TableColumn col = getTableColumn();
//        TableRow row = getTableRow();


        this.autosize();
        super.updateItem(item, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            setGraphic(controlBox);
        }

    }

    @Override
    public void handle(Event event) {
        try {
            new ReagentCatalogSelectionForm(WindowManager.getInstance().getMainWindow().getRootLayout()).display();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
