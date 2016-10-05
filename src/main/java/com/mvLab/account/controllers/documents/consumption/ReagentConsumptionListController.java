package com.mvLab.account.controllers.documents.consumption;

import com.mvLab.account.WindowManager;
import com.mvLab.account.documents.reagentConsumption.ReagentConsumption;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class ReagentConsumptionListController<Type> implements EventHandler<MouseEvent>, Callback<TableView<Type>, TableRow<Type>> {
    private TableView table;

    private TableRow<Type> row;

    @FXML
    public void addButtonClick() {
        WindowManager.getInstance().openReagentConsumptionElementForm();
    }

    public void selectRow(ReagentConsumption document, boolean scrollToRow) {
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
            ReagentConsumption rowData = ((TableRow<ReagentConsumption>) event.getSource()).getItem();
//            //WindowManager.openReagentCatalogElementForm((Catalog) rowData);
//
            ReagentConsumption document = ReagentConsumption.readElement(rowData.getNumber());
            double mouseX = event.getSceneX();
            double mouseY = event.getSceneY();
            WindowManager.getInstance().openReagentConsumptionElementForm(document, mouseX, mouseY);
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

