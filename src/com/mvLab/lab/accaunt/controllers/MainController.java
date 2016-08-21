package com.mvLab.lab.accaunt.controllers;

import com.mvLab.lab.accaunt.WindowManager;

public class MainController {
    public void reagentButtonOnClick()  {
            WindowManager.getInstance().openReagentCatalogListForm();
    }

    public void menuOnExitClick() {
        System.exit(0);
    }
}
