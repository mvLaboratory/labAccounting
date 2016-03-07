package com.mvLab.lab.accaunt;

import com.mvLab.lab.accaunt.Catalogs.Reagents.ReagentCatalogElementForm;

import java.util.ArrayList;

public class WindowManager {
    public static WindowManager instance;
    private static ArrayList<Window> windowsList = new ArrayList<Window>();

    public static WindowManager getInstance() {
        if (instance == null)
            instance = new WindowManager();

        return instance;
    }

    private WindowManager() {
    }

    public static void displayMainWindow() {

    }

    public static void openWindow() {

    }

    public static void openReagentCatalogElementForm() {
        ReagentCatalogElementForm listForm = new ReagentCatalogElementForm();
        listForm.display();
    }
}