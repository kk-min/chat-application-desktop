package com.Min.Controller;

import com.Min.Model.DataModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

/**
 * Controller class for ChatScreen.fxml that is activated upon a successful connection.
 */
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

    /**
     * Initializes the ChatScreenController. Enter key is bound to the send button here.
     */
    public void initialize(){
        chatBox.setOnKeyPressed(event -> {
                    if (event.getCode().equals(KeyCode.ENTER)) {
                        sendButton.fire();
                    }
                }
        );
    }

    /**
     * Initializes the DataModel.
     * @param model Injected DataModel that is used to access the window of the application.
     */
    public void initModel(DataModel model){
        if(this.dataModel != null){
            throw new IllegalStateException("Model can only be initialized once");
        }
        this.dataModel = model;
        this.window = dataModel.getWindow();
        dataModel.setCurrentScene("ChatScreen");

    }

    /**
     * Intializes the NetworkManager.
     * @param nManager Injected NetworkManager that is used to access network functions.
     */
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

    /**
     * Method bound to the send button. Asynchronously sends message to connected client through NetworkManager, and updates chat bubble on screen.
     * @throws IOException
     */
    @FXML
    public void onSend() throws IOException {
        String message = chatBox.getText();
        if(message.equals("")){
            return;
        }
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
