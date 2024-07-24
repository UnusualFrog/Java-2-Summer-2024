package main.java.ca.nl.cna.java2.project;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BlackjackGame {
    private Player player;
    private Dealer dealer;
    private CardDeck currentDeck;
    private float score;

    public BlackjackGame() {
        score = 0;
        currentDeck = new CardDeck();
        currentDeck.shuffleDeck();
        player = new Player();
        dealer = new Dealer();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public CardDeck getCurrentDeck() {
        return currentDeck;
    }

    public void setCurrentDeck(CardDeck currentDeck) {
        this.currentDeck = currentDeck;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
