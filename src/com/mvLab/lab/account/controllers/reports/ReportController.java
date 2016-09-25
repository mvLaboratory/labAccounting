package com.mvLab.lab.account.controllers.reports;

import com.mvLab.lab.account.DB_Manager;
import com.mvLab.lab.account.catalogs.reagents.ReagentCatalog;
import com.mvLab.lab.account.register.ReagentBalance;
import com.mvLab.lab.account.reports.BalanceReport;
import com.mvLab.lab.account.reports.ReagentUsageReport;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class ReportController {
    @FXML TableView<ReagentBalance> balanceReport;
    @FXML LineChart<Date, Double> usageChart;
    @FXML TableColumn<ReagentBalance, ReagentCatalog> reagentCol;
    @FXML TableColumn<ReagentBalance, Double> balanceCol;

    @FXML
    private void initialize() {
        List<ReagentBalance> balance = DB_Manager.getInstance().readReport(new BalanceReport());
        balanceReport.getItems().addAll(balance);
        reagentCol.setCellValueFactory(new PropertyValueFactory<>("reagent"));
        balanceCol.setCellValueFactory(new PropertyValueFactory<>("balance"));

        ReagentUsageReport report = new ReagentUsageReport();
        Map<ReagentCatalog, List<ReagentUsageReport>> usageTree = report.getUsageTree(DB_Manager.getInstance().readReport(report));

        ReagentCatalog presentReagent = null;
        XYChart.Series<Date, Double> series = new XYChart.Series();
        series.setName("unknown reagent");

        for (Map.Entry<ReagentCatalog, List<ReagentUsageReport>> usageData : usageTree.entrySet()) {
            series = new XYChart.Series();
            series.setName(usageData.getKey().toString());

            for (ReagentUsageReport reportDataRecord : usageData.getValue()) {
                series.getData().add(new XYChart.Data(reportDataRecord.getDate().toString(), reportDataRecord.getUsage()));
            }
            usageChart.getData().add(series);
        }
    }

    @FXML
    private void updateButtonOnClick() {
        balanceReport.getItems().clear();
        balanceReport.getItems().addAll(DB_Manager.getInstance().readReport(new BalanceReport()));
    }

    @FXML
    private void usageUpdateButtonOnClick() {
        usageChart.getData().clear();

        ReagentUsageReport report = new ReagentUsageReport();
        Map<ReagentCatalog, List<ReagentUsageReport>> usageTree = report.getUsageTree(DB_Manager.getInstance().readReport(report));

        ReagentCatalog presentReagent = null;
        XYChart.Series<Date, Double> series = new XYChart.Series();
        series.setName("unknown reagent");

        for (Map.Entry<ReagentCatalog, List<ReagentUsageReport>> usageData : usageTree.entrySet()) {
            series = new XYChart.Series();
            series.setName(usageData.getKey().toString());

            for (ReagentUsageReport reportDataRecord : usageData.getValue()) {
                series.getData().add(new XYChart.Data(reportDataRecord.getDate().toString(), reportDataRecord.getUsage()));
            }
            usageChart.getData().add(series);
        }
    }
}
