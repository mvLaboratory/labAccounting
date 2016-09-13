package com.mvLab.lab.accaunt.catalogs;

import com.mvLab.lab.accaunt.DB_Manager;
import com.mvLab.lab.accaunt.WindowManager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public abstract class Catalog {
//    protected Integer id;
//    protected String name = "";
//    protected UUID uuid;
//    protected String tableName;

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

    //    protected final ArrayList<String> serviceFields = new ArrayList<String>() {{
//        add("tableName");
//        add("serviceFields");
//    }};

//    public Catalog(Integer id, String name) {
//        this.id = id;
//        this.name = name;
//        //this.uuid = UUID.randomUUID();
//    }
//
//    public Catalog(Integer id, String name, String uuid) {
//        this.id = id;
//        this.name = name;
//
//        if (uuid == null || uuid.isEmpty())
//            this.uuid = UUID.randomUUID();
//        else
//            this.uuid = UUID.fromString(uuid);
//    }
//
//    public Catalog() {
//        //this.uuid = UUID.randomUUID();
//    }

    //    public String getTableName() {
//        return tableName;
//    }

//    public void setTableName(String tableName) {
//        this.tableName = tableName;
//    }

    //    public Boolean isServiceField(String field) {
//        return serviceFields.contains(field);
//    }

//    public abstract HashMap<String, Object> getElementFields();

//    public HashMap<String, Object> getElementFields() {
//        HashMap<String, Object> fields = new HashMap<String, Object>();
//
//        ArrayList<Class> classes = new ArrayList<Class>();
//        classes.add(this.getClass());
//        //classes.add(element.getClass().getSuperclass());
//
//        for (Class catClass : classes) {
//            for (Field catFld : catClass.getDeclaredFields()) {
//                String fldName = catFld.getName();
//                if (isServiceField(fldName))
//                    continue;
//                fldName = fldName.substring(0, 1).toUpperCase() + fldName.substring(1);
//                try {
//                    fields.put(fldName, this.getClass().getMethod("get" + fldName).invoke(this));
//                } catch (Exception e) {
//                    //TODO Handle exception
//                    WindowManager.openErrorWindow(e.toString());
//                }
//            }
//        }
//        return fields;
//    }

}
