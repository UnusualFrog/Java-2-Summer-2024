package main.java.ca.nl.cna.java2.project;

public class Dealer {
    private Hand currentHand;

    public Dealer() {
        currentHand = new Hand();
    }

    public Hand getCurrentHand() {
        return currentHand;
    }

    public void setCurrentHand(Hand currentHand) {
        this.currentHand = currentHand;
    }
}
