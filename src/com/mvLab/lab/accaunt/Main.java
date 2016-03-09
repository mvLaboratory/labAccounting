package com.mvLab.lab.accaunt;

import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        DB_Helper labDB = DB_Helper.getInstance();
        MainWindow mainWindow = new MainWindow(primaryStage, labDB);

        mainWindow.Display();
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 800, 500));
//        primaryStage.show();
//
//        DB_Helper labDB = new DB_Helper();
//        labDB.ReadDB();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
