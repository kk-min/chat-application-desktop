package com.Min.Controller;

import com.Min.Model.DataModel;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

/**
 * Controller class for loading screen that is shown while a connection is being attempted.
 */
public class LoadingScreenController{
    Stage window;
    DataModel dataModel;
    NetworkManager networkManager;

    /**
     * Initializes the DataModel.
     * @param model Injected DataModel that is used to get the window of the application.
     */
    public void initModel(DataModel model){
        if (this.dataModel != null) {
            throw new IllegalStateException("Model can only be initialized once");
        }
        this.dataModel = model ;
        this.window = dataModel.getWindow();
        this.dataModel.setCurrentScene("LoadingScreen");
        System.out.println("Setting up observer...");
        dataModel.targetIP().addListener((observable, oldIP, newIP) -> {
            System.out.println("Old IP: " + oldIP);
            System.out.println("New IP: " + newIP);
            networkManager.setServerIP(newIP);
        });
    }

    /**
     * Method to start an asynchronous connection attempt; changes the Scene to the Chat Screen/Connection Failed Screen depending on a successful/unsuccessful connection.
     */
    public void startConnection(){
        System.out.println("Trying to connect...");

        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
                networkManager.connect();
                //Successfully connected:
                Platform.runLater(() ->{
                    FXMLLoader chatScreenLoader = new FXMLLoader(getClass().getResource("/ChatScreen.fxml"));
                    try {
                        Scene chatScreenScene = new Scene(chatScreenLoader.load());
                        ChatScreenController chatScreenController = chatScreenLoader.getController();
                        chatScreenController.initModel(dataModel);
                        chatScreenController.initNetworkManager(networkManager);
                        window.setScene(chatScreenScene);
                        System.out.println("Chat screen displayed.");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                });

            } catch (Exception e) {
                System.out.println("Connection failed!");
                Platform.runLater(() ->{
                            FXMLLoader connectionFailedLoader = new FXMLLoader(getClass().getResource("/ConnectionFailedScreen.fxml"));
                            try {
                                Scene connectionFailedScene = new Scene(connectionFailedLoader.load());
                                ConnectionFailedController connectionFailedController = connectionFailedLoader.getController();
                                connectionFailedController.initModel(dataModel);
                                connectionFailedController.initNetworkManager(networkManager);
                                window.setScene(connectionFailedScene);
                                System.out.println("Connection failed scene set.");
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                );
                return null;
            }
            System.out.println("Successful connection.");
            return null;
        });
    }

    /**
     * Initializes the NetworkManager.
     * @param nManager Injected NetworkManager that is used to access network functions.
     */
    public void initNetworkManager(NetworkManager nManager){
        if(this.networkManager != null){
            throw new IllegalStateException("Network Manager can only be initialized once");
        }
        this.networkManager = nManager;
    }
}
