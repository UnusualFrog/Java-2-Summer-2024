package main.java.ca.nl.cna.java2.project;

import java.util.Objects;
import java.util.Scanner;

public class BJPtest {

    public static void main(String[] args) {
        BlackjackProtocol bjp = new BlackjackProtocol();
        BlackjackGame bjg = new BlackjackGame();
        Scanner userInput = new Scanner(System.in);

        String response = bjp.processInput("");

        while (!Objects.equals(response, "EXIT")) {
            if (response == "OPEN THE GAME!") {

            }
        }

    }
}
