package com.Min;

import java.io.InputStreamReader;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.concurrent.CompletableFuture;


/*Name: Min Kabar Kyaw
 *Group: SSP4
 *IP Address: 172.21.149.213
 */

public class Rfc865UdpClient{
    static DatagramSocket clientSocket;
    static BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) {
        //Open a UDP Socket:
        try{
            clientSocket = new DatagramSocket(18);
        }        catch(SocketException e){}
        //Receive a message from the server:
        CompletableFuture.supplyAsync(() -> {
            while(true){
                try{
                    DatagramPacket reply = receiveReply();
                    String receivedMessage = new String(reply.getData(), 0, reply.getLength()); // Convert the byte array data into String
                    System.out.printf("\nMessage from server: %s\n", receivedMessage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        while(true) {
            try {
                //Send a UDP request to the server:
                System.out.print("\nEnter Message: ");
                String requestMessage = inputReader.readLine();
                sendRequest(requestMessage);
            }
            catch (UnknownHostException e) {}
            catch (IOException e) {}
        }
    }

    public static void sendRequest(String requestMessage) throws UnknownHostException, IOException{
        byte[] byteEncodedMessage = requestMessage.getBytes(); // Encode our message into bytes to put it inside the DatagramPacket
        InetAddress targetAddress = InetAddress.getByName("LAPTOP-3LECIJKQ"); //Get the InetAddress of the server we want to send message to
        DatagramPacket request = new DatagramPacket(byteEncodedMessage, byteEncodedMessage.length, targetAddress ,17); // Create a send request packet with our message
        clientSocket.send(request);
    }

    public static DatagramPacket receiveReply() throws IOException{
        byte[] buffer = new byte[512]; // RFC865 Document says quote should be less than 512 characters, so we set 512 as the limit
        DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
        clientSocket.receive(reply);
        return reply;
    }
}
