package com.Min.Controller;

import com.Min.Model.DataModel;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class ChatScreenController {
    private static final Pos BASELINE_LEFT = Pos.BASELINE_LEFT;
    private static final Pos BASELINE_RIGHT = Pos.BASELINE_RIGHT;
    @FXML
    Button sendButton;
    @FXML
    TextField chatBox;
    @FXML
    VBox chatContainer;

    NetworkManager networkManager;
    DataModel dataModel;
    Stage window;

    public void initialize(){
        chatBox.setOnKeyPressed(event -> {
                    if (event.getCode().equals(KeyCode.ENTER)) {
                        sendButton.fire();
                    }
                }
        );
    }
    public void initModel(DataModel model){
        if(this.dataModel != null){
            throw new IllegalStateException("Model can only be initialized once");
        }
        this.dataModel = model;
        this.window = dataModel.getWindow();
        dataModel.setCurrentScene("ChatScreen");

    }

    public void initNetworkManager(NetworkManager nManager){
        if(this.networkManager != null){
            throw new IllegalStateException("Network Manager can only be initialized once");
        }
        this.networkManager = nManager;
    }

    @FXML
    public void onSend() throws IOException {
        HBox hBox = new HBox();
        String message = chatBox.getText();
        Label sentText = new Label();
        sentText.setText(message);
        sentText.setTextAlignment(TextAlignment.RIGHT);
        //sentText.setPadding(new Insets(20));
        hBox.getChildren().add(sentText);
        hBox.setAlignment(Pos.BASELINE_RIGHT);
        hBox.setPadding(new Insets(10,20,0,0));
        chatContainer.getChildren().add(hBox);
        chatBox.clear();
        CompletableFuture.supplyAsync(() ->{
            try {
                networkManager.sendRequest(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        });

    }
}
