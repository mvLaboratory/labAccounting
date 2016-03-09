package com.mvLab.lab.accaunt.Catalogs;

import com.mvLab.lab.accaunt.DB_Helper;

import java.util.UUID;

public class Catalog {
    private UUID uuid;

    public Catalog() {
        this.uuid = UUID.randomUUID();
    }

    public void save() {
        //this.getClass().cast(this);
        DB_Helper.addReagentCatalogElement(this);
    }

    public void readElement(Catalog element) {

    }
}
