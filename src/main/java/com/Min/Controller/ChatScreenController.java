package com.Min.Controller;

import com.Min.Model.DataModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.DatagramPacket;
import java.util.concurrent.CompletableFuture;

public class ChatScreenController {
    @FXML
    Button sendButton;
    @FXML
    TextField chatBox;
    @FXML
    VBox chatContainer;

    NetworkManager networkManager;
    DataModel dataModel;
    Stage window;
    NetworkService networkService;

    public void initialize(){
        chatBox.setOnKeyPressed(event -> {
                    if (event.getCode().equals(KeyCode.ENTER)) {
                        sendButton.fire();
                    }
                }
        );
        //Begin receiving chat messages asynchronously:
//        CompletableFuture.supplyAsync(() ->{
//            while(true){
//                DatagramPacket request = null;
//                try {
//                    request = networkManager.receiveReply();
//                    String receivedMessage = new String(request.getData(), 0, request.getLength());
//                    System.out.printf("\nMessage from counterparty: %s\n", receivedMessage);
//                    //Show message to screen via the FXAT:
//                    Platform.runLater(() ->{
//                        ChatBubble chatBubble = new ReceivedChatBubble(receivedMessage);
//                        chatContainer.getChildren().add(chatBubble);
//                        System.out.println("Chat Bubble added!");
//                    });
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
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
        this.networkService = new NetworkService(networkManager);
        networkService.start();
        System.out.println("NetworkService started.");
        networkService.setOnSucceeded((e) -> {
            ChatBubble chatBubble = new ReceivedChatBubble(networkService.getReceivedMessage());
            chatContainer.getChildren().add(chatBubble);
            System.out.println("Chat Bubble added!");
            networkService.restart();
        });
    }

    @FXML
    public void onSend() throws IOException {
        String message = chatBox.getText();
        ChatBubble chatBubble = new SentChatBubble(message);
        chatContainer.getChildren().add(chatBubble);
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
