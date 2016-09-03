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

        //git marge test
        //git marge test develop - work
        //git merge test develop - home
        //git marge test develop - work2
    }

    public static void main(String[] args) {
        launch(args);
    }
}
