package com.mvLab.lab.accaunt;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        DB_Manager.initialize();

        WindowManager.initialize(primaryStage);
        WindowManager windowManager = WindowManager.getInstance();
        windowManager.openMainWindow();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
