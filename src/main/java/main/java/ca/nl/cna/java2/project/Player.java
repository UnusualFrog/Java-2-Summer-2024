package main.java.ca.nl.cna.java2.project;

import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * This class represents a player in a Blackjack game.
 * It manages the player's current hand, bet money, and current bet
 *
 * @author Noah.Forward
 */
public class Player {
    private Hand currentHand;
    private int betMoney;
    private int currentBet;

    /**
     * Constructor for Player.
     * Initializes the player with an empty hand, a starting bet money of 100, and a current bet of 0.
     */
    public Player() {
        currentHand = new Hand();
        betMoney = 100;
        currentBet = 0;
    }

    /**
     * Gets the player's current hand.
     *
     * @return the current hand
     */
    public Hand getCurrentHand() {
        return currentHand;
    }

    /**
     * Sets the player's current hand.
     *
     * @param currentHand the hand to set
     */
    public void setCurrentHand(Hand currentHand) {
        this.currentHand = currentHand;
    }

    /**
     * Gets the player's bet money.
     *
     * @return the bet money
     */
    public int getBetMoney() {
        return betMoney;
    }

    /**
     * Sets the player's bet money.
     *
     * @param betMoney the bet money to set
     */
    public void setBetMoney(int betMoney) {
        this.betMoney = betMoney;
        if (this.betMoney < 0) {
            this.betMoney = 0;
        }
    }

    /**
     * Gets the player's current bet.
     *
     * @return the current bet
     */
    public int getCurrentBet() {
        return currentBet;
    }

    /**
     * Sets the player's current bet.
     *
     * @param currentBet the current bet to set
     */
    public void setCurrentBet(int currentBet) {
        this.currentBet = currentBet;
    }

}
