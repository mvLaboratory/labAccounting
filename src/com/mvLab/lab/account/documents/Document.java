package com.mvLab.lab.account.documents;

import com.mvLab.lab.account.DB_Manager;
import com.mvLab.lab.account.windows.interfaces.Header;

import java.util.Date;
import java.util.UUID;

public abstract class Document implements Header {
    public abstract Integer getNumber();

    public abstract void setNumber(Integer number);

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

    public abstract void readElement();

    public String getHeader() {
        if (isNew()) {
            return "*new ";
        }
        else {
            return getNumber() == null ? "*" : getNumber() + " from " + getDate();
        }
    }

}
