package com.mvLab.lab.accaunt.catalogs;

import com.mvLab.lab.accaunt.DB_Manager;
import com.mvLab.lab.accaunt.WindowManager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Catalog {
    protected Integer id;
    protected String name = "";
    protected UUID uuid;
    protected String tableName;
    protected ArrayList<String> serviceFields = new ArrayList<String>() {{
        add("tableName");
        add("serviceFields");
    }};

    public Catalog(Integer id, String name) {
        this.id = id;
        this.name = name;
        //this.uuid = UUID.randomUUID();
    }

    public Catalog(Integer id, String name, String uuid) {
        this.id = id;
        this.name = name;

        if (uuid == null || uuid.isEmpty())
            this.uuid = UUID.randomUUID();
        else
            this.uuid = UUID.fromString(uuid);
    }

    public Catalog() {
        //this.uuid = UUID.randomUUID();
    }

    public Integer getId() {
        if (id != null && id == 0)
            return null;
        else
            return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void save() {
        if (!isNew())
            DB_Manager.updateCatalogElement(this);
        else {
            setNewUUID();
            DB_Manager.addReagentCatalogElement(this);
            //TODO rewrite existing element
        }
    }

    public void readElement() {
        DB_Manager.readCatalogElement(this);
    }

    public HashMap<String, Object> getElementFields() {
        HashMap<String, Object> fields = new HashMap<String, Object>();

        ArrayList<Class> classes = new ArrayList<Class>();
        classes.add(this.getClass());
        //classes.add(element.getClass().getSuperclass());

        for (Class catClass : classes) {
            for (Field catFld : catClass.getDeclaredFields()) {
                String fldName = catFld.getName();
                if (isServiceField(fldName))
                    continue;
                fldName = fldName.substring(0, 1).toUpperCase() + fldName.substring(1);
                try {
                    fields.put(fldName, this.getClass().getMethod("get" + fldName).invoke(this));
                } catch (Exception e) {
                    //TODO Handle exception
                    WindowManager.openErrorWindow(e.toString());
                }
            }
        }
        return fields;
    }
    public String getHeader() {
        return getId() + ": " + getName();
    }
}
