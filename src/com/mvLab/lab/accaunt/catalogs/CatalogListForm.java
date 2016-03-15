package com.mvLab.lab.accaunt.catalogs;

import com.mvLab.lab.accaunt.windows.Window;

public class CatalogListForm extends Window {

    public CatalogListForm(String title, int windowWidth, int windowHeight) {
        super(title, windowWidth, windowHeight);
        setCommandPanelsUsege(false, false, true, false);
    }
}
