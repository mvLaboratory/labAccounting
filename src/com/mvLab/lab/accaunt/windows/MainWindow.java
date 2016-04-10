package com.mvLab.lab.accaunt.windows;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainWindow extends Window implements EventHandler<ActionEvent> {
    private Stage window;

    public MainWindow(Stage window, String title, int windowWidth, int windowHeight) {
        super(window, title, windowWidth, windowHeight);
        this.window = window;

        addElements();
    }

    public final void addElements() {
        Label titleLabel = new Label();
        titleLabel.setText("Lab accounting");
        setTopCommandPanelAligment(2);
        addTopElement(titleLabel);

        Button btnReact = new Button("Reagents");
        btnReact.setMinWidth(80);
        btnReact.setId("openReagentCatalogListForm");
        btnReact.setOnAction(this);
        addLeftElement(btnReact);

        Button btnReagentArrival = new Button("Arrival");
        btnReagentArrival.setMinWidth(80);
        btnReagentArrival.setId("openReagentArrivalListForm");
        btnReagentArrival.setOnAction(this);
        addLeftElement(btnReagentArrival);

        Button btnReagentConsumption = new Button("Consumption");
        btnReagentConsumption.setMinWidth(80);
        btnReagentConsumption.setId("openReagentConsumptionListForm");
        btnReagentConsumption.setOnAction(this);
        addLeftElement(btnReagentConsumption);
    }

//    public void Display() {
//        window.setTitle("Lab accounting");
//        window.setMinWidth(300);
//
//        Label titleLabel = new Label();
//        titleLabel.setText("Lab accounting");
//
//        Button ButtonOK = new Button("Reagents");
//        ButtonOK.setMinWidth(60);
//        ButtonOK.setOnAction(this);
//
//        GridPane centerLayout = new GridPane();
//        centerLayout.setPadding(new Insets(10, 10, 10, 10));
//        centerLayout.setVgap(8);
//        centerLayout.setHgap(10);
//        GridPane.setConstraints(titleLabel, 4, 0);
//        centerLayout.getChildren().addAll(titleLabel);
//
//        GridPane leftLayout = new GridPane();
//        leftLayout.setPadding(new Insets(10, 10, 10, 10));
//        leftLayout.setVgap(8);
//        leftLayout.setHgap(10);
//        //VBox verticalLayout = new VBox(20);
//        GridPane.setConstraints(ButtonOK, 0, 3);
//        leftLayout.getChildren().addAll(ButtonOK);
//
//        BorderPane mainLayout = new BorderPane();
//        mainLayout.setCenter(centerLayout);
//        mainLayout.setLeft(leftLayout);
//
//        Scene scene = new Scene(mainLayout, 800, 500);
//        window.setScene(scene);
//        window.show();
//    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() instanceof Button) {
            Button source = (Button) event.getSource();
            String buttonID = source.idProperty().getValue();

            if (buttonID.equals("openReagentCatalogListForm")) {
                WindowManager.openReagentCatalogListForm();
            }
            else if (buttonID.equals("openReagentArrivalListForm")) {
                WindowManager.openReagentArrivalListForm();
            }
            else if (buttonID.equals("openReagentConsumptionListForm")) {
                WindowManager.openReagentConsumptionListForm();
            }
        }
    }
}
