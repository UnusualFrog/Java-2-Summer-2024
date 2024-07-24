package main.java.ca.nl.cna.java2.project;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Class for a deck of cards made up of an arraylist of PlayingCard objects
 * Contains a discard pile arraylist to store drawn cards to be shuffled back into the deck if it becomes empty
 * Contains a method to shuffle the deck to randomize the order of card objects
 * Contains a method to draw a card
 * Contains a method to draw a hand of cards
 *
 * @author Noah Forward
 */
public class CardDeck {
    private ArrayList<PlayingCard> currentDeck;
    private ArrayList<PlayingCard> discardPile;

    /**
     * Builds a CardDeck instance with an arraylist to track the current deck and a discard pile arraylist to track cards that have been already drawn
     * Automatically fills the current deck with 52 cards, composed of all 13 cards of each card suit
     */
    public CardDeck() {
        currentDeck = new ArrayList<>();
        discardPile = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 2; j < 15; j++) {
                currentDeck.add(new PlayingCard(j, PlayingCard.Suit.values()[i]));
            }
        }
    }

    /**
     * Randomizes the order of card objects in the currentDeck arraylist to emulate the shuffling of a real deck of cards
     */
    public void shuffleDeck() {
        Collections.shuffle(this.currentDeck);
    }

    /**
     * Gets and removes the last PlayingCard object from the currentDeck arraylist to simulate drawing the top card of a deck of cards
     * The card removed from the end is added to the discardPile arraylist, so it can be re-added if the deck becomes empty
     * Attempting to draw with an empty currentDeck arraylist will set the currentDeck to the discardPile's value and then shuffle the currentDeck and empty the discardPile
     *
     * @return the last playingCard object in the currentDeck arraylist of playingCard objects
     */
    public PlayingCard drawCard() {
        if (this.currentDeck.isEmpty()) {
            this.currentDeck = this.discardPile;
            this.shuffleDeck();
            this.discardPile = new ArrayList<>(0);
        }
        PlayingCard card = this.currentDeck.remove(this.currentDeck.size() - 1);
        this.discardPile.add(card);

        return card;
    }

    /**
     * Calls drawCard 6 times to simulate drawing a hand of cards of size 6
     * Each card is added to an arraylist of PlayingCard objects which is returned
     *
     * @return arraylist of 6 randomly drawn PlayingCard objects
     */
    public Hand drawHand() {
        ArrayList<PlayingCard> newHand = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            newHand.add(this.drawCard());
        }
        return new Hand(newHand);
    }

    /**
     * Returns a string representation of the current deck
     *
     * @return String representation of each card in the current deck, joined together in to a space-seperated string
     */
    public String printDeck() {
        String deck = "";
        for (PlayingCard card : this.currentDeck) {
            deck += card.toString();
            deck += " ";
        }
        return deck;
    }

    /**
     * Returns a string representation of the current discard pile
     *
     * @return String representation of each card in the current discard pile, joined together in to a space-seperated string
     */
    public String printDiscardPile() {
        String discardPile = "";
        for (PlayingCard card : this.discardPile) {
            discardPile += card.toString();
            discardPile += " ";
        }
        return discardPile;
    }
}
