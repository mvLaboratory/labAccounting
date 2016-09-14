package com.mvLab.lab.account.documents.reagentAdmission;

import com.mvLab.lab.account.DB_Manager;
import com.mvLab.lab.account.catalogs.reagents.ReagentCatalog;
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

//    @Column(name = "posted")
//    private boolean posted;
//
//    @Column(name = "documentSum")
//    private double documentSum;

    @OneToMany(mappedBy="document")
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
    public String getHeader() {
        if (isNew()) {
            return "*new Admission";
        }
        else {
            return "Admission #" + super.getHeader();
        }
    }
}
