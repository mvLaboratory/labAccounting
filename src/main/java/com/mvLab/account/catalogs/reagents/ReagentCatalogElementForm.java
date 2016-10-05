package com.mvLab.account.catalogs.reagents;

import com.mvLab.account.Main;
import com.mvLab.account.WindowManager;
import com.mvLab.account.catalogs.CatalogElementForm;
import com.mvLab.account.controllers.catalogs.ReagentElementController;
import com.mvLab.account.windows.InternalWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class ReagentCatalogElementForm extends CatalogElementForm {

    private ReagentCatalog reagentElement;
    private double posX, posY;
    private InternalWindow formWindow;
    private BorderPane windowPane;
    private FXMLLoader loader;
    private ReagentElementController elementController;

    {
        loader = new FXMLLoader(Main.class.getResource("/view/catalog/ReagentCatalogElementForm.fxml"));

        try {
            windowPane = loader.load();
            elementController = loader.getController();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ReagentCatalogElementForm() {
        this.reagentElement = new ReagentCatalog();
        this.posX = (WindowManager.getInstance().getStageWidth() / 2) - (windowPane.getPrefWidth() / 2) + 50;
        this.posY = WindowManager.getInstance().getStageHeight() / 4;

        this.posX += (WindowManager.getInstance().getInnerWindowsCount() * 20);
        this.posY += (WindowManager.getInstance().getInnerWindowsCount() * 20);

        newElement = true;
    }

    public ReagentCatalogElementForm(ReagentCatalog reagentElement, double posX, double posY) {
        this.reagentElement = reagentElement;

        this.posX = (WindowManager.getInstance().getStageWidth() / 2) - (windowPane.getPrefWidth() / 2) + 50;
        this.posY = WindowManager.getInstance().getStageHeight() / 4;

        this.posX += (WindowManager.getInstance().getInnerWindowsCount() * 20);
        this.posY += (WindowManager.getInstance().getInnerWindowsCount() * 20);
    }

    @Override
    public void display() throws IOException {
        if (windowPane == null) {
            WindowManager.openErrorWindow("Sorry! Can't open catalog element!");
            return;
        }


        formWindow = InternalWindow.constructWindow(posX, posY, windowPane);

        //ReagentElementController controller = loader.getController();
        elementController.setForm(this);
        elementController.setFields();
        elementController.customizeWindow(formWindow);

        WindowManager.getInstance().getMainWindow().getRootLayout().getChildren().add(formWindow);


    }

    public void hide() {
        if (windowPane == null) {
            WindowManager.openErrorWindow("Sorry! Can't open catalog element!");
            return;
        }

        windowPane.setVisible(false);
    }

    public void show() {
        if (windowPane == null) {
            WindowManager.openErrorWindow("Sorry! Can't open catalog element!");
            return;
        }

        windowPane.setVisible(true);
        formWindow.focus();

    }

    public void save() {
        reagentElement.save();
        reagentElement.readElement();
        elementController.setFields();
        WindowManager.getInstance().getMainWindow().getController().updateWindowLink(reagentElement);
        elementController.setHeader();
        //elementController = loader.getController();
    }

    public void closeWindow() {
        if (newElement) {
            WindowManager.getInstance().closeNewCatalogWindow(formWindow, reagentElement, "ReagentList");
        }
        else
        {
            WindowManager.getInstance().closeCatalogWindow(formWindow, reagentElement, "ReagentList");
        }
        //WindowManager.getInstance().updateReagentCatalogListForm();
    }

    @Override
    public ReagentCatalog getCatalogElement() {
        return reagentElement;
    }

}
