package main.java.ca.nl.cna.java2.project;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a hand of playing cards in a Blackjack game.
 * It includes methods to manage the hand, calculate its value, and check for Blackjack or bust conditions.
 *
 * @author Noah.Forward
 */
public class Hand {
    private List<PlayingCard> currentHand;

    /**
     * Constructor for Hand.
     * Initializes the hand with an empty list of cards.
     */
    public Hand() {
        currentHand = new ArrayList<>();
    }

    /**
     * Constructor for Hand with an initial list of cards.
     *
     * @param hand the initial list of cards
     */
    public Hand(List<PlayingCard> hand) {
        currentHand = new ArrayList<>(hand);
    }

    /**
     * Gets the current hand of cards.
     *
     * @return the current hand
     */
    public List<PlayingCard> getCurrentHand() {
        return currentHand;
    }

    /**
     * Sets the current hand of cards.
     *
     * @param currentHand the hand to set
     */
    public void setCurrentHand(List<PlayingCard> currentHand) {
        this.currentHand = currentHand;
    }

    /**
     * Adds a card to the hand.
     *
     * @param newCard the card to add
     */
    public void addToHand(PlayingCard newCard) {
        this.currentHand.add(newCard);
    }

    /**
     * Sets a card in the hand to face down.
     *
     * @param index the index of the card to set face down
     */
    public void setFaceDown(int index) {
        PlayingCard originalCard = this.currentHand.get(index);
        originalCard.faceDown = true;
        this.currentHand.set(index, originalCard);
    }

    /**
     * Sets a card in the hand to face up.
     *
     * @param index the index of the card to set face up
     */
    public void setFaceUp(int index) {
        PlayingCard originalCard = this.currentHand.get(index);
        originalCard.faceDown = false;
        this.currentHand.set(index, originalCard);
    }

    /**
     * Calculates the total value of the hand.
     *
     * @return the total value of the hand
     */
    public int calculateHandValue() {
        int handValue = 0;
        int aceCount = 0;

        // Count King, Queen and Jack as 10, Ace as 11
        for (PlayingCard card : this.currentHand) {
            if (card.getValue() == 11 || card.getValue() == 12 || card.getValue() == 13) {
                handValue += 10;
            } else if (card.getValue() == 14) {
                handValue += 11;
                aceCount++;
            } else {
                handValue += card.getValue();
            }
        }

        // Determine if aces should be counted as 1 or 11
        while (handValue > 21 && aceCount > 0) {
            handValue -= 10;
            aceCount--;
        }

        return handValue;
    }

    /**
     * Checks if the hand value exceeds 21.
     *
     * @return true if the hand value exceeds 21, false otherwise
     */
    public boolean isBust() {
        return this.calculateHandValue() > 21;
    }

    /**
     * Checks if the hand value is exactly 21.
     *
     * @return true if the hand value is exactly 21, false otherwise
     */
    public boolean isBlackjack() {
        return this.calculateHandValue() == 21;
    }

    /**
     * Returns a string representation of the current hand.
     *
     * @return String representation of each card in the current hand, joined together into a space-separated string
     */
    public String printHand() {
        StringBuilder deck = new StringBuilder();
        for (PlayingCard card : this.currentHand) {
            deck.append(card.toString()).append(" ");
        }
        return deck.toString().trim();
    }
}
