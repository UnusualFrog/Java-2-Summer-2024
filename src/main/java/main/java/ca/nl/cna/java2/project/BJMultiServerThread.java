package main.java.ca.nl.cna.java2.project;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.net.Socket;

public class BJMultiServerThread extends Thread {
    private Socket clientSocket = null;

    public BJMultiServerThread(Socket clientSocket) {
        super("BJMultiServerThread");
        this.clientSocket = clientSocket;
    }

    public void run() {
        BlackjackGame currentGame = new BlackjackGame();

        // Create Reader and Writer streams for client requests and responses
        try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true); BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {
            String inputLine, outputLine;

            // Protocol for managing game state based on requests and responses
            BlackjackProtocol bjp = new BlackjackProtocol();
            outputLine = bjp.processInput(null, currentGame);

            // Send initial response to client
            out.println(outputLine);
            if (outputLine.equals("OPEN THE GAME!")) {
                currentGame.getPlayer().setCurrentHand(currentGame.getCurrentDeck().drawHand());
                currentGame.getDealer().setCurrentHand(currentGame.getCurrentDeck().drawHand());
                out.println("Player Hand: " + currentGame.getPlayer().getCurrentHand().printHand());
                out.println("Dealer Hand: " + currentGame.getDealer().getCurrentHand().printHand());
//                marshalValue(currentGame, out);
            }

            // Continue to receive and respond to requests until the protocol returns "Bye"
            while ((inputLine = in.readLine()) != null) {
                outputLine = bjp.processInput(inputLine, currentGame);
                out.println(outputLine);

                if (outputLine.equals("Bye")) break;
            }

            // Close client connection and end game
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void marshalValue(BlackjackGame game, PrintWriter output) {
        // Perform marshalling
        try {
//            File file = new File("./logs/xml/blackjack/blackjack_game.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(BlackjackGame.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // To format the XML output
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Marshal the object to file
//            jaxbMarshaller.marshal(game, file);
            jaxbMarshaller.marshal(game, output); // Also print to console for verification

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}