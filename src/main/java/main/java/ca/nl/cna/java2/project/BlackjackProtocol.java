package main.java.ca.nl.cna.java2.project;

public class BlackjackProtocol {
    private static final int START_GAME = 0;
    private static final int PLAYER_TURN = 1;
    private static final int DEALER_TURN = 2;
    private static final int END_GAME = 3;

    private int state = START_GAME;

    public String processInput(String theInput) {
        String theOutput = null;

        if (state == START_GAME) {
            theOutput = "OPEN THE GAME!";

            // If the input is a balance greater than 0
            state = PLAYER_TURN;
        } else if (state == PLAYER_TURN) {

            // If bust or stand move to dealer turn
            state = DEALER_TURN;
        } else if (state == DEALER_TURN) {

            // Always go to end game from dealer turn
            state = END_GAME;
        } else if (state == END_GAME) {

            // Start new game if player has money
            state = START_GAME;
        }

        return theOutput;
    }
}