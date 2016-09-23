package com.mvLab.lab.account.documents;

import com.mvLab.lab.account.windows.MV_Window;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public abstract class DocumentForm extends MV_Window implements EventHandler<ActionEvent> {

    public abstract Document getDocument();

    @Override
    public void handle(ActionEvent event) {

    }

    public abstract void show();
}
