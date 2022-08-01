package com.Min.Controller;

import com.Min.Model.DataModel;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Main extends Application{
    @FXML
    Button sendButton;
    @FXML
    TextField textField;

    DataModel dataModel;
    Stage window;
    NetworkManager networkManager;

    LoadingScreenController loadingScreenController;
    StartScreenController startScreenController;

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

        FXMLLoader loadingScreenLoader = new FXMLLoader(getClass().getResource("/LoadingScreen.fxml"));
        Scene loadingScene = new Scene(loadingScreenLoader.load());
        loadingScreenController = loadingScreenLoader.getController();
        loadingScreenController.initModel(dataModel); // Inject data model into Controller
        loadingScreenController.initNetworkManager(networkManager); // Inject network manager into Controller

        window = mainStage;
        window.setTitle("Rfc865");

        window.setScene(startScene);
        window.show();
    }
}
