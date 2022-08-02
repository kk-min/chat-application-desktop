package com.Min.Controller;

import com.Min.Model.DataModel;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ConnectionFailedController{
    @FXML
    Button returnButton;
    Stage window;
    DataModel dataModel;
    NetworkManager networkManager;

    public void initModel(DataModel model){
        if (this.dataModel != null) {
            throw new IllegalStateException("Model can only be initialized once");
        }
        this.dataModel = model;
        this.window = dataModel.getWindow();
        this.dataModel.setCurrentScene("ConnectionFailedScreen");
    }

    public void initNetworkManager(NetworkManager nManager){
        if(this.networkManager != null){
            throw new IllegalStateException("Network Manager can only be initialized once");
        }
        this.networkManager = nManager;
    }

    @FXML
    public void onReturn() throws IOException {
        FXMLLoader startScreenLoader = new FXMLLoader(getClass().getResource("/StartScreen.fxml"));
        Scene startScene = new Scene(startScreenLoader.load());
        StartScreenController startScreenController = startScreenLoader.getController();
        startScreenController.initModel(dataModel);
        startScreenController.initNetworkManager(networkManager);
        window.setScene(startScene);
    }
}
