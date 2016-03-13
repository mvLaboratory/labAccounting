package com.mvLab.lab.accaunt.catalogs;

import com.mvLab.lab.accaunt.DB_Manager;

import java.util.HashMap;
import java.util.UUID;

public class Catalog {
    private UUID uuid;

    public Catalog() {
        this.uuid = UUID.randomUUID();
    }

    public Catalog(String uuid) {
        if (uuid == null || uuid.isEmpty())
            this.uuid = UUID.randomUUID();
        else
            this.uuid = UUID.fromString(uuid);
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void save() {
        //this.getClass().cast(this);
        DB_Manager.addReagentCatalogElement(this);
    }

    public void readElement() {
        DB_Manager.readCatalogElement(this);
    }

    public HashMap<String, Object> getElementFields() {
        HashMap<String, Object> fields = new HashMap<String, Object>();

        //TODO fill the fields

        return fields;
    }
}
