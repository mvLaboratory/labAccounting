package com.mvLab.lab.account.documents;

import java.util.Date;
import java.util.UUID;

public abstract class Document {
    public abstract Integer getNumber();

    public abstract void setNumber(Integer number);

    public abstract Date getDate();

    public abstract void setDate(Date name);

    public abstract UUID getUuid();

    public abstract void setUuid(UUID uuid);

    public abstract void setNewUUID();

    public abstract Boolean isNew();

    public void save() {
//        if (!isNew())
//            DB_Manager.getInstance().updateCatalogElement(this);
//        else {
//            setNewUUID();
//            this.setId(DB_Manager.getInstance().saveCatalogElement(this));
//        }
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
