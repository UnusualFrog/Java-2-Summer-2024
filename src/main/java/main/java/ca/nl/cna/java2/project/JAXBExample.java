package main.java.ca.nl.cna.java2.project;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class JAXBExample {

    public static void main(String[] args) {
        BlackjackGame game = new BlackjackGame();

        // Perform marshalling
        try {
            File file = new File("./logs/xml/blackjack/blackjack_game.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(BlackjackGame.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // To format the XML output
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Marshal the object to file
            jaxbMarshaller.marshal(game, file);
            jaxbMarshaller.marshal(game, System.out); // Also print to console for verification

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
