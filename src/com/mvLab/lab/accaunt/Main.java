package com.mvLab.lab.accaunt;

import com.mvLab.lab.accaunt.windows.MainWindow;
import com.mvLab.lab.accaunt.windows.WindowManager;
import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        WindowManager.setPrimaryStage(primaryStage);
        WindowManager.openMainWindow();
//        MainWindow mainWindow = new MainWindow(primaryStage);
//
//        mainWindow.Display();
        //testing git
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 800, 500));
//        primaryStage.show();
//
//        DB_Manager labDB = new DB_Manager();
//        labDB.ReadDB();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
