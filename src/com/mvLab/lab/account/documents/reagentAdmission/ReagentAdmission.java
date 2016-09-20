package com.mvLab.lab.account.documents.reagentAdmission;

import com.mvLab.lab.account.DB_Manager;
import com.mvLab.lab.account.documents.Document;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "REAGENT_ADMISSION")
public class ReagentAdmission extends Document implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "number")
    private Integer number;

    @Column(name = "date")
    private Date date;

    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "supplier")
    private String supplier = "";

    @Column(name = "posted")
    private boolean posted = false;

//    @Column(name = "posted")
//    private boolean posted;
//
//    @Column(name = "documentSum")
//    private double documentSum;

    @OneToMany(fetch = FetchType.EAGER, mappedBy="document")
    private Set<ReagentAdmissionTablePartRow> rowSet  = new HashSet<>();

    public ReagentAdmission() {
    }

    public ReagentAdmission(Integer number, Date date, UUID uuid) {
        this.number = number;
        this.date = date;
        this.uuid = uuid;
    }

    public ReagentAdmission(Integer number, Date date, UUID uuid, Set<ReagentAdmissionTablePartRow> rowSet) {
        this.number = number;
        this.date = date;
        this.uuid = uuid;
        this.rowSet = rowSet;
    }

    @Override
    public Integer getNumber() {
        return number;
    }

    @Override
    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public void setDate(Date date) {
        this.date = date;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public boolean isPosted() {
        return posted;
    }

    public void setPosted(boolean posted) {
        this.posted = posted;
    }

    @Override
    public UUID getUuid() {
        return uuid;
    }

    @Override
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public void setNewUUID() {
        this.uuid = UUID.randomUUID();
    }

    public Set<ReagentAdmissionTablePartRow> getRowSet() {
        return rowSet;
    }

    public void setRowSet(Set<ReagentAdmissionTablePartRow> rowSet) {
        this.rowSet = rowSet;
    }

    @Override
    public Boolean isNew() {
        return uuid == null || uuid.toString().isEmpty();
    }

    @Override
    public void readElement() {

    }

    public static ObservableList<ReagentAdmission> getDocumentData() {
        ObservableList<ReagentAdmission> documentData = FXCollections.observableArrayList();
        documentData.addAll(DB_Manager.readReagentAdmission());
        return documentData;
    }

    @Override
    public void save() {
        super.save();
        getRowSet().forEach(ReagentAdmissionTablePartRow::save);
        //super.save();
    }

    @Override
    public String getHeader() {
        if (isNew()) {
            return "*new Admission";
        }
        else {
            return "Admission #" + super.getHeader();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReagentAdmission)) return false;

        ReagentAdmission that = (ReagentAdmission) o;

        if (isPosted() != that.isPosted()) return false;
        if (getDate() != null ? !getDate().equals(that.getDate()) : that.getDate() != null) return false;
        if (getUuid() != null ? !getUuid().equals(that.getUuid()) : that.getUuid() != null) return false;
        return getSupplier() != null ? getSupplier().equals(that.getSupplier()) : that.getSupplier() == null;

    }

    @Override
    public int hashCode() {
        int result = getDate() != null ? getDate().hashCode() : 0;
        result = 31 * result + (getUuid() != null ? getUuid().hashCode() : 0);
        result = 31 * result + (getSupplier() != null ? getSupplier().hashCode() : 0);
        result = 31 * result + (isPosted() ? 1 : 0);
        return result;
    }
}
