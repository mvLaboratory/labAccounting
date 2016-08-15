package com.mvLab.lab.accaunt;

import com.mvLab.lab.accaunt.windows.WindowManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane root = FXMLLoader.load(getClass().getResource("view/MainView.fxml"));
        primaryStage.setTitle("Laboratory accountant");
        primaryStage.setScene(new Scene(root));
        primaryStage.setMaximized(true);
        primaryStage.show();

        Tab nTab = new Tab("Main");
        nTab.setContent(FXMLLoader.load(getClass().getResource("view/ReportView.fxml")));

//        Tab nTab2 = new Tab("blah");
//        nTab2.setContent(FXMLLoader.load(getClass().getResource("view/TableView.fxml")));

        TabPane centralPane = new TabPane();
        centralPane.getTabs().add(nTab);
//        centralPane.getTabs().add(nTab2);
        root.setCenter(centralPane);

//        Group root= new Group();
//        GridPane frame=FXMLLoader.load(getClass().getResource("MainView.fxml"));
//        AnchorPane  content=  FXMLLoader.load(getClass().getResource("TableView.fxml"));
//        Scene scene = new Scene(root);
//
//        root.getChildren().add(frame);
//        root.getChildren().add(content);
//
//
//        primaryStage.setScene(scene);
//        primaryStage.show();
        
//        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("MainView.fxml")), 100, 100);
//        Stage nWindow = new Stage();
//        nWindow.setScene(scene);
//        nWindow.show();


//        Window w =new
//        Window w = new Window("My MV_Window#"+counter);
//        WindowManager.setPrimaryStage(primaryStage);
//        WindowManager.openMainWindow();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
