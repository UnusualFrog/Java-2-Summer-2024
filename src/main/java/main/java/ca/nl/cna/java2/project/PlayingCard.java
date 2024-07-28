package main.java.ca.nl.cna.java2.project;

import java.io.PrintStream;

/**
 * Class for creating a playing card with a suit and a value that could be part of a deck of cards
 * @author Noah Forward
 * */
public class PlayingCard {
    int value;
    Suit suit;
    boolean faceDown;
    /**
     * Enumeration representing the possible suits for a card
     * */
    public enum Suit {DIAMONDS, HEARTS, CLUBS, SPADES}

    /**
     * Constant values representing the numeric value of special case card values
     * */
    public static final int ACE = 14;
    public static final int KING = 13;
    public static final int QUEEN = 12;
    public static final int JACK = 11;

    /**
     * Constructor to create a card instance with a suit and a value
     * Suit and value are used to set corresponding suit symbol and value symbol for cases like Ace/King/Queen/Jack
     * @param value int from 2-14 representing the number value of the card. Values outside the range will default to 2
     * @param suit enumerable from the class enumeration of suits, representing one of the four card suits. Defaults to DIAMONDS if suit does not exist in the Suit enum
     * */
    public PlayingCard(int value, Suit suit, boolean faceDown) {
        if (value < 2 || value > 14){
            value = 2;
        }
        this.value = value;

        if (suit.equals(Suit.DIAMONDS) || suit.equals(Suit.HEARTS) || suit.equals(Suit.CLUBS) || suit.equals(Suit.SPADES)){
            this.suit = suit;
        }
        else{
            this.suit = Suit.DIAMONDS;
        }

        this.faceDown = faceDown;
    }

    /**
     * Gets the numeric value of the card
     * @return the numeric value of the card as an int
     * */
    public int getValue() {
        return value;
    }

    /**
     * Gets the value of the symbol representing the numeric value of the current card
     * Values 2-10 be set to strings containing their numbers
     * Values 11/12/13/14 be set to strings containing either J/Q/K/A accordingly
     * @return string representing the numeric value of the current card (i.e., 11 -> J)
     * */
    private static String getValueSymbol(int cardValue) {
        String valueSymbol;
        if (cardValue == ACE){
            valueSymbol = "A";
        }
        else if (cardValue == KING){
            valueSymbol = "K";
        }
        else if (cardValue == QUEEN){
            valueSymbol = "Q";
        }
        else if (cardValue == JACK){
            valueSymbol = "J";
        }
        else {
            valueSymbol = "" + cardValue;
        }
        return valueSymbol;
    }

    /**
     * Gets the enumeration value representing the current suit of the card
     * @return an enumeration from the set of enumerations representing the 4 suits of playing cards
     * */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Gets the unicode symbol corresponding to the card's current suit
     * @return string containing the unicode symbol for the suit of the current card
     * */
    private static String getSuitSymbol(Suit cardSuit) {
        String suitSymbol;
        if (cardSuit == (PlayingCard.Suit.DIAMONDS)){
            suitSymbol = "♦";
        }else if (cardSuit == Suit.HEARTS){
            suitSymbol = "♥";
        }
        else if (cardSuit == Suit.CLUBS){
            suitSymbol = "♣";
        }
        else if (cardSuit == Suit.SPADES){
            suitSymbol = "♠";
        }
        else {
            suitSymbol = "E!";
        }
        return suitSymbol;
    }

    /**
     * Override of the toString method to print the card using its symbol representations for card value and suit
     * @return string containing the symbol representing the value of the card followed by the symbol representing the suit
     * */
    public String toString(){
        if (this.faceDown) {
            return "? ?";
        }
        return String.format("%s %s", getValueSymbol(this.value), getSuitSymbol(this.suit));
    }

    /**
     * Prints an ascii text representation of a card passed in with its suit and value displayed
     * @param playingCard the playing card object to be printed
     * @param printStream the print stream object to be used in outputting the ascii text representation of the card
     * */
    public static void printAsciiCard(PlayingCard playingCard, PrintStream printStream){
        int cardWidth = 7;

        String asciiCard = "*".repeat(cardWidth);
        asciiCard += "\n";


        if (playingCard.getValue() == 10){
            asciiCard += String.format("*%s%s   *\n", "1","0");
        }else {
            asciiCard += String.format("*%s    *\n", getValueSymbol(playingCard.value));
        }
        asciiCard += "*     *\n";

        asciiCard += String.format("*  %s  *\n", getSuitSymbol(playingCard.suit));

        asciiCard += "*     *\n";
        if (playingCard.getValue() == 10){
            asciiCard += String.format("*   %s%s*\n", "1","0");
        }else {
            asciiCard += String.format("*    %s*\n", getValueSymbol(playingCard.value));
        }

        asciiCard += "*".repeat(cardWidth);
        asciiCard += "\n";

        printStream.print(asciiCard);
    }
}
