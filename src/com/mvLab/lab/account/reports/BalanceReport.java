package com.mvLab.lab.account.reports;

import com.mvLab.lab.account.catalogs.reagents.ReagentCatalog;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class BalanceReport implements Serializable {
    @Id
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name="reagent")
    private ReagentCatalog reagent;

    @Column(name = "balance")
    private double balance;

    public BalanceReport() {
    }

    public BalanceReport(ReagentCatalog reagent, double balance) {
        this.reagent = reagent;
        this.balance = balance;
    }

    public ReagentCatalog getReagent() {
        return reagent;
    }

    public void setReagent(ReagentCatalog reagent) {
        this.reagent = reagent;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static String getQueryString() {
        String queryString = "Select max(balance.ID) as id, balance.reagent as reagent, Sum(balance.balance) as balance ";
        queryString = queryString + " from (Select";
        queryString = queryString + " max(recordID) as id, balanceADD.reagent as reagent, Sum(balanceADD.quantity) as balance";
        queryString = queryString + " from REAGENT_BALANCE balanceADD where DIRECTION = 1 group by balanceADD.reagent";
        queryString = queryString + " Union all";
        queryString = queryString + " Select";
        queryString = queryString + " max(recordID) as id, balanceCons.reagent as reagent, Sum(balanceCons.quantity) * -1 as balance";
        queryString = queryString + " from REAGENT_BALANCE balanceCons where DIRECTION = 0 group by balanceCons.reagent";
        queryString = queryString + ") balance";
        queryString = queryString + " group by balance.reagent";

        return queryString;
    }
}
