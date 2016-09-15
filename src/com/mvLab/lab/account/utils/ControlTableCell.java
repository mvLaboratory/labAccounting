package com.mvLab.lab.account.utils;


import com.mvLab.lab.account.controllers.ReagentAdmissionDocumentController;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

public class ControlTableCell<S,T> extends TableCell<S,T> {
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
        } else if (isEditable()) {
            setGraphic(controlBox);
        }

    }

}
