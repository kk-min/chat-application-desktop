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

public class StartScreenController extends Application {
    @FXML
    Button connectButton;
    @FXML
    TextField ipField;

    DataModel dataModel;
    Stage window;
    NetworkManager networkManager;

    LoadingScreenController loadingScreenController;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage mainStage) throws Exception {
        System.out.println("Start method of StartScreenController");
        window = mainStage;
        Parent root = FXMLLoader.load(getClass().getResource("/StartScreen.fxml"));
        Scene startScene = new Scene(root);
        window.setScene(startScene);
        window.show();
    }

    @FXML
    public void initialize() throws Exception {
        super.init();
        System.out.println("initialize method of StartScreenController");
    }

    @FXML
    public void handleConnect(ActionEvent event) throws Exception {
        //dataModel.setTargetIP(ipField.getText());
        Parent root = FXMLLoader.load(getClass().getResource("/LoadingScreen.fxml"));
        window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene newScene = new Scene(root);
        window.setScene(newScene);
    }

    public void initModel(DataModel model){
        if (this.dataModel != null) {
            throw new IllegalStateException("Model can only be initialized once");
        }
        this.dataModel = model ;
    }
}
