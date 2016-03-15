package com.mvLab.lab.accaunt.windows;

import com.mvLab.lab.accaunt.catalogs.Reagents.ReagentCatalogListForm;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Window {
    private Stage window;
    private String title;
    private int windowMinWidth, windowWidth, windowHeight;
    private GridPane centerLayout;
    private HBox topCommandPanel, bottomCommandPanel;
    private VBox leftCommandPanel,rightCommandPanel;
    private BorderPane mainLayout;
    private ArrayList<Node> centralElements, leftElements, rightElements, topElements, bottomElements;
    private Boolean leftCommandsUsage = true, rightCommandUsage = true, topCommandUsage = true, bottomCommandUsage = true;

    public Window(Stage window, String title, int windowWidth, int windowHeight) {
        this.window = window;
        this.title = title;
        this.windowMinWidth = 300;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;

        prepare();
    }

    public Window(String title, int windowWidth, int windowHeight, Boolean modal) {
        this.window = new Stage();
        this.title = title;
        this.windowMinWidth = 300;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;

        if (modal) {
            this.window.initModality(Modality.APPLICATION_MODAL);
        }

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
        centerLayout.setPadding(new Insets(5, 5, 5, 5));
        centerLayout.setVgap(5);
        centerLayout.setHgap(5);
        centralElements = new  ArrayList<Node>();

        topCommandPanel = new HBox();
        topCommandPanel.setSpacing(8);
        topCommandPanel.setPadding(new Insets(10, 10, 10, 10));
        topElements = new  ArrayList<Node>();

        bottomCommandPanel = new HBox();
        bottomCommandPanel.setSpacing(8);
        bottomCommandPanel.setAlignment(Pos.BOTTOM_RIGHT);
        bottomCommandPanel.setPadding(new Insets(10, 10, 10, 10));
        bottomElements = new  ArrayList<Node>();

        leftCommandPanel = new VBox();
        leftCommandPanel.setSpacing(8);
        leftCommandPanel.setPadding(new Insets(10, 10, 10, 10));
        leftElements = new  ArrayList<Node>();

        rightCommandPanel = new VBox();
        rightCommandPanel.setSpacing(8);
        rightCommandPanel.setPadding(new Insets(10, 10, 10, 10));
        rightElements = new  ArrayList<Node>();

        mainLayout = new BorderPane();
    }

    public void addCenterElement(Node element, int col, int row) {
        GridPane.setConstraints(element, col, row);
        GridPane.setVgrow(element, Priority.ALWAYS);
        GridPane.setHgrow(element, Priority.ALWAYS);
        centralElements.add(element);
    }

    public void addTopElement(Node element) {
        topElements.add(element);
    }

    public void addBottomElement(Node element) {
        bottomElements.add(element);
    }

    public void addLeftElement(Node element) {
        leftElements.add(element);
    }

    public void addRightElement(Node element) {
        rightElements.add(element);
    }

    /**
     * Sets alignment by integer value
     * @param alignment 1 - Left; 2 - Center; 3 - Right.
     */
    public void setBottomCommandPanelAligment(Integer alignment) {
        switch (alignment) {
            case 1:  bottomCommandPanel.setAlignment(Pos.BOTTOM_LEFT);
                break;
            case 2:  bottomCommandPanel.setAlignment(Pos.BOTTOM_CENTER);
                break;
            case 3:  bottomCommandPanel.setAlignment(Pos.BOTTOM_RIGHT);
                break;
        }
    }

    public void setCommandPanelsUsege(Boolean leftCommandsUsage, Boolean rightCommandUsage, Boolean topCommandUsage, Boolean bottomCommandUsage) {
        this.leftCommandsUsage = leftCommandsUsage;
        this.rightCommandUsage = rightCommandUsage;
        this.topCommandUsage = topCommandUsage;
        this.bottomCommandUsage = bottomCommandUsage;
    }

    public void display() {
        centerLayout.getChildren().addAll(centralElements);
        topCommandPanel.getChildren().addAll(topElements);
        bottomCommandPanel.getChildren().addAll(bottomElements);
        leftCommandPanel.getChildren().addAll(leftElements);
        rightCommandPanel.getChildren().addAll(rightElements);

        mainLayout.setCenter(centerLayout);
        if (bottomCommandUsage)
            mainLayout.setBottom(bottomCommandPanel);
        if (topCommandUsage)
            mainLayout.setTop(topCommandPanel);
        if (leftCommandsUsage)
            mainLayout.setLeft(leftCommandPanel);
        if (rightCommandUsage)
            mainLayout.setRight(rightCommandPanel);

        Scene scene = new Scene(mainLayout, windowWidth, windowHeight);
        window.setScene(scene);
        window.show();
    }

    public void close() {
        window.close();
        ReagentCatalogListForm.update();
    }

    public HashMap<String, String> getInputValues() {
        HashMap<String, String> inputs = new HashMap<String, String>();
        for (Node formElement : centralElements) {
            if (formElement instanceof TextArea)
                inputs.put(formElement.getId(), ((TextArea) formElement).getText());
            else if (formElement instanceof TextField)
                inputs.put(formElement.getId(), ((TextField) formElement).getText());
        }

        for (Node formElement : topElements) {
            if (formElement instanceof TextArea)
                inputs.put(formElement.getId(), ((TextArea) formElement).getText());
            else if (formElement instanceof TextField)
                inputs.put(formElement.getId(), ((TextField) formElement).getText());
        }

        for (Node formElement : leftElements) {
            if (formElement instanceof TextArea)
                inputs.put(formElement.getId(), ((TextArea) formElement).getText());
            else if (formElement instanceof TextField)
                inputs.put(formElement.getId(), ((TextField) formElement).getText());
        }

        for (Node formElement : leftElements) {
            if (formElement instanceof TextArea)
                inputs.put(formElement.getId(), ((TextArea) formElement).getText());
            else if (formElement instanceof TextField)
                inputs.put(formElement.getId(), ((TextField) formElement).getText());
        }

        for (Node formElement : rightElements) {
            if (formElement instanceof TextArea)
                inputs.put(formElement.getId(), ((TextArea) formElement).getText());
            else if (formElement instanceof TextField)
                inputs.put(formElement.getId(), ((TextField) formElement).getText());
        }
        return inputs;
    }

    public void fillElements(HashMap<String, Object> inputs) {
        for (Node element : centralElements) {
            String elementID = element.getId();
            if (elementID != null) {
                for (Map.Entry<String, Object> fields : inputs.entrySet()) {
                    if (elementID.equalsIgnoreCase(fields.getKey())) {
                        Object fldValue = fields.getValue();
                        if (fldValue == null)
                            continue;
                        if (element instanceof TextArea) {
                            ((TextArea) element).setText(fldValue.toString());
                        }
                        else if (element instanceof TextField) {
                            ((TextField) element).setText(fldValue.toString());
                        }
                    }
                }
            }
        }
    }
}
