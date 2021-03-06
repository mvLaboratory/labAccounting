package com.mvLab.account.documents.reagentAdmission;

import com.mvLab.account.DB_Manager;
import com.mvLab.account.documents.Document;
import com.mvLab.account.register.ReagentBalance;
import com.mvLab.account.register.RecordSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "REAGENT_ADMISSION")
@PrimaryKeyJoinColumn(name="Number")
public class ReagentAdmission extends Document implements Serializable {
    @Column(name = "date")
    private Date date;

    @Column(name = "uuid")
    @Type(type="uuid-char")
    private UUID uuid;

    @Column(name = "supplier")
    private String supplier = "";

    @Column(name = "posted")
    private boolean posted = false;

    @OneToMany(fetch = FetchType.EAGER, mappedBy="document")
    @OrderBy("rowNumber ASC")
    private Set<ReagentAdmissionTablePartRow> rowSet  = new HashSet<>();

    @Transient
    private Set<ReagentAdmissionTablePartRow> delRowSet  = new HashSet<>();

    public ReagentAdmission() {
    }

    public ReagentAdmission(Integer number, Date date, UUID uuid) {
        setNumber(number);
        this.date = date;
        this.uuid = uuid;
    }

    public ReagentAdmission(Integer number, Date date, UUID uuid, Set<ReagentAdmissionTablePartRow> rowSet) {
        setNumber(number);
        this.date = date;
        this.uuid = uuid;
        this.rowSet = rowSet;
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

    public Set<ReagentAdmissionTablePartRow> getDelRowSet() {
        return delRowSet;
    }

    public void setDelRowSet(Set<ReagentAdmissionTablePartRow> delRowSet) {
        this.delRowSet = delRowSet;
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

    public static ReagentAdmission readElement(int number) {
        return (ReagentAdmission) DB_Manager.getInstance().readReagentAdmissionElement(number);
    }

    public static ObservableList<ReagentAdmission> getDocumentData() {
        ObservableList<ReagentAdmission> documentData = FXCollections.observableArrayList();
        documentData.addAll(DB_Manager.getInstance().readReagentAdmission());
        return documentData;
    }

    @Override
    public void save() {
        super.save();
        getDelRowSet().forEach(ReagentAdmissionTablePartRow::delete);
        getDelRowSet().clear();

        getRowSet().forEach(ReagentAdmissionTablePartRow::save);
        //super.save();
    }

    public void post() {
        DB_Manager.getInstance().deleteDocPosts(this);

        RecordSet reagentRS = new RecordSet(this);
        for (ReagentAdmissionTablePartRow row : getRowSet()) {
            reagentRS.add(new ReagentBalance(1, getDate(), this, row.getReagent(), row.getQuantity()));
        }
        reagentRS.save();
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
