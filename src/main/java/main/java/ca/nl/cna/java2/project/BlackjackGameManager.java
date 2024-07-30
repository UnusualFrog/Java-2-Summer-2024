package main.java.ca.nl.cna.java2.project;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * This class manages the Blackjack game server.
 * It listens for client connections on a specified port and starts a new thread for each connection.
 * The main method serves as the entry point for the server application.
 *
 * @author Noah.Forward
 */
public class BlackjackGameManager {

    public static void main(String[] args) throws IOException {

        int portNumber = 4401; // Port number on which the server listens for connections
        boolean listening = true; // Flag to keep the server listening for connections

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            while (listening) {
                new BJMultiServerThread(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
    }
}
