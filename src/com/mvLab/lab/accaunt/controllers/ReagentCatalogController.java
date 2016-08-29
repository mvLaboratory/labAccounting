package com.mvLab.lab.accaunt.controllers;

import br.com.supremeforever.suprememdiwindow.MDICanvas;
import br.com.supremeforever.suprememdiwindow.MDIWindow;
import com.mvLab.lab.accaunt.catalogs.Catalog;
import com.mvLab.lab.accaunt.WindowManager;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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
            //Type rowData = ((TableRow<Type>) event.getSource()).getItem();
            //WindowManager.openReagentCatalogElementForm((Catalog) rowData);
            MDICanvas canvas = new MDICanvas();
            try {
                int i = 0;

                //canvas.setPrefSize(500, 500);
                canvas.setAlignment(Pos.BASELINE_LEFT);

                AnchorPane root = new AnchorPane();
                Label label = new Label("sdsdsds");
                root.getChildren().add(label);
                MDIWindow mDIWindow = new MDIWindow("myUnicId" + i, new ImageView("http://icongal.com/gallery/icon/44614/32/signal_cell_sound_mobile_call_ringtone_phone"), "My Window Title " + i, root);
                canvas.addMDIWindow(mDIWindow);
                i++;
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            WindowManager.getInstance().getMainWindow().getRootLayout().getChildren().add(canvas);
        }
    }
}

