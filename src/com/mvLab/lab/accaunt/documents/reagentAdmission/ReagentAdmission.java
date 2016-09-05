package com.mvLab.lab.accaunt.documents.reagentAdmission;

import com.mvLab.lab.accaunt.DB_Manager;
import com.mvLab.lab.accaunt.WindowManager;
import com.mvLab.lab.accaunt.catalogs.Catalog;
import com.mvLab.lab.accaunt.documents.Document;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

public class ReagentAdmission extends Document {

    private String description = "";

    {
        this.tableName = "Reagents";
    }

    public ReagentAdmission() {
    }

    public ReagentAdmission(Integer id, String name, String description) {
        super(id, name);
        this.description = description;
    }

    public ReagentAdmission(Integer id, String name, String description, String uuid) {
        super(id, name, uuid);
        this.description = description;
    }

    public ReagentAdmission getElement() {
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static ObservableList<ReagentAdmission> getCatalogData() {
        ObservableList<ReagentAdmission> catalogData = FXCollections.observableArrayList();
        ArrayList<HashMap<String, Object>> catalogElements = DB_Manager.ReadReagentCatalog();
        for (HashMap<String, Object> element : catalogElements) {
            catalogData.add(new ReagentAdmission((Integer) element.get("id"), (String)element.get("name"), (String)element.get("description"), (String)element.get("uuid")));
        }
        return catalogData;
    }

    @Override
    public String getHeader() {
        if (isNew()) {
            return "*new Reagent";
        }
        else {
            return "Reagent #" + super.getHeader();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReagentAdmission)) return false;

        ReagentAdmission that = (ReagentAdmission) o;

        //if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (getUuid() != null ? !getUuid().equals(that.getUuid()) : that.getName() != null) return false;
        if (getTableName() != null ? !getTableName().equals(that.getTableName()) : that.getTableName() != null)
            return false;
        return getDescription() != null ? getDescription().equals(that.getDescription()) : that.getDescription() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getUuid() != null ? getUuid().hashCode() : 0);
        result = 31 * result + (getTableName() != null ? getTableName().hashCode() : 0);

        return result;
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
}
