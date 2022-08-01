package com.Min.Controller;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.CompletableFuture;

public class NetworkManager {
    String myIP;
    String serverIP;

    DatagramSocket clientSocket;
    public NetworkManager() throws UnknownHostException {
        myIP = InetAddress.getLocalHost().toString();
        try{
            clientSocket = new DatagramSocket(18);
            clientSocket.setSoTimeout(15);
        }        catch(SocketException ignored){}
    }

    public String getMyIP() {
        return myIP;
    }

    public void setMyIP(String ip){
        this.myIP = ip;
    }

    public String getServerIP(){
        return serverIP;
    }

    public void setServerIP(String ip){
        this.serverIP = ip;
    }

    public void connect() throws Exception{
        // Send a message to server to establish TCP Connection:
        try{
                sendRequest( "A");
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean receive() throws IOException {
            // Receive request for connection
            try {
                DatagramPacket request = receiveReply();
                serverIP = request.getAddress().toString();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            return true;
    }

    public void sendRequest(String message) throws UnknownHostException, IOException{
        byte[] byteEncodedMessage = message.getBytes(); // Encode our message into bytes to put it inside the DatagramPacket
        InetAddress targetAddress = InetAddress.getByName(serverIP); //Get the InetAddress of the server we want to send message to
        DatagramPacket request = new DatagramPacket(byteEncodedMessage, byteEncodedMessage.length, targetAddress ,18); // Create a send request packet with our message
        this.clientSocket.send(request);
    }

    public DatagramPacket receiveReply() throws IOException{
        byte[] buffer = new byte[512];
        DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
        clientSocket.receive(reply);
        return reply;
    }
}
