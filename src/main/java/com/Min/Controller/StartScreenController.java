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

/**
 * Controller class for the Start Screen.
 */
public class StartScreenController{
    @FXML
    Button connectButton;
    @FXML
    TextField ipField;

    DataModel dataModel;
    Stage window;
    NetworkManager networkManager;

    LoadingScreenController loadingScreenController;

    /**
     * Initializes the Controller. Binds the Enter Key in the TextField to the Connect button.
     * @throws Exception
     */
    @FXML
    public void initialize() throws Exception {
        ipField.setOnKeyPressed(event -> {
                    if (event.getCode().equals(KeyCode.ENTER)) {
                        connectButton.fire();
                    }
                }
        );
    }

    /**
     * Method bound to the Connect button. Changes the Scene to the Loading Screen. (Connection attempt is handled in the Loading Screen.)
     * @param event
     * @throws Exception
     */
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

    /**
     * Initializes the DataModel.
     * @param model Injected DataModel that is used to access the window of the application.
     */
    public void initModel(DataModel model){
        if (this.dataModel != null) {
            throw new IllegalStateException("Model can only be initialized once");
        }
        this.dataModel = model ;
        this.dataModel.setCurrentScene("StartScreen");
    }

    /**
     * Initializes the NetworkManager.
     * @param nManager Injected NetworkManager that is used to further inject into the Loading Screen Controller.
     */
    public void initNetworkManager(NetworkManager nManager){
        if(this.networkManager != null){
            throw new IllegalStateException("Network Manager can only be initialized once");
        }
        this.networkManager = nManager;
    }
}
