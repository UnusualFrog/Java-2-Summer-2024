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

    public void addToHand(PlayingCard new_card) {
        this.currentHand.add(new_card);
    }

    public void setFacedown(int index) {
        PlayingCard original_card = this.currentHand.get(index);
        original_card.faceDown = true;
        this.currentHand.set(index, original_card);
    }

    public void setFaceup(int index) {
        PlayingCard original_card = this.currentHand.get(index);
        original_card.faceDown = false;
        this.currentHand.set(index, original_card);
    }

    public int calculateHandValue() {
        int handValue = 0;
        int aceCount = 0;

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

        while (handValue > 21 && aceCount > 0 ) {
            handValue -= 10;
        }

        return handValue;
    }

    public boolean isBust() {
        return this.calculateHandValue() > 21;
    }

    public boolean isBlackjack() {
        return this.calculateHandValue() == 21;
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
