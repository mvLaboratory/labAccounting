package com.mvLab.account.catalogs;

import com.mvLab.account.DB_Manager;
import com.mvLab.account.windows.interfaces.Header;

import java.util.UUID;

public abstract class Catalog implements Header {

    public abstract Integer getId();

    public abstract void setId(Integer id);

    public abstract String getName();

    public abstract void setName(String name);

    public abstract UUID getUuid();

    public abstract void setUuid(UUID uuid);

    public abstract void setNewUUID();

    public abstract Boolean isNew();

    public void save() {
        if (!isNew())
            DB_Manager.getInstance().updateCatalogElement(this);
        else {
            setNewUUID();
            this.setId(DB_Manager.getInstance().saveCatalogElement(this));
        }
    }

    public abstract void readElement();

    public String getHeader() {
        if (isNew()) {
            return "*new ";
        }
        else {
            return getId() == null ? "*" : getId() + ": " + getName().toLowerCase();
        }
    }
}
