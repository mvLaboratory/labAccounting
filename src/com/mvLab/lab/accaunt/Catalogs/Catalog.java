package com.mvLab.lab.accaunt.Catalogs;

import com.mvLab.lab.accaunt.Catalogs.Reagents.ReagentCatalog;
import com.mvLab.lab.accaunt.DB_Helper;

public class Catalog {

    public void save() {
        DB_Helper.addReagentCatalogElement((ReagentCatalog) this);
    }
}
