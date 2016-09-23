package com.mvLab.lab.account.controllers.reports;

import com.mvLab.lab.account.DB_Manager;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.List;


public class ReagentBalanceController {
    @FXML TableView balanceReport;
    @FXML TableColumn reagentCol;
    @FXML TableColumn balanceCol;

    @FXML
    private void initialize() {
        List balance = DB_Manager.getInstance().readReagentBalance();
        balanceReport.getItems().addAll(balance);
        reagentCol.setCellValueFactory(new PropertyValueFactory("reagent"));
        balanceCol.setCellValueFactory(new PropertyValueFactory("balance"));
    }
}
