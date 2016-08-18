package com.mvLab.lab.accaunt;

import com.mvLab.lab.accaunt.windows.WindowManager;

import java.io.IOException;

public class Controller {
    public void reagentButtonOnClick() {
        try {
            WindowManager.openReagentCatalogListForm();
        }
        catch (IOException e) {
            //TODO handle exception
        }

       // WindowManager.openErrorWindow("222");
    }

    public void menuOnExitClick() {
        System.exit(0);
    }
}
