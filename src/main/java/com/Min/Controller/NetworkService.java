package com.Min.Controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.io.IOException;
import java.net.DatagramPacket;

/**
 * Service class for asynchronously receiving messages sent to the client socket.
 */
public class NetworkService extends Service<String> {
    NetworkManager networkManager;
    StringProperty receivedMessage = new SimpleStringProperty();

    public NetworkService(NetworkManager nManager){
        this.networkManager = nManager;
    }

    public String getReceivedMessage() {
        return receivedMessage.get();
    }

    @Override
    protected Task<String> createTask() {
        //System.out.println("Inside createTask()!");
        return new Task<>() {
            @Override
            protected String call() throws Exception {
                //System.out.println("inside call()!");
                DatagramPacket request = null;
                while(true) {
                    try {
                        request = networkManager.receiveReply();
                        String receivedReply = new String(request.getData(), 0, request.getLength());
                        receivedMessage.setValue(receivedReply);
                        //System.out.printf("\nMessage from counterparty: %s\n", receivedReply);
                        return receivedReply;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }
}
