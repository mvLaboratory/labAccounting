package com.mvLab.lab.accaunt.catalogs.Reagents;

import com.mvLab.lab.accaunt.catalogs.Catalog;
import com.mvLab.lab.accaunt.windows.WindowManager;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

public class ReagentCatalog extends Catalog {

    private String description;

    public ReagentCatalog() {
    }

    public ReagentCatalog(Integer id, String name, String description) {
        super(id, name);
        this.description = description;
    }

    public ReagentCatalog(Integer id, String name, String description, String uuid) {
        super(id, name, uuid);
        this.description = description;
    }

    public ReagentCatalog getElement() {
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public HashMap<String, Object> getElementFields() {
        HashMap<String, Object> fields = new HashMap<String, Object>();

        ArrayList<Class> classes = new ArrayList<Class>();
        classes.add(this.getClass());
        classes.add(this.getClass().getSuperclass());

        for (Class catClass : classes) {
            for (Field catFld : catClass.getDeclaredFields()) {
                String fldName = catFld.getName();
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
}
