package com.Min.Controller;

import com.Min.Model.DataModel;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class Main extends Application{
    @FXML
    Button sendButton;
    @FXML
    TextField textField;

    DataModel dataModel;
    Stage window;
    NetworkManager networkManager;

    StartScreenController startScreenController;
    ConnectionFailedController connectionFailedController;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage mainStage) throws Exception {
        System.out.println("Start method of Main");
        dataModel = new DataModel();
        networkManager = new NetworkManager();

        FXMLLoader startScreenLoader = new FXMLLoader(getClass().getResource("/StartScreen.fxml"));
        Scene startScene = new Scene(startScreenLoader.load());
        startScreenController = startScreenLoader.getController();
        startScreenController.initModel(dataModel);
        startScreenController.initNetworkManager(networkManager);

        window = mainStage;
        window.setTitle("Rfc865");
        dataModel.setWindow(window);
        window.setScene(startScene);
        window.show();
    }
}
