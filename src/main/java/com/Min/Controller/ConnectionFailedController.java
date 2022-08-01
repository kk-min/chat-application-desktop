package com.Min.Controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ConnectionFailedController extends Application {
    @FXML
    Button returnButton;
    Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
    }

    @FXML
    public void handleClick(){

    }
}
