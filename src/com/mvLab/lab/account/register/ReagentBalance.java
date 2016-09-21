package com.mvLab.lab.account.register;

import com.mvLab.lab.account.catalogs.reagents.ReagentCatalog;
import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

public class ReagentBalance implements Register, Serializable {
    @Column(name = "date")
    private Date date;

    @Column(name = "reagent")
    private ReagentCatalog reagent;

    @Column(name = "quantity")
    private double quantity;

    public ReagentBalance() {
    }

    public ReagentBalance(Date date, ReagentCatalog reagent, double quantity) {
        this.date = date;
        this.reagent = reagent;
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ReagentCatalog getReagent() {
        return reagent;
    }

    public void setReagent(ReagentCatalog reagent) {
        this.reagent = reagent;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
