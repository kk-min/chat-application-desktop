package com.Min.View;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application{
    Button sendButton;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage mainStage) throws Exception {
        mainStage.setTitle("Rfc865");

        sendButton = new Button("Connect");
        sendButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            
            }
        });

        StackPane layout = new StackPane();
        layout.getChildren().add(sendButton);

        Scene mainScene = new Scene(layout, 300, 250);
        mainStage.setScene(mainScene);
        mainStage.show();
    }
}
