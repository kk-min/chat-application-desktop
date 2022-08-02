package com.Min.Controller;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.CompletableFuture;

/**
 * Class to handle establishing connections, sending and receiving messages.
 */
public class NetworkManager {
    String myIP;
    String serverIP;
    DatagramSocket clientSocket;

    /**
     * Initializes the UDP DatagramSocket and sets the timeout duration.
     * @throws UnknownHostException
     */
    public NetworkManager() throws UnknownHostException {
        myIP = InetAddress.getLocalHost().toString();
        try{
            clientSocket = new DatagramSocket(18);
            clientSocket.setSoTimeout(10*1000);
        }        catch(SocketException e){
            System.out.println("Failed to create socket!");
            e.printStackTrace();
        }
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

    /**
     * Attempts a connection by sending a character to the target IP.
     * @throws Exception
     */
    public void connect() throws Exception{
        // Send a message to server to establish TCP Connection:
        try{
            sendRequest("A");
        } catch (Exception e) {
            System.out.println("Connection failed in NetworkManager");
            e.printStackTrace();
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

    /**
     * Sends a message to the target IP by encapsulating a string into a DatagramPacket.
     * @param message Message to send to target IP.
     * @throws UnknownHostException
     * @throws IOException
     */
    public void sendRequest(String message) throws UnknownHostException, IOException{
        byte[] byteEncodedMessage = message.getBytes(); // Encode our message into bytes to put it inside the DatagramPacket
        InetAddress targetAddress = InetAddress.getByName(serverIP); //Get the InetAddress of the server we want to send message to
        DatagramPacket request = new DatagramPacket(byteEncodedMessage, byteEncodedMessage.length, targetAddress ,17); // Create a send request packet with our message
        this.clientSocket.send(request);
    }

    /**\
     * Receives a message that is sent to the client socket.
     * @return The received DatagramPacket.
     * @throws IOException
     */
    public DatagramPacket receiveReply() throws IOException{
        byte[] buffer = new byte[512];
        DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
        clientSocket.receive(reply);
        return reply;
    }
}
