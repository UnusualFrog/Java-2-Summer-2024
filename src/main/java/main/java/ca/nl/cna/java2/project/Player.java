package main.java.ca.nl.cna.java2.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Player {
    private Hand currentHand;
    private float betMoney;

    public Player() {
        currentHand = new Hand();
        betMoney = 100;
    }

    public Hand getCurrentHand() {
        return currentHand;
    }

    public void setCurrentHand(Hand currentHand) {
        this.currentHand = currentHand;
    }

    public float getBetMoney() {
        return betMoney;
    }

    public void setBetMoney(float betMoney) {
        this.betMoney = betMoney;
    }

    public static void main(String[] args) throws IOException {

        String hostName = "localhost";
        int portNumber = 4401;

        // Establish socket connection, output for sending messages to server, and input for receiving messages from the server
        try (Socket kkSocket = new Socket(hostName, portNumber); PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true); BufferedReader in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));) {
            // Stream for reading user input
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            // Store server and user input respectively
            String fromServer;
            String fromUser;

            while ((fromServer = in.readLine()) != null) {
                // Handle server response
                System.out.println("Server: " + fromServer);
                // End connection if server replies with "bye"
                if (fromServer.equals("Bye.")) break;

                // Handle user input
//                fromUser = stdIn.readLine();
//                if (fromUser != null) {
//                    System.out.println("Client: " + fromUser);
//                    out.println(fromUser);
//                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        }
    }

}
