package com.mvLab.account.documents.reagentAdmission;

import com.mvLab.account.DB_Manager;
import com.mvLab.account.catalogs.reagents.ReagentCatalog;
import com.mvLab.account.documents.Savable;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "REAGENT_ADMISSION_TABLE_PART_ROW")
public class ReagentAdmissionTablePartRow implements Serializable, Savable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rowID")
    private int rowID;

    @ManyToOne
    @JoinColumn(name="document")
    private ReagentAdmission document;

    @Column(name = "rowNumber")
    private int rowNumber;

    @ManyToOne
    @JoinColumn(name="reagent")
    private ReagentCatalog reagent;

    @Column(name = "quantity")
    private double quantity;

    @Column(name = "price")
    private double price;

    @Column(name = "sum")
    private double sum;

    public ReagentAdmissionTablePartRow() {
    }

    public ReagentAdmissionTablePartRow(int rowID, ReagentAdmission document, int rowNumber, ReagentCatalog reagent, double quantity, double price, double sum) {
        this.rowID = rowID;
        this.document = document;
        this.rowNumber = rowNumber;
        this.reagent = reagent;
        this.quantity = quantity;
        this.price = price;
        this.sum = sum;
    }

    public int getRowID() {
        return rowID;
    }

    public void setRowID(int rowID) {
        this.rowID = rowID;
    }

    public ReagentAdmission getDocument() {
        return document;
    }

    public void setDocument(ReagentAdmission document) {
        this.document = document;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public boolean isNew() {
        return getRowID() == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReagentAdmissionTablePartRow)) return false;

        ReagentAdmissionTablePartRow that = (ReagentAdmissionTablePartRow) o;

        if (getRowID() != that.getRowID()) return false;
        if (Double.compare(that.getQuantity(), getQuantity()) != 0) return false;
        if (Double.compare(that.getPrice(), getPrice()) != 0) return false;
        if (Double.compare(that.getSum(), getSum()) != 0) return false;
        if (getDocument() != null ? !getDocument().equals(that.getDocument()) : that.getDocument() != null)
            return false;
        return getReagent() != null ? getReagent().equals(that.getReagent()) : that.getReagent() == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getRowID();
        result = 31 * result + (getDocument() != null ? getDocument().hashCode() : 0);
        result = 31 * result + (getReagent() != null ? getReagent().hashCode() : 0);
        temp = Double.doubleToLongBits(getQuantity());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getPrice());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getSum());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public void save() {
        if (isNew()) {
            setRowID(DB_Manager.getInstance().saveElement(this));
        }
        else {
            DB_Manager.getInstance().updateElement(this);
        }
    }

    public void delete() {
        DB_Manager.getInstance().deleteElement(this);
    }
}
