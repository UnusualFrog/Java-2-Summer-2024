package main.java.ca.nl.cna.java2.project;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    /**
     * Main method to connect to the Blackjack server.
     *
     * @param args command-line arguments
     * @throws IOException if an I/O error occurs when creating the socket
     */
    public static void main(String[] args) throws IOException {

        String hostName = "localhost";
        int portNumber = 4401;

        // Establish socket connection, output for sending messages to server, and input for receiving messages from the server
        try (Socket kkSocket = new Socket(hostName, portNumber);
             PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()))) {

            // Stream for reading user input
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            // Store server and user input respectively
            String fromServer;
            String fromServerMsg;
            String fromUser;

            JSONParser parser = new JSONParser();
            JSONObject jsonResponse;

            while ((fromServer = in.readLine()) != null) {
                // Parse JSON object for server response msg
                try {
                    jsonResponse = (JSONObject) parser.parse(fromServer);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                fromServerMsg = (String) jsonResponse.get("string");

                // Output response from server
                if (!fromServerMsg.equals("continue")) {
                    System.out.println(fromServerMsg);
                } else {
                    // Allow for user input if server msg is "continue"
                    fromUser = stdIn.readLine();
                    if (fromUser != null) {
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
