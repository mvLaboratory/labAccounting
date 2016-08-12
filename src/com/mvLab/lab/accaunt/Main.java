package com.mvLab.lab.accaunt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        primaryStage.setTitle("Laboratory accountant");
        primaryStage.setScene(new Scene(root));
        primaryStage.setMaximized(true);
        primaryStage.show();
        
//        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("MainWindow.fxml")), 100, 100);
//        Stage nWindow = new Stage();
//        nWindow.setScene(scene);
//        nWindow.show();


        //Window w =new
//        Window w = new Window("My MV_Window#"+counter);
//        WindowManager.setPrimaryStage(primaryStage);
//        WindowManager.openMainWindow();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
