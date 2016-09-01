package com.mvLab.lab.accaunt.windows;

import com.mvLab.lab.accaunt.Main;
import com.mvLab.lab.accaunt.WindowManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Iterator;

public class InternalWindow extends Region {

    public void setRoot(Node node) {
        getChildren().add(node);
    }

    public void makeFocusable() {
        this.setOnMouseClicked(mouseEvent -> {
            toFront();
        });
    }

    //just for encapsulation
    private static class Delta {
        double x, y;
    }

    //we can select nodes that react drag event
    public void makeDragable(Node what) {
        final Delta dragDelta = new Delta();
        what.setOnMousePressed(mouseEvent -> {
            dragDelta.x = getLayoutX() - mouseEvent.getScreenX();
            dragDelta.y = getLayoutY() - mouseEvent.getScreenY();
            //also bring to front when moving
            toFront();
        });
        what.setOnMouseDragged(mouseEvent -> {
            setLayoutX(mouseEvent.getScreenX() + dragDelta.x);
            setLayoutY(mouseEvent.getScreenY() + dragDelta.y);
        });
    }

        //current state
    private boolean RESIZE_BOTTOM;
    private boolean RESIZE_RIGHT;

    public void makeResizable(double mouseBorderWidth) {
        this.setOnMouseMoved(mouseEvent -> {
            //local window's coordiantes
            double mouseX = mouseEvent.getX();
            double mouseY = mouseEvent.getY();
            //window size
            double width = this.boundsInLocalProperty().get().getWidth();
            double height = this.boundsInLocalProperty().get().getHeight();
            //if we on the edge, change state and cursor
            if (Math.abs(mouseX - width) < mouseBorderWidth
                    && Math.abs(mouseY - height) < mouseBorderWidth) {
                RESIZE_RIGHT = true;
                RESIZE_BOTTOM = true;
                this.setCursor(Cursor.NW_RESIZE);
            } else {
                RESIZE_BOTTOM = false;
                RESIZE_RIGHT = false;
                this.setCursor(Cursor.DEFAULT);
            }

        });
        this.setOnMouseDragged(mouseEvent -> {
            //resize root
            Region region = (Region) getChildren().get(0);
            //resize logic depends on state
            if (RESIZE_BOTTOM && RESIZE_RIGHT) {
                region.setPrefSize(mouseEvent.getX(), mouseEvent.getY());
            } else if (RESIZE_RIGHT) {
                region.setPrefWidth(mouseEvent.getX());
            } else if (RESIZE_BOTTOM) {
                region.setPrefHeight(mouseEvent.getY());
            }
        });
    }

    public static InternalWindow constructWindow(double posX, double posY, String header, BorderPane windowLayout) {
        // content
        InternalWindow internalWindow = new InternalWindow();

        BorderPane titleBar = new BorderPane();
        titleBar.setId("windowHeader");

        Label headerLbl = new Label(header);
        headerLbl.setId("windowHeaderLbl");
        titleBar.setLeft(headerLbl);

        Button closeButton = new Button("x");
        closeButton.setId("windowCloseButton");
        closeButton.setOnAction(event -> {
            WindowManager.getInstance().closeCatalogWindow(internalWindow, "ReagentList");
        });

        titleBar.setRight(closeButton);

        windowLayout.setTop(titleBar);
        internalWindow.setRoot(windowLayout);

        //drag only by title
        internalWindow.makeDragable(titleBar);
        internalWindow.makeDragable(headerLbl);
        internalWindow.makeResizable(20);
        internalWindow.makeFocusable();

        internalWindow.setLayoutX(posX - 50);
        internalWindow.setLayoutY(posY - 50);

        return internalWindow;
    }

}
