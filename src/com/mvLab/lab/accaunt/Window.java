package com.mvLab.lab.accaunt;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Window {
    private Stage window;
    private String title;
    private int windowMinWidth, windowWidth, windowHeight;
    private GridPane centerLayout;
    private HBox topCommandPanel, bottomCommandPannel;
    private VBox leftCommandPanel,rightCommandPannel;
    private BorderPane mainLayout;
    private ArrayList<Node> centralElements, leftElements, rightElements, topElements, bottomElements;

    public Window(Stage window, String title, int windowWidth, int windowHeight) {
        this.window = window;
        this.title = title;
        this.windowMinWidth = 300;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;

        prepare();
    }

    public Window(String title, int windowWidth, int windowHeight) {
        this.window = new Stage();
        this.title = title;
        this.windowMinWidth = 300;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;

        prepare();
    }

    private void prepare() {
        window.setTitle(title);
        window.setMinWidth(windowMinWidth);

        centerLayout = new GridPane();
        centerLayout.setPadding(new Insets(20, 20, 20, 20));
        centerLayout.setVgap(8);
        centerLayout.setHgap(10);
        centralElements = new  ArrayList<Node>();

        mainLayout = new BorderPane();
    }

    public void addCenterElement(Node element, int col, int row) {
        GridPane.setConstraints(element, col, row);
        centralElements.add(element);
    }

    public void display() {
        centerLayout.getChildren().addAll(centralElements);

        mainLayout.setCenter(centerLayout);
        mainLayout.setBottom(bottomCommandPannel);
        mainLayout.setTop(topCommandPanel);
        mainLayout.setLeft(leftCommandPanel);
        mainLayout.setRight(rightCommandPannel);

        Scene scene = new Scene(mainLayout, windowWidth, windowHeight);
        window.setScene(scene);
        window.show();
    }
}
