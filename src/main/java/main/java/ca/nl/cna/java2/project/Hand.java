package main.java.ca.nl.cna.java2.project;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<PlayingCard> currentHand;

    public Hand() {
        currentHand = new ArrayList<>();
    }

    public Hand(List hand) {
        currentHand = new ArrayList<>(hand);
    }

    public List<PlayingCard> getCurrentHand() {
        return currentHand;
    }

    public void setCurrentHand(List<PlayingCard> currentHand) {
        this.currentHand = currentHand;
    }

    /**
     * Returns a string representation of the current hand
     *
     * @return String representation of each card in the current deck, joined together in to a space-seperated string
     */
    public String printHand() {
        String deck = "";
        for (PlayingCard card : this.currentHand) {
            deck += card.toString();
            deck += " ";
        }
        return deck;
    }
}
