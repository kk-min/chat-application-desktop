package com.Min.Controller;

import com.Min.Model.DataModel;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.concurrent.CompletableFuture;

public class LoadingScreenController extends Application {
    Stage window;
    String targetIP;
    DataModel dataModel;
    NetworkManager networkManager;

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("start method of LoadingScreenController");
        window = primaryStage;
    }

//    @FXML
//    public void initialize() throws Exception{
//        System.out.println("initialize method of LoadingScreenController");
//        //targetIP = dataModel.getTargetIP();
////
//
//        //networkManager.setServerIP(targetIP);
//        try{
//            networkManager.connect();
//        }
//        catch(Exception e){
//            Parent root = FXMLLoader.load(getClass().getResource("/ConnectionFailedScreen.fxml"));
//            Scene newScene = new Scene(root);
//            window.setScene(newScene);
//        }
//    }

    public void initModel(DataModel model){
        if (this.dataModel != null) {
            throw new IllegalStateException("Model can only be initialized once");
        }
        this.dataModel = model ;
    }

    public void initNetworkManager(NetworkManager networkManager){
        if(this.networkManager != null){
            throw new IllegalStateException("Network Manager can only be initialized once");
        }
        this.networkManager = networkManager;
    }
}
