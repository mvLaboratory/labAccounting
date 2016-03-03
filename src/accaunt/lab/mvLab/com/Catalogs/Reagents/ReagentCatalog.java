package accaunt.lab.mvLab.com.Catalogs.Reagents;

/**
 * Created by mv on 03.03.2016.
 */
public class ReagentCatalog {
    private int id;
    private String name;
    private String description;

    public ReagentCatalog(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
