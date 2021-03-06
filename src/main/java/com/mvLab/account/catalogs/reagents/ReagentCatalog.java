package com.mvLab.account.catalogs.reagents;

import com.mvLab.account.DB_Manager;
import com.mvLab.account.catalogs.Catalog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "REAGENTS")
public class ReagentCatalog extends Catalog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name = "";

    @Column(name = "uuid")
    @Type(type="uuid-char")
    private UUID uuid;

    @Column(name = "description")
    private String description = "";

    @Column(name = "precursor")
    private boolean precursor = false;

    public ReagentCatalog() {

    }

    public ReagentCatalog(Integer id, String name, String uuid) {
        this.id = id;
        this.name = name;

        if (uuid == null || uuid.isEmpty())
            this.uuid = UUID.randomUUID();
        else
            this.uuid = UUID.fromString(uuid);
    }

    public ReagentCatalog(Integer id, String name, String description, UUID uuid) {
        this.id = id;
        this.name = name;

        if (uuid == null) {
            this.uuid = UUID.randomUUID(); }
        else {
            this.uuid = uuid;
        }

        this.description = description;
    }

    public ReagentCatalog(Integer id, String name, String description, String uuid) {
        this(id, name, uuid);
        this.description = description;
    }

    @Override
    public Integer getId() {
        if (id != null && id == 0)
            return null;
        else
            return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public UUID getUuid() {
        return uuid;
    }

    @Override
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public void setNewUUID() {
        this.uuid = UUID.randomUUID();
    }

    public boolean isPrecursor() {
        return precursor;
    }

    public void setPrecursor(boolean precursor) {
        this.precursor = precursor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Boolean isNew() {
        return id == null || id.toString().isEmpty() || id == 0;
    }

    public ReagentCatalog getElement() {
        return this;
    }

    @Override
    public void readElement() {
        DB_Manager.getInstance().readReagentCatalogElement(this.getId());
    }

    public static ObservableList<ReagentCatalog> getCatalogData() {
        ObservableList<ReagentCatalog> catalogData = FXCollections.observableArrayList();
        catalogData.addAll(DB_Manager.readReagentCatalog());
//        List catalog = DB_Manager.readReagentCatalog();
//        ArrayList<HashMap<String, Object>> catalogElements = DB_Manager.ReadReagentCatalog();
//        for (HashMap<String, Object> element : catalogElements) {
//            catalogData.add(new ReagentCatalog((Integer) element.get("id"), (String)element.get("name"), (String)element.get("description"), (String)element.get("uuid")));
//        }
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
        if (!(o instanceof ReagentCatalog)) return false;

        ReagentCatalog that = (ReagentCatalog) o;

        if (isPrecursor() != that.isPrecursor()) return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (getUuid() != null ? !getUuid().equals(that.getUuid()) : that.getUuid() != null) return false;
        return getDescription() != null ? getDescription().equals(that.getDescription()) : that.getDescription() == null;

    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getUuid() != null ? getUuid().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (isPrecursor() ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}
