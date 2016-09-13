package com.mvLab.lab.account.catalogs;

import com.mvLab.lab.account.windows.MV_Window;

public class CatalogListForm extends MV_Window {
    public CatalogListForm() {

    }

    public CatalogListForm(String title, int windowWidth, int windowHeight) {
        super(title, windowWidth, windowHeight);
        setCommandPanelsUsege(false, false, true, false);
    }
}
