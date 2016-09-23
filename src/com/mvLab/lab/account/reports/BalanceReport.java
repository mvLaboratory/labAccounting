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
        return "Select max(recordID) as id, balance.reagent as reagent, Sum(balance.quantity) as balance from REAGENT_BALANCE balance group by balance.reagent";
    }
}
