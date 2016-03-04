package accaunt.lab.mvLab.com.Catalogs.Reagents;

import accaunt.lab.mvLab.com.DB_Helper;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ReagentCatalogElementForm {
    private static DB_Helper labDB;
    private static Stage window = new Stage();

    public static void display() {
        window.setTitle("Lab accaunting");
        window.setMinWidth(300);

        GridPane centerLayout = new GridPane();
        centerLayout.setPadding(new Insets(20, 20, 20, 20));
        centerLayout.setVgap(8);
        centerLayout.setHgap(10);

        //Lables ++
            //id label++
            Label idLabel = new Label();
            idLabel.setText("ID:");
            GridPane.setConstraints(idLabel, 1, 2);
            //--

            //id name++
            Label nameLabel = new Label();
            nameLabel.setText("Name:");
            GridPane.setConstraints(nameLabel, 1, 4);
            //--

            //id Desc++
            Label descriptionLabel = new Label();
            descriptionLabel.setText("Description:");
            GridPane.setConstraints(descriptionLabel, 1, 6);
            //--
        //--

        //Text fields+++
            //IDText field++
            TextField idTxtFld = new TextField();
            idTxtFld.setPromptText("ID");
            idTxtFld.setEditable(false);
            GridPane.setConstraints(idTxtFld, 3, 2);
            //--

            //NmaeText field++
            TextField nameTxtFld = new TextField();
            nameTxtFld.setPromptText("Name");
            GridPane.setConstraints(nameTxtFld, 3, 4);
            //--

            //DescText field++
            TextArea deScTxtFld = new TextArea();
            deScTxtFld.setPromptText("Description");
            //deScTxtFld.set
            //deScTxtFld.setMinHeight(80);
            deScTxtFld.setMinWidth(50);
            GridPane.setConstraints(deScTxtFld, 3, 6);
            //--
        //---
        centerLayout.getChildren().addAll(idLabel, nameLabel, descriptionLabel, idTxtFld, nameTxtFld, deScTxtFld);

        HBox commandPanel = new HBox();
        //layout.getChildren().addAll(reagentTable);

        BorderPane mainLayout = new BorderPane();
        mainLayout.setCenter(centerLayout);
        mainLayout.setBottom(commandPanel);

        Scene scene = new Scene(mainLayout, 400, 200);
        window.setScene(scene);
        window.show();
    }
}
