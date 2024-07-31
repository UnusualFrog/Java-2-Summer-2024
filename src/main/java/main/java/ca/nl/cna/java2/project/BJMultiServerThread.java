package main.java.ca.nl.cna.java2.project;

import org.json.simple.JSONObject;

import java.io.*;
import java.net.Socket;
import java.util.Objects;

/**
 * Class for running a thread based game of Black Jack
 * Contains logic to handle the game states and flow of execution between game states
 * This class extends the Thread class, allowing it to be run as a separate thread.
 *
 * @author Noah.Forward
 * */
public class BJMultiServerThread extends Thread {
    private Socket clientSocket = null;

    /**
     * Constructor for BJMultiServerThread.
     *
     * @param clientSocket the socket connected to the client
     */
    public BJMultiServerThread(Socket clientSocket) {
        super("BJMultiServerThread");
        this.clientSocket = clientSocket;
    }

    /**
     * Constant values representing the numeric value of the game state
     * */
    private static final int START_GAME = 0;
    private static final int PLAYER_TURN = 1;
    private static final int DEALER_TURN = 2;
    private static final int DETERMINE_WINNER = 3;
    private static final int EXIT_GAME = 4;


    /**
     * The run method is executed when the thread is started.
     * It handles the game logic and communication with the client.
     */
    public void run() {
        // Create Reader and Writer streams for client requests and responses
        try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true); BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {

            BlackjackGame bjg = new BlackjackGame();

            // Set initial game state
            int state = START_GAME;
            String userInput = "";
            int userBet = 0;
            Player player = bjg.getPlayer();
            Dealer dealer = bjg.getDealer();

            JSONObject jsonObject = ResponseJSONObject.getObject("★ OPEN THE GAME ★");
            out.println(jsonObject.toJSONString());
            while (state != EXIT_GAME) {
                if (state == START_GAME) {
                    // Game starts by setting user bet
                    out.println(ResponseJSONObject.getObject("----- Start Game -----"));
                    out.println(ResponseJSONObject.getObject("Bet Money: \uD83D\uDCB0" + player.getBetMoney()));
                    out.println(ResponseJSONObject.getObject("Enter a bet to begin (or -1 to exit): "));

                    // Tell client to accept use input
                    out.println(ResponseJSONObject.getObject("continue"));

                    // Ensure user bet is valid
                    try {
                        userInput = in.readLine();
                        userBet = Integer.parseInt(userInput);
                    } catch (Exception e) {
                        System.err.println(e);
                        out.println(ResponseJSONObject.getObject("⚠️ ERROR: Value must be an integer ⚠️"));
                    }

                    if (userBet == -1) {
                        // Exit game by choice
                        out.println(ResponseJSONObject.getObject("Thanks for playing! We hope to see you again!"));
                        state = EXIT_GAME;
                    } else if (player.getBetMoney() == 0) {
                        // Exit game due to no remaining money
                        out.println(ResponseJSONObject.getObject("Begone from my casino you penniless peon!"));
                        state = EXIT_GAME;
                    } else if (userBet > 0 && userBet <= player.getBetMoney()) {
                        // Set player bet and proceed to player turn
                        out.println(ResponseJSONObject.getObject("Bet set at: " + userBet));
                        player.setCurrentBet(userBet);
                        state = PLAYER_TURN;
                    } else {
                        out.println(ResponseJSONObject.getObject("⚠️ Insufficient Bet Money ⚠️"));
                    }

                } else if (state == PLAYER_TURN) {
                    // Player is allowed to hit or stay
                    out.println(ResponseJSONObject.getObject("----- Player Turn -----"));
                    out.println(ResponseJSONObject.getObject("Player Hand: " + player.getCurrentHand().printHand()));
                    out.println(ResponseJSONObject.getObject("Dealer Hand: " + dealer.getCurrentHand().printHand()));

                    // Move to dealer turn if player got black jack
                    if (player.getCurrentHand().isBlackjack()) {
                        out.println(ResponseJSONObject.getObject("You got ⭐ BLACKJACK ⭐"));
                        state = DEALER_TURN;
                    } else {
                        out.println(ResponseJSONObject.getObject("Hit or Stay: "));
                        out.println(ResponseJSONObject.getObject("continue"));

                        try {
                            userInput = in.readLine().toLowerCase();
                            if (Objects.equals(userInput, "hit")) {
                                player.getCurrentHand().addToHand(bjg.getCurrentDeck().drawCard());

                                // Move to dealer turn if player busts
                                if (player.getCurrentHand().isBust()) {
                                    out.println(ResponseJSONObject.getObject("Player Hand: " + player.getCurrentHand().printHand()));
                                    out.println(ResponseJSONObject.getObject("☠️ BUST ☠️"));
                                    state = DEALER_TURN;
                                }
                            } else if (Objects.equals(userInput, "stay")) {
                                // Move to dealer turn if player stays
                                state = DEALER_TURN;
                            } else {
                                out.println(ResponseJSONObject.getObject("⚠️ Value must be either 'Hit' or 'Stay' ⚠️"));
                            }
                        } catch (Exception e) {
                            System.err.println(e);
                        }
                    }

                } else if (state == DEALER_TURN) {
                    // Dealer will hit until their hand is over 16
                    out.println(ResponseJSONObject.getObject("----- Dealer Turn -----"));
                    // Reveal dealer's face down card
                    dealer.getCurrentHand().setFaceUp(0);
                    out.println(ResponseJSONObject.getObject("Player Hand: " + player.getCurrentHand().printHand()));
                    out.println(ResponseJSONObject.getObject("Dealer Hand: " + dealer.getCurrentHand().printHand()));

                    // Move to determine winner if dealer gets blackjack
                    if (dealer.getCurrentHand().isBlackjack()) {
                        out.println(ResponseJSONObject.getObject("⭐Dealer got ★ BLACKJACK ⭐"));
                        state = DETERMINE_WINNER;
                    } else {
                        // Keep hitting while dealer hand less than 17
                        if (dealer.getCurrentHand().calculateHandValue() < 17) {
                            out.println(ResponseJSONObject.getObject("Dealer Hit"));
                            dealer.getCurrentHand().addToHand(bjg.getCurrentDeck().drawCard());

                            // Allow 1.5 seconds of delay between dealer hits for better user experience
                            try {
                                Thread.sleep(1500);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }

                            // Move to determine winner if dealer busts
                            if (dealer.getCurrentHand().isBust()) {
                                out.println(ResponseJSONObject.getObject("Dealer Hand: " + dealer.getCurrentHand().printHand()));
                                out.println(ResponseJSONObject.getObject("☠️ Dealer Bust ☠️"));
                                state = DETERMINE_WINNER;
                            } else {
                                out.println(ResponseJSONObject.getObject("Dealer Hand: " + dealer.getCurrentHand().printHand()));
                            }
                        } else {
                            // Move to determine winner if dealer hand greater than 16
                            out.println(ResponseJSONObject.getObject("Dealer Stay"));
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
                    out.println(ResponseJSONObject.getObject("----- Determine Result -----"));
                    if (player.getCurrentHand().isBust()) {
                        out.println(ResponseJSONObject.getObject("☠️ Player Loses \uD83D\uDCB0" + player.getCurrentBet() + "☠️"));
                        player.setBetMoney(player.getBetMoney() - player.getCurrentBet());
                    } else if (dealer.getCurrentHand().isBust()) {
                        out.println(ResponseJSONObject.getObject("⭐Player Wins \uD83D\uDCB0" + player.getCurrentBet() + "⭐"));
                        player.setBetMoney(player.getCurrentBet() + player.getBetMoney());
                    } else if (player.getCurrentHand().isBlackjack()) {
                        if (dealer.getCurrentHand().isBlackjack()) {
                            out.println(ResponseJSONObject.getObject("\uD83D\uDE10 Player Ties Dealer, No Payout \uD83D\uDE10"));
                        } else {
                            out.println(ResponseJSONObject.getObject("⭐Player Wins \uD83D\uDCB0" + player.getCurrentBet() + "⭐"));
                            player.setBetMoney(player.getCurrentBet() + player.getBetMoney());
                        }
                    } else if (player.getCurrentHand().calculateHandValue() > dealer.getCurrentHand().calculateHandValue()) {
                        out.println(ResponseJSONObject.getObject("⭐Player Wins \uD83D\uDCB0" + player.getCurrentBet() + "⭐"));
                        player.setBetMoney(player.getCurrentBet() + player.getBetMoney());
                    } else if (player.getCurrentHand().calculateHandValue() < dealer.getCurrentHand().calculateHandValue()) {
                        out.println(ResponseJSONObject.getObject("☠️ Player Loses \uD83D\uDCB0" + player.getCurrentBet() + "☠️"));
                        player.setBetMoney(player.getBetMoney() - player.getCurrentBet());
                    } else {
                        out.println(ResponseJSONObject.getObject("\uD83D\uDE10 Player Ties Dealer \uD83D\uDE10"));
                    }

                    // Return to initial game state, with deck shuffled and hands reset
                    out.println(ResponseJSONObject.getObject("Play again?"));
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