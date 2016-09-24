package com.mvLab.lab.account.controllers.reports;

import com.mvLab.lab.account.DB_Manager;
import com.mvLab.lab.account.catalogs.reagents.ReagentCatalog;
import com.mvLab.lab.account.register.ReagentBalance;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.List;


public class ReagentBalanceController {
    @FXML TableView<ReagentBalance> balanceReport;
    @FXML TableColumn<ReagentBalance, ReagentCatalog> reagentCol;
    @FXML TableColumn<ReagentBalance, Double> balanceCol;

    @FXML
    private void initialize() {
        List<ReagentBalance> balance = DB_Manager.getInstance().readReagentBalance();
        balanceReport.getItems().addAll(balance);
        reagentCol.setCellValueFactory(new PropertyValueFactory<>("reagent"));
        balanceCol.setCellValueFactory(new PropertyValueFactory<>("balance"));
    }

    @FXML
    private void updateButtonOnClick() {
        balanceReport.getItems().clear();
        balanceReport.getItems().addAll(DB_Manager.getInstance().readReagentBalance());
    }
}
