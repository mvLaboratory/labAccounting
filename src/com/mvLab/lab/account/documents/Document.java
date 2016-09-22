package com.mvLab.lab.account.documents;

import com.mvLab.lab.account.DB_Manager;
import com.mvLab.lab.account.windows.interfaces.Header;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "DOCUMENT")
@Inheritance(strategy= InheritanceType.JOINED)
public abstract class Document implements Header, Serializable {
    @Id
    @GeneratedValue
    @Column(name = "docNumber")
    private Integer number;

    public abstract Integer getNumber();

    public void setNumber(Integer number) {
        this.number = number;
    }

    public abstract Date getDate();

    public abstract void setDate(Date name);

    public abstract UUID getUuid();

    public abstract void setUuid(UUID uuid);

    public abstract void setNewUUID();

    public abstract Boolean isNew();

    public void save() {
        if (!isNew()) {
            DB_Manager.getInstance().updateDocumentElement(this);
        }
        else {
            setNewUUID();
            this.setNumber(DB_Manager.getInstance().saveDocumentElement(this));
        }
    }

    public String getHeader() {
        if (isNew()) {
            return "*new ";
        }
        else {
            return getNumber() == null ? "*" : getNumber() + " from " + getDate();
        }
    }

}
