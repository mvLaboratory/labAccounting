package com.mvLab.lab.accaunt.Catalogs.Reagents;

import com.mvLab.lab.accaunt.Catalogs.Catalog;

public class ReagentCatalog extends Catalog {
    private int id;
    private String name;
    private String description;

    public ReagentCatalog() {
    }

    public ReagentCatalog(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public ReagentCatalog getElement() {
        return this;
    }

    public int getId() {
        return id;
    }

//    public void setId(int id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
