package com.mvLab.lab.account.documents.reagentAdmission;

import com.mvLab.lab.account.Main;
import com.mvLab.lab.account.WindowManager;
import com.mvLab.lab.account.controllers.documents.admission.ReagentAdmissionDocumentController;
import com.mvLab.lab.account.documents.DocumentForm;
import com.mvLab.lab.account.windows.InternalWindow;
import com.mvLab.lab.account.windows.interfaces.Showable;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class ReagentAdmissionElementForm extends DocumentForm implements Showable {

    private ReagentAdmission document;
    private double posX, posY;
    private InternalWindow formWindow;
    private BorderPane windowPane;
    private FXMLLoader loader;
    private ReagentAdmissionDocumentController documentController;

    {
        loader = new FXMLLoader(Main.class.getResource("/view/documents/admission/ReagentAdmissionDocumentForm.fxml"));

        try {
            windowPane = loader.load();
            documentController = loader.getController();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ReagentAdmissionElementForm() {
        this.document = new ReagentAdmission();
        this.posX = (WindowManager.getInstance().getStageWidth() / 2) - (windowPane.getPrefWidth() / 2) + 50;
        this.posY = WindowManager.getInstance().getStageHeight() / 4;

        this.posX += (WindowManager.getInstance().getInnerWindowsCount() * 20);
        this.posY += (WindowManager.getInstance().getInnerWindowsCount() * 20);

        newElement = true;
    }

    public ReagentAdmissionElementForm(ReagentAdmission document, double posX, double posY) {
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

        //ReagentElementController controller = loader.getController();
        documentController.setForm(this);
        documentController.setFields();
        documentController.customizeWindow(formWindow);

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
        document.save();
//        document.getRowSet().clear();
//        document.readElement();
        documentController.setFields();
        WindowManager.getInstance().getMainWindow().getController().updateWindowLink(document);
        documentController.setHeader();
    }

    public void closeWindow() {
        if (newElement) {
            WindowManager.getInstance().closeNewAdmissionWindow(formWindow, document, "ReagentAdmissionList");
        }
        else
        {
           WindowManager.getInstance().closeAdmissionWindow(formWindow, document, "ReagentAdmissionList");
        }
    }

    @Override
    public ReagentAdmission getDocument() {
        return document;
    }

}
