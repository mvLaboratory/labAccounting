package com.mvLab.lab.accaunt.windows;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ErrorWindow extends MV_Window implements EventHandler<ActionEvent> {
    private String text;

    public ErrorWindow(String title, String text, int windowWidth, int windowHeight) {
        super(title, windowWidth, windowHeight, true);
        this.text = text;
        addElements();
    }

    private void addElements() {
        Label msgLabel = new Label(text);
        msgLabel.setMinSize(50, 50);
        addCenterElement(msgLabel, 0, 0);

        Button btnOK = new Button("OK");
        btnOK.setId("ok");
        btnOK.setOnAction(this);
        btnOK.setMinWidth(50);
        btnOK.setDefaultButton(true);
        addBottomElement(btnOK);
        setBottomCommandPanelAligment(2);
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() instanceof Button) {
            Button source = (Button) event.getSource();
            String buttonID = source.idProperty().getValue();
            if (buttonID.equals("ok")) {
                close();
            }
        }
    }
}
