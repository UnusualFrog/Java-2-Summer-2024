package main.java.ca.nl.cna.java2.project;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

public class BJMultiServerThread extends Thread {
    private Socket clientSocket = null;

    public BJMultiServerThread(Socket clientSocket) {
        super("BJMultiServerThread");
        this.clientSocket = clientSocket;
    }

    private static final int START_GAME = 0;
    private static final int PLAYER_TURN = 1;
    private static final int DEALER_TURN = 2;
    private static final int DETERMINE_WINNER = 3;
    private static final int EXIT_GAME = 4;


    public void run() {

        // Create Reader and Writer streams for client requests and responses
        try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true); BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {
            String inputLine, outputLine;

            Scanner scanner = new Scanner(System.in);
            BlackjackGame bjg = new BlackjackGame();

            // Set initial game state
            int state = START_GAME;
            String userInput = "";
            int userBet = 0;
            Player player = bjg.getPlayer();
            Dealer dealer = bjg.getDealer();

            out.println("★ OPEN THE GAME ★");
            while (state != EXIT_GAME) {
                if (state == START_GAME) {
                    // Game starts by setting user bet
                    out.println("----- Start Game -----");
                    out.println("Bet Money: \uD83D\uDCB0" + player.getBetMoney());
                    out.println("Enter a bet to begin (or -1 to exit): ");

                    // Tell client to accept use input
                    out.println("continue");

                    // Ensure user bet is valid
                    try {
                        userInput = in.readLine();
                        userBet = Integer.parseInt(userInput);
                    } catch (Exception e) {
                        System.err.println(e);
                        out.println("⚠️ ERROR: Value must be an integer ⚠️");
                    }

                    if (userBet == -1) {
                        // Exit game by choice
                        out.println("Thanks for playing! We hope to see you again!");
                        state = EXIT_GAME;
                    } else if (player.getBetMoney() == 0) {
                        // Exit game due to no remaining money
                        out.println("Begone from my casino you penniless peon!");
                        state = EXIT_GAME;
                    }
                    else if (userBet > 0 && userBet <= player.getBetMoney()) {
                        // Set player bet and proceed to player turn
                        out.println("Bet set at: " + userBet);
                        player.setCurrentBet(userBet);
                        state = PLAYER_TURN;
                    } else {
                        System.err.println("⚠️ Insufficient Bet Money ⚠️");
                    }

                } else if (state == PLAYER_TURN) {
                    // Player is allowed to hit or stay
                    out.println("----- Player Turn -----");
                    out.println("Player Hand: " + player.getCurrentHand().printHand());
                    out.println("Dealer Hand: " + dealer.getCurrentHand().printHand());

                    // Move to dealer turn if player got black jack
                    if (player.getCurrentHand().isBlackjack()) {
                        out.println("You got ⭐ BLACKJACK ⭐");
                        state = DEALER_TURN;
                    } else {
                        out.println("Hit or Stay: ");
                        out.println("continue");

                        try  {
                            userInput = in.readLine().toLowerCase();
                            if (Objects.equals(userInput, "hit")) {
                                player.getCurrentHand().addToHand(bjg.getCurrentDeck().drawCard());

                                // Move to dealer turn if player busts
                                if (player.getCurrentHand().isBust()){
                                    out.println("Player Hand: " + player.getCurrentHand().printHand());
                                    out.println("☠️ BUST ☠️");
                                    state = DEALER_TURN;
                                }
                            } else if (Objects.equals(userInput, "stay")) {
                                // Move to dealer turn if player stays
                                state = DEALER_TURN;
                            } else {
                                out.println("⚠️ Value must be either 'Hit' or 'Stay' ⚠️");
                            }
                        } catch (Exception e) {
                            System.err.println(e);
                        }
                    }

                } else if (state == DEALER_TURN) {
                    // Dealer will hit until their hand is over 16
                    out.println("----- Dealer Turn -----");
                    // Reveal dealer's face down card
                    dealer.getCurrentHand().setFaceup(0);
                    out.println("Player Hand: " + player.getCurrentHand().printHand());
                    out.println("Dealer Hand: " + dealer.getCurrentHand().printHand());

                    // Move to determine winner if dealer gets blackjack
                    if (dealer.getCurrentHand().isBlackjack()) {
                        out.println("⭐Dealer got ★ BLACKJACK ⭐");
                        state = DETERMINE_WINNER;
                    } else {
                        // Keep hitting while dealer hand less than 17
                        if (dealer.getCurrentHand().calculateHandValue() < 17) {
                            out.println("Dealer Hit");
                            dealer.getCurrentHand().addToHand(bjg.getCurrentDeck().drawCard());

                            // Allow 1.5 seconds of delay between dealer hits for better user experience
                            try {
                                Thread.sleep(1500);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }

                            // Move to determine winner if dealer busts
                            if (dealer.getCurrentHand().isBust()) {
                                out.println("Dealer Hand: " + dealer.getCurrentHand().printHand());
                                out.println("☠️ Dealer Bust ☠️");
                                state = DETERMINE_WINNER;
                            } else {
                                out.println("Dealer Hand: " + dealer.getCurrentHand().printHand());
                            }
                        } else {
                            // Move to determine winner if dealer hand greater than 16
                            out.println("Dealer Stay");
                            state = DETERMINE_WINNER;
                        }
                    }

                } else if (state == DETERMINE_WINNER) {
                    // Allow 1.5 seconds of delay between dealer end turn for better user experience
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    // Output winner and update bet money accordingly
                    out.println("----- Determine Result -----");
                    if (player.getCurrentHand().isBust()) {
                        out.println("☠️ Player Loses \uD83D\uDCB0" + player.getCurrentBet() + "☠️");
                        player.setBetMoney(player.getBetMoney() - player.getCurrentBet());
                    } else if (dealer.getCurrentHand().isBust()) {
                        out.println("⭐Player Wins \uD83D\uDCB0" + player.getCurrentBet() + "⭐");
                        player.setBetMoney(player.getCurrentBet() + player.getBetMoney());
                    } else if (player.getCurrentHand().isBlackjack()) {
                        if (dealer.getCurrentHand().isBlackjack()) {
                            out.println("\uD83D\uDE10 Player Ties Dealer, No Payout \uD83D\uDE10");
                        } else {
                            out.println("⭐Player Wins \uD83D\uDCB0" + player.getCurrentBet() + "⭐");
                            player.setBetMoney(player.getCurrentBet() + player.getBetMoney());
                        }
                    } else if (player.getCurrentHand().calculateHandValue() > dealer.getCurrentHand().calculateHandValue()) {
                        out.println("⭐Player Wins \uD83D\uDCB0" + player.getCurrentBet() + "⭐");
                        player.setBetMoney(player.getCurrentBet() + player.getBetMoney());
                    } else if (player.getCurrentHand().calculateHandValue() < dealer.getCurrentHand().calculateHandValue()) {
                        out.println("☠️ Player Loses \uD83D\uDCB0" + player.getCurrentBet() + "☠️");
                        player.setBetMoney(player.getBetMoney() - player.getCurrentBet());
                    } else {
                        out.println("\uD83D\uDE10 Player Ties Dealer \uD83D\uDE10");
                    }

                    // Return to initial game state, with deck shuffled and hands reset
                    out.println("Play again?");
                    state = START_GAME;
                    bjg.resetGame();
                }
            }

            // Close client connection and end game
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}