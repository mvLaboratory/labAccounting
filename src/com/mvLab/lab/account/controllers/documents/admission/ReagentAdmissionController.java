package com.mvLab.lab.account.controllers.documents.admission;

import com.mvLab.lab.account.WindowManager;
import com.mvLab.lab.account.catalogs.reagents.ReagentCatalog;
import com.mvLab.lab.account.documents.reagentAdmission.ReagentAdmission;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class ReagentAdmissionController<Type> implements EventHandler<MouseEvent>, Callback<TableView<Type>, TableRow<Type>> {
    private TableView table;

    private TableRow<Type> row;

    @FXML
    public void addButtonClick() {
        WindowManager.getInstance().openReagentAdmissionElementForm();
    }

    public void selectRow(ReagentAdmission document, boolean scrollToRow) {
        table.getSelectionModel().select(document);
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
            ReagentAdmission rowData = ((TableRow<ReagentAdmission>) event.getSource()).getItem();
//            //WindowManager.openReagentCatalogElementForm((Catalog) rowData);
//
            ReagentAdmission document = ReagentAdmission.readElement(rowData.getNumber());
            double mouseX = event.getSceneX();
            double mouseY = event.getSceneY();
            WindowManager.getInstance().openReagentAdmissionElementForm(document, mouseX, mouseY);
//        }
//        else if (event.getClickCount() == 1) {
//            // TODO: 02.09.2016 to front main window
////            WindowManager.getInstance().getMainWindow().getRootLayout().toFront();
        }
    }

    public void setTable(TableView table) {
        this.table = table;
    }
}

