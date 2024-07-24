package main.java.ca.nl.cna.java2.project;

import java.io.IOException;
import java.net.ServerSocket;

public class BlackjackGameManager {

    public static void main(String[] args) throws IOException {

        int portNumber = 4401; //Integer.parseInt(args[0]);
        boolean listening = true;

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
