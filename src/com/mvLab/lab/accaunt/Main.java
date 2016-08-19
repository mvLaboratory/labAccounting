package com.mvLab.lab.accaunt;

import com.mvLab.lab.accaunt.windows.WindowManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        WindowManager windowManager = WindowManager.initialize(primaryStage);
        windowManager.openMainWindow();

        
    }

    public static void main(String[] args) {
        launch(args);
    }
}
