package main.java.ca.nl.cna.java2.project;

public class BlackjackProtocol {
    private static final int START_GAME = 0;
    private static final int SET_BET = 1;
    private static final int PLAYER_TURN = 2;
    private static final int DEALER_TURN = 3;
    private static final int END_GAME = 4;

    private int state = START_GAME;

    public String processInput(String theInput, BlackjackGame currentGame) {
        String theOutput = null;

        if (state == START_GAME) {
            theOutput = "OPEN THE GAME!";
            state = SET_BET;

        } else if (state == SET_BET) {
            // If the input is a balance greater than 0
            if (Integer.parseInt(theInput) > 0 && Integer.parseInt(theInput) <= currentGame.getPlayer().getBetMoney()) {
                theOutput = "Bet set at: " + theInput;
                state = PLAYER_TURN;

            } else {
                theOutput = "Get out of my casino you broke ass bitch";
                state = END_GAME;
            }
        }
        else if (state == PLAYER_TURN) {

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