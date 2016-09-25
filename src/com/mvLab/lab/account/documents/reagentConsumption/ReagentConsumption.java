package com.mvLab.lab.account.documents.reagentConsumption;

import com.mvLab.lab.account.DB_Manager;
import com.mvLab.lab.account.documents.Document;
import com.mvLab.lab.account.register.ReagentBalance;
import com.mvLab.lab.account.register.RecordSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "REAGENT_CONSUMPTION")
@PrimaryKeyJoinColumn(name="Number")
public class ReagentConsumption extends Document implements Serializable {
    @Column(name = "date")
    private Date date;

    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "posted")
    private boolean posted = false;

    @OneToMany(fetch = FetchType.EAGER, mappedBy="document")
    @OrderBy("rowNumber ASC")
    private Set<ReagentConsumptionTablePartRow> rowSet  = new HashSet<>();

    @Transient
    private Set<ReagentConsumptionTablePartRow> delRowSet  = new HashSet<>();

    public ReagentConsumption() {
    }

    public ReagentConsumption(Integer number, Date date, UUID uuid) {
        setNumber(number);
        this.date = date;
        this.uuid = uuid;
    }

    public ReagentConsumption(Integer number, Date date, UUID uuid, Set<ReagentConsumptionTablePartRow> rowSet) {
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

    @Override
    public UUID getUuid() {
        return uuid;
    }

    @Override
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public boolean isPosted() {
        return posted;
    }

    public void setPosted(boolean posted) {
        this.posted = posted;
    }

    public Set<ReagentConsumptionTablePartRow> getRowSet() {
        return rowSet;
    }

    public void setRowSet(Set<ReagentConsumptionTablePartRow> rowSet) {
        this.rowSet = rowSet;
    }

    public Set<ReagentConsumptionTablePartRow> getDelRowSet() {
        return delRowSet;
    }

    public void setDelRowSet(Set<ReagentConsumptionTablePartRow> delRowSet) {
        this.delRowSet = delRowSet;
    }

    @Override
    public void setNewUUID() {
        this.uuid = UUID.randomUUID();
    }

    @Override
    public Boolean isNew() {
        return uuid == null || uuid.toString().isEmpty();
    }

    public static ReagentConsumption readElement(int number) {
        return (ReagentConsumption) DB_Manager.getInstance().readReagentConsumptionElement(number);
    }

    public static ObservableList<ReagentConsumption> getDocumentData() {
        ObservableList<ReagentConsumption> documentData = FXCollections.observableArrayList();
        documentData.addAll(DB_Manager.getInstance().readReagentConsumption());
        return documentData;
    }

    @Override
    public void save() {
        super.save();
        getDelRowSet().forEach(ReagentConsumptionTablePartRow::delete);
        getDelRowSet().clear();

        getRowSet().forEach(ReagentConsumptionTablePartRow::save);
    }

    public void post() {
        RecordSet reagentRS = new RecordSet(this);
        for (ReagentConsumptionTablePartRow row : getRowSet()) {
            reagentRS.add(new ReagentBalance(0, getDate(), this, row.getReagent(), row.getQuantity()));
        }
        reagentRS.save();
    }

    @Override
    public String getHeader() {
        if (isNew()) {
            return "*new Consumption";
        }
        else {
            return "Consumption #" + super.getHeader();
        }
    }


}
