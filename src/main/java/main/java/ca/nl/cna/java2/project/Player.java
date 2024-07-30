package main.java.ca.nl.cna.java2.project;

import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Player {
    private Hand currentHand;
    private int betMoney;
    private int currentBet;

    public Player() {
        currentHand = new Hand();
        betMoney = 100;
        currentBet = 0;
    }

    public Hand getCurrentHand() {
        return currentHand;
    }

    public void setCurrentHand(Hand currentHand) {
        this.currentHand = currentHand;
    }

    public int getBetMoney() {
        return betMoney;
    }

    public void setBetMoney(int betMoney) {
        this.betMoney = betMoney;
        if (this.betMoney < 0) {
            this.betMoney = 0;
        }
    }

    public int getCurrentBet() {
        return currentBet;
    }

    public void setCurrentBet(int currentBet) {
        this.currentBet = currentBet;
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
            String fromServerMsg;
            String fromUser;


            JSONParser parser = new JSONParser();
            JSONObject jsonResponse;


            while ((fromServer = in.readLine()) != null) {
                try {
                    jsonResponse = (JSONObject) parser.parse(fromServer);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                fromServerMsg = (String) jsonResponse.get("string");

                if (!fromServerMsg.equals("continue")) {
                    // Handle server response
//                    System.out.println(fromServer);

                    System.out.println(fromServerMsg);
                    // End connection if server replies with "bye"
                    if (fromServerMsg.equals("Bye.")) break;
                } else {
                    // Handle user input
                    fromUser = stdIn.readLine();
                    if (fromUser != null) {
//                        System.out.println("Client: " + fromUser);
                        out.println(fromUser);
                    }
                }

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
