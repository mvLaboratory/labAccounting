package com.mvLab.lab.accaunt.documents;

import com.mvLab.lab.accaunt.DB_Manager;
import com.mvLab.lab.accaunt.WindowManager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public abstract class Document {
    protected Integer number;
    protected Date date;
    protected UUID uuid;
    protected String tableName;
    protected ArrayList<String> serviceFields = new ArrayList<String>() {{
        add("tableName");
        add("serviceFields");
    }};

    public Document(Integer number, Date date) {
        this.number = number;
        this.date = date;
        //this.uuid = UUID.randomUUID();
    }

    public Document(Integer number, Date date, String uuid) {
        this.number = number;
        this.date = date;

        if (uuid == null || uuid.isEmpty())
            this.uuid = UUID.randomUUID();
        else
            this.uuid = UUID.fromString(uuid);
    }

    public Document() {
        //this.uuid = UUID.randomUUID();
    }

    public Integer getNumber() {
        if (number != null && number == 0)
            return null;
        else
            return number;
    }

    public void setId(Integer number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Boolean isServiceField(String field) {
        return serviceFields.contains(field);
    }

    public void setNewUUID() {
        this.uuid = UUID.randomUUID();
    }

    public Boolean isNew() {
        return uuid == null || uuid.toString().isEmpty();
    }

    public abstract void save();

    public abstract void readElement();

    public abstract HashMap<String, Object> getElementFields() ;

    public String getHeader() {
        if (isNew()) {
            return "*new ";
        }
        else {
            return getNumber() == null ? "*" : getNumber() + " from " + getDate();
        }
    }

}
