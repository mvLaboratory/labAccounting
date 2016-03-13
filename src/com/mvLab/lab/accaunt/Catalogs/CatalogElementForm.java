package com.mvLab.lab.accaunt.catalogs;

import com.mvLab.lab.accaunt.windows.Window;
import com.mvLab.lab.accaunt.windows.WindowManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CatalogElementForm extends Window  implements EventHandler<ActionEvent> {
    private Catalog catalogElement;
    private Class<? extends Catalog> catalogClass;

    public CatalogElementForm(Class<? extends Catalog> catalogClass, String title, int windowWidth, int windowHeight) {
        super(title, windowWidth, windowHeight);
        this.catalogClass = catalogClass;
        this.catalogElement = null;

        addBottomCommandPanel();
    }

    public void addBottomCommandPanel() {
        Button btnOK = new Button("OK");
        btnOK.setId("ok");
        btnOK.setOnAction(this);
        btnOK.setMinWidth(50);
        btnOK.setDefaultButton(true);
        addBottomElement(btnOK);

        Button btnSave = new Button("Save");
        btnSave.setId("save");
        btnSave.setOnAction(this);
        btnSave.setMinWidth(50);
        addBottomElement(btnSave);

        Button btnClose = new Button("Close");
        btnClose.setId("close");
        btnClose.setCancelButton(true);
        btnClose.setMinWidth(50);
        btnClose.setOnAction(this);
        addBottomElement(btnClose);
    }

    private void saveCatalogElement() {
        //TODO validate input
        if (catalogElement == null) {
            try {
                catalogElement = catalogClass.newInstance();

            }
            catch (Exception e) {
                WindowManager.openErrorWindow("Unable to create new catalog element! \n" + e.toString());
            }
        }
        HashMap<String, String> formInputs = getInputValues();

        ArrayList<Class> classes = new ArrayList<Class>();
        classes.add(catalogClass);
        classes.add(catalogClass.getSuperclass());

        for (Class catClass : classes) {
            for (Map.Entry<String, String> formInput : formInputs.entrySet()) {
                try {
                    Field setterFld = catClass.getDeclaredField(formInput.getKey().toLowerCase());
                    if (catalogElement.isServiceField(setterFld.getName()))
                        continue;
                    Method setter = catClass.getMethod("set" + formInput.getKey(), setterFld.getType());
                    setter.invoke(catClass.cast(catalogElement), formInput.getValue());
                } catch (Exception e) {

                    //TODO handle exception
                }
            }
        }
        catalogElement.save();
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() instanceof Button) {
            Button source = (Button) event.getSource();
            String buttonID = source.idProperty().getValue();
            if (buttonID.equals("close")) {
                close();
            }
            else if (buttonID.equals("ok")) {
                saveCatalogElement();
                close();
            }
            else if (buttonID.equals("save")) {
                saveCatalogElement();
                catalogElement.readElement();
                fillElements(catalogElement.getElementFields());
            }
        }
    }
}
