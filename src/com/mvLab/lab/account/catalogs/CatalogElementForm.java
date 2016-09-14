package com.mvLab.lab.account.catalogs;

import com.mvLab.lab.account.windows.MV_Window;
import com.mvLab.lab.account.windows.Showable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public abstract class CatalogElementForm extends MV_Window implements EventHandler<ActionEvent>, Showable {
 //   private Catalog catalogElement;


//    public Catalog getCatalogElement() {
//        return catalogElement;
//    }

    public abstract Catalog getCatalogElement();
// {
//        return catalogElement;
//    }


    @Override
    public void handle(ActionEvent event) {

    }
}
