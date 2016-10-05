package com.mvLab.account.register;

import com.mvLab.account.catalogs.reagents.ReagentCatalog;
import com.mvLab.account.documents.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "REAGENT_USAGE")
public class ReagentUsage implements Register, Serializable {
    @Id
    @GeneratedValue
    @Column(name = "recordID")
    private int recordID;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name="document")
    private Document document;

    @ManyToOne
    @JoinColumn(name="reagent")
    private ReagentCatalog reagent;

    @Column(name = "quantity")
    private double quantity;

    public ReagentUsage() {
    }

    public ReagentUsage(Date date, Document document, ReagentCatalog reagent, double quantity) {
        this.date = date;
        this.reagent = reagent;
        this.quantity = quantity;
        this.document = document;
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

    public int getRecordID() {
        return recordID;
    }

    public void setRecordID(int recordID) {
        this.recordID = recordID;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}
