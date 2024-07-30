package main.java.ca.nl.cna.java2.project;

/**
 * This class represents the dealer in a Blackjack game.
 * It manages the dealer's current hand and includes methods to get and set the current hand.
 *
 * @author Noah.Forward
 */
public class Dealer {
    private Hand currentHand;

    /**
     * Constructor for Dealer.
     * Initializes the dealer with an empty hand.
     */
    public Dealer() {
        currentHand = new Hand();
    }

    /**
     * Gets the dealer's current hand.
     *
     * @return the current hand
     */
    public Hand getCurrentHand() {
        return currentHand;
    }

    /**
     * Sets the dealer's current hand.
     *
     * @param currentHand the hand to set as the dealer's current hand
     */
    public void setCurrentHand(Hand currentHand) {
        this.currentHand = currentHand;
    }
}
