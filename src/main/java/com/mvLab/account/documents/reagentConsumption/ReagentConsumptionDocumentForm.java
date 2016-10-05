package com.mvLab.account.documents.reagentConsumption;

import com.mvLab.account.Main;
import com.mvLab.account.WindowManager;
import com.mvLab.account.controllers.documents.consumption.ReagentConsumptionDocumentController;
import com.mvLab.account.documents.DocumentForm;
import com.mvLab.account.windows.InternalWindow;
import com.mvLab.account.windows.interfaces.Showable;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class ReagentConsumptionDocumentForm extends DocumentForm implements Showable {

    private ReagentConsumption document;
    private double posX, posY;
    private InternalWindow formWindow;
    private BorderPane windowPane;
    private FXMLLoader loader;
    private ReagentConsumptionDocumentController documentController;

    {
        loader = new FXMLLoader(Main.class.getResource("/view/documents/consumption/ReagentConsumptionDocumentForm.fxml"));

        try {
            windowPane = loader.load();
            documentController = loader.getController();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ReagentConsumptionDocumentForm() {
        this.document = new ReagentConsumption();
        this.posX = (WindowManager.getInstance().getStageWidth() / 2) - (windowPane.getPrefWidth() / 2) + 50;
        this.posY = WindowManager.getInstance().getStageHeight() / 4;

        this.posX += (WindowManager.getInstance().getInnerWindowsCount() * 20);
        this.posY += (WindowManager.getInstance().getInnerWindowsCount() * 20);

        newElement = true;
    }

    public ReagentConsumptionDocumentForm(ReagentConsumption document, double posX, double posY) {
        this.document = document;

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
        documentController.setForm(this);
        documentController.setFields();
        documentController.customizeWindow(formWindow);

        WindowManager.getInstance().getMainWindow().getRootLayout().getChildren().add(formWindow);
    }

    public void hide() {
        if (windowPane == null) {
            WindowManager.openErrorWindow("Sorry! Can't open reagent consumption document!");
            return;
        }

        windowPane.setVisible(false);
    }

    @Override
    public void show() {
        if (windowPane == null) {
            WindowManager.openErrorWindow("Sorry! Can't open catalog element!");
            return;
        }

        windowPane.setVisible(true);
        formWindow.focus();
    }

    public void save() {
        document.save();
        documentController.setFields();
        WindowManager.getInstance().getMainWindow().getController().updateWindowLink(document);
        documentController.setHeader();
    }

    public void closeWindow() {
        if (newElement) {
            WindowManager.getInstance().closeNewConsumptionWindow(formWindow, document, "ReagentConsumptionList");
        }
        else
        {
            WindowManager.getInstance().closeConsumptionWindow(formWindow, document, "ReagentConsumptionList");
        }
    }

    @Override
    public ReagentConsumption getDocument() {
        return document;
    }

}
