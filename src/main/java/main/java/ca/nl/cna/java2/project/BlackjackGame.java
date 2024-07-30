package main.java.ca.nl.cna.java2.project;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class represents a Blackjack game.
 * It manages the player, dealer, current deck of cards, and the score.
 * It includes methods to reset the game and to get and set the game attributes.
 *
 * @author Noah.Forward
 */
@XmlRootElement
public class BlackjackGame {
    private Player player;
    private Dealer dealer;
    private CardDeck currentDeck;
    private float score;

    /**
     * Constructor for BlackjackGame.
     * Initializes the game by shuffling the deck, drawing hands for the player and dealer,
     * and setting the initial score to 0.
     */
    public BlackjackGame() {
        score = 0;

        currentDeck = new CardDeck();
        currentDeck.shuffleDeck();

        player = new Player();
        player.setCurrentHand(currentDeck.drawHand());

        dealer = new Dealer();
        dealer.getCurrentHand().addToHand(currentDeck.drawCard());
        dealer.getCurrentHand().addToHand(currentDeck.drawCard());
        dealer.getCurrentHand().setFaceDown(0);
    }

    /**
     * Resets the game by shuffling the deck, drawing new hands for the player and dealer.
     */
    public void resetGame() {
        currentDeck.shuffleDeck();
        player.setCurrentHand(new Hand());
        player.setCurrentHand(currentDeck.drawHand());

        dealer.setCurrentHand(new Hand());
        dealer.getCurrentHand().addToHand(currentDeck.drawCard());
        dealer.getCurrentHand().addToHand(currentDeck.drawCard());
        dealer.getCurrentHand().setFaceDown(0);
    }

    /**
     * Gets the player.
     *
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Sets the player.
     *
     * @param player the player to set
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Gets the dealer.
     *
     * @return the dealer
     */
    public Dealer getDealer() {
        return dealer;
    }

    /**
     * Sets the dealer.
     *
     * @param dealer the dealer to set
     */
    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    /**
     * Gets the current deck of cards.
     *
     * @return the current deck
     */
    public CardDeck getCurrentDeck() {
        return currentDeck;
    }

    /**
     * Sets the current deck of cards.
     *
     * @param currentDeck the current deck to set
     */
    public void setCurrentDeck(CardDeck currentDeck) {
        this.currentDeck = currentDeck;
    }

    /**
     * Gets the current score.
     *
     * @return the score
     */
    public float getScore() {
        return score;
    }

    /**
     * Sets the current score.
     *
     * @param score the score to set
     */
    public void setScore(float score) {
        this.score = score;
    }
}
