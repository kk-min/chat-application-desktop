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
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class StartScreenController{
    @FXML
    Button connectButton;
    @FXML
    TextField ipField;

    DataModel dataModel;
    Stage window;
    NetworkManager networkManager;

    LoadingScreenController loadingScreenController;

    @FXML
    public void initialize() throws Exception {
        ipField.setOnKeyPressed(event -> {
                    if (event.getCode().equals(KeyCode.ENTER)) {
                        connectButton.fire();
                    }
                }
        );
    }

    @FXML
    public void handleConnect(ActionEvent event) throws Exception {
        FXMLLoader loadingScreenLoader =
                new FXMLLoader(getClass().getResource("/LoadingScreen.fxml"));
        Scene loadingScene = new Scene(loadingScreenLoader.load());
        this.window = dataModel.getWindow();
        this.window.setScene(loadingScene);

        loadingScreenController = loadingScreenLoader.getController();
        loadingScreenController.initNetworkManager(networkManager); // Inject network manager into Controller
        loadingScreenController.initModel(dataModel); // Inject data model into Controller
        loadingScreenController.startConnection();

        System.out.println("Text in field: "+ ipField.getText());
        dataModel.setTargetIP(ipField.getText());
    }

    public void initModel(DataModel model){
        if (this.dataModel != null) {
            throw new IllegalStateException("Model can only be initialized once");
        }
        this.dataModel = model ;
        this.dataModel.setCurrentScene("StartScreen");
    }

    public void initNetworkManager(NetworkManager nManager){
        if(this.networkManager != null){
            throw new IllegalStateException("Network Manager can only be initialized once");
        }
        this.networkManager = nManager;
    }
}
