package com.mvLab.account.documents;

import com.mvLab.account.windows.MV_Window;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public abstract class DocumentForm extends MV_Window implements EventHandler<ActionEvent> {

    public abstract Document getDocument();

    @Override
    public void handle(ActionEvent event) {

    }

    public abstract void show();
}
