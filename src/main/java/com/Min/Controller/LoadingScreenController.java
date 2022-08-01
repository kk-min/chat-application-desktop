package com.Min.Controller;

import com.Min.Model.DataModel;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class LoadingScreenController{
    Stage window;
    String targetIP;
    DataModel dataModel;
    NetworkManager networkManager;

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
            System.out.println("Trying to connect...");

            CompletableFuture.supplyAsync(() -> {
                try {
                    networkManager.connect();
                } catch (Exception e) {
                    System.out.println("Connection failed!");
                    Platform.runLater(() ->{
                                FXMLLoader connectionFailedLoader = new FXMLLoader(getClass().getResource("/ConnectionFailedScreen.fxml"));
                                try {
                                    Scene connectionFailedScene = new Scene(connectionFailedLoader.load());
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
        });
    }

    public void initNetworkManager(NetworkManager nManager){
        if(this.networkManager != null){
            throw new IllegalStateException("Network Manager can only be initialized once");
        }
        this.networkManager = nManager;
    }
}
