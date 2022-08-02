package com.Min.Controller;

import com.Min.Model.DataModel;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller class for ConnectionFailedScreen.fxml.
 */
public class ConnectionFailedController{
    @FXML
    Button returnButton;
    Stage window;
    DataModel dataModel;
    NetworkManager networkManager;

    /**
     * Initializes the DataModel.
     * @param model Injected DataModel that is used to access the application window.
     */
    public void initModel(DataModel model){
        if (this.dataModel != null) {
            throw new IllegalStateException("Model can only be initialized once");
        }
        this.dataModel = model;
        this.window = dataModel.getWindow();
        this.dataModel.setCurrentScene("ConnectionFailedScreen");
    }

    /**
     * Intializes the NetworkManager.
     * @param nManager Injected NetworkManager that is used to inject back to StartScreenController when returning to the start screen.
     */
    public void initNetworkManager(NetworkManager nManager){
        if(this.networkManager != null){
            throw new IllegalStateException("Network Manager can only be initialized once");
        }
        this.networkManager = nManager;
    }

    /**
     * Method bound to Return button that moves the Scene back to the start screen.
     * @throws IOException
     */
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
