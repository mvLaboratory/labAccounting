package com.mvLab.account.windows;

import com.mvLab.account.Main;
import com.mvLab.account.controllers.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindow {
    private BorderPane rootLayout;
    private Stage primaryStage;
    private FXMLLoader loader;

    public MainWindow(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void display() throws IOException {
//        primaryStage.setOn
        //rootLayout = FXMLLoader.load(Main.class.getResource("view/MainForm.fxml"));
        loader = new FXMLLoader(Main.class.getResource("/view/MainForm.fxml"));
        rootLayout = loader.load();

        primaryStage.setTitle("Laboratory accountant");
        primaryStage.setScene(new Scene(rootLayout));
        primaryStage.setMaximized(true);
        primaryStage.show();

        Tab nTab = new Tab("Main");
        nTab.setContent(FXMLLoader.load(Main.class.getResource("/view/ReportView.fxml")));
        TabPane centralPane = new TabPane();
        centralPane.getTabs().add(nTab);
        rootLayout.setCenter(centralPane);
        nTab.setClosable(false);
    }

    public BorderPane getRootLayout() {
        return rootLayout;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public MainController getController() {
        return loader.getController();
    }

}
