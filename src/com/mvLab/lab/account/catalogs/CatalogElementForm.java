package com.mvLab.lab.account.catalogs;

import com.mvLab.lab.account.windows.MV_Window;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class CatalogElementForm extends MV_Window implements EventHandler<ActionEvent> {
    private Catalog catalogElement;
//    private Class<? extends Catalog> catalogClass;

    public CatalogElementForm() {

    }

    public CatalogElementForm(Class<? extends Catalog> catalogClass, String title, int windowWidth, int windowHeight) {
//        super(title, windowWidth, windowHeight);
//        this.catalogClass = catalogClass;
//        this.catalogElement = null;
//
//        addBottomCommandPanel();
//        setCommandPanelsUsege(false, false, false, true);
    }

    public Catalog getCatalogElement() {
        return catalogElement;
    }

//    public void setCatalogElement(Catalog catalogElement) {
//        this.catalogElement = catalogElement;
//    }

    public void addBottomCommandPanel() {
//        Button btnOK = new Button("OK");
//        btnOK.setId("ok");
//        btnOK.setOnAction(this);
//        btnOK.setMinWidth(50);
//        btnOK.setDefaultButton(true);
//        addBottomElement(btnOK);
//
//        Button btnSave = new Button("Save");
//        btnSave.setId("save");
//        btnSave.setOnAction(this);
//        btnSave.setMinWidth(50);
//        addBottomElement(btnSave);
//
//        Button btnClose = new Button("Close");
//        btnClose.setId("close");
//        btnClose.setCancelButton(true);
//        btnClose.setMinWidth(50);
//        btnClose.setOnAction(this);
//        addBottomElement(btnClose);
    }

    private void saveCatalogElement() {
//        //TODO validate input
//        if (catalogElement == null) {
//            try {
//                catalogElement = catalogClass.newInstance();
//
//            }
//            catch (Exception e) {
//                WindowManager.openErrorWindow("Unable to create new catalog element! \n" + e.toString());
//            }
//        }
//        HashMap<String, String> formInputs = getInputValues();
//
//        ArrayList<Class> classes = new ArrayList<Class>();
//        classes.add(catalogClass);
//        classes.add(catalogClass.getSuperclass());
//
//        for (Class catClass : classes) {
//            for (Map.Entry<String, String> formInput : formInputs.entrySet()) {
//                try {
//                    Field setterFld = catClass.getDeclaredField(formInput.getKey().toLowerCase());
//                    if (catalogElement.isServiceField(setterFld.getName()))
//                        continue;
//                    Method setter = catClass.getMethod("set" + formInput.getKey(), setterFld.getType());
//                    setter.invoke(catClass.cast(catalogElement), formInput.getValue());
//                } catch (Exception e) {
//
//                    //TODO handle exception
//                }
//            }
//        }
//        catalogElement.save();
    }

//    public void elementToForm() {
////        catalogElement.readElement();
////        fillElements(catalogElement.getElementFields());
//    }

    @Override
    public void handle(ActionEvent event) {
//        if (event.getSource() instanceof Button) {
//            Button source = (Button) event.getSource();
//            String buttonID = source.idProperty().getValue();
//            if (buttonID.equals("close")) {
//                close();
//            }
//            else if (buttonID.equals("ok")) {
//                saveCatalogElement();
//                close();
//            }
//            else if (buttonID.equals("save")) {
//                saveCatalogElement();
//                catalogElement.readElement();
//                fillElements(catalogElement.getElementFields());
//            }
//        }
    }
}
