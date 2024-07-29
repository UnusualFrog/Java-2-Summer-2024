package main.java.ca.nl.cna.java2.project;

import java.util.Objects;
import java.util.Scanner;

public class BlackJackConsole {
    /**
     * Constant values representing the numeric value of the game state
     * */
    private static final int START_GAME = 0;
    private static final int PLAYER_TURN = 1;
    private static final int DEALER_TURN = 2;
    private static final int DETERMINE_WINNER = 3;
    private static final int EXIT_GAME = 4;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BlackjackGame bjg = new BlackjackGame();

        // Set initial game state
        int state = START_GAME;
        String userInput = "";
        int userBet = 0;
        Player player = bjg.getPlayer();
        Dealer dealer = bjg.getDealer();

        System.out.println("★ OPEN THE GAME ★");
        while (state != EXIT_GAME) {
            if (state == START_GAME) {
                // Game starts by setting user bet
                System.out.println("----- Start Game -----");
                System.out.println("Bet Money: \uD83D\uDCB0" + player.getBetMoney());
                System.out.println("Enter a bet to begin (or -1 to exit): ");

                // Ensure user bet is valid
                try  {
                    userInput = scanner.nextLine();
                    userBet = Integer.parseInt(userInput);
                } catch (Exception e) {
                    System.err.println("Value must be an integer");
                }

                if (userBet == -1) {
                    // Exit game by choice
                    System.out.println("Thanks for playing! We hope to see you again!");
                    state = EXIT_GAME;
                } else if (player.getBetMoney() == 0) {
                    // Exit game due to no remaining money
                    System.out.println("Begone from my casino you penniless peon!");
                    state = EXIT_GAME;
                }
                else if (userBet > 0 && userBet <= player.getBetMoney()) {
                    // Set player bet and proceed to player turn
                    System.out.println("Bet set at: " + userBet);
                    player.setCurrentBet(userBet);
                    state = PLAYER_TURN;
                } else {
                    System.err.println("Insufficient Bet Money!");
                }

            } else if (state == PLAYER_TURN) {
                // Player is allowed to hit or stay
                System.out.println("----- Player Turn -----");
                System.out.println("Player Hand: " + player.getCurrentHand().printHand());
                System.out.println("Dealer Hand: " + dealer.getCurrentHand().printHand());

                // Move to dealer turn if player got black jack
                if (player.getCurrentHand().isBlackjack()) {
                    System.out.println("You got ⭐ BLACKJACK ⭐");
                    state = DEALER_TURN;
                } else {
                    System.out.println("Hit or Stay: ");
                    try  {
                        userInput = scanner.nextLine().toLowerCase();
                        if (Objects.equals(userInput, "hit")) {
                            player.getCurrentHand().addToHand(bjg.getCurrentDeck().drawCard());

                            // Move to dealer turn if player busts
                            if (player.getCurrentHand().isBust()){
                                System.out.println("Player Hand: " + player.getCurrentHand().printHand());
                                System.out.println("☠️ BUST ☠️");
                                state = DEALER_TURN;
                            }
                        } else if (Objects.equals(userInput, "stay")) {
                            // Move to dealer turn if player stays
                            state = DEALER_TURN;
                        } else {
                            throw new IllegalArgumentException("Value must be either 'Hit' or 'Stay'");
                        }
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                }

            } else if (state == DEALER_TURN) {
                // Dealer will hit until their hand is over 16
                System.out.println("----- Dealer Turn -----");
                // Reveal dealer's face down card
                dealer.getCurrentHand().setFaceup(0);
                System.out.println("Player Hand: " + player.getCurrentHand().printHand());
                System.out.println("Dealer Hand: " + dealer.getCurrentHand().printHand());

                // Move to determine winner if dealer gets blackjack
                if (dealer.getCurrentHand().isBlackjack()) {
                    System.out.println("⭐Dealer got ★ BLACKJACK ⭐");
                    state = DETERMINE_WINNER;
                } else {
                    // Keep hitting while dealer hand less than 17
                    if (dealer.getCurrentHand().calculateHandValue() < 17) {
                        System.out.println("Dealer Hit");
                        dealer.getCurrentHand().addToHand(bjg.getCurrentDeck().drawCard());

                        // Allow 3 seconds of delay between dealer hits for better user experience
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                        // Move to determine winner if dealer busts
                        if (dealer.getCurrentHand().isBust()) {
                            System.out.println("Dealer Hand: " + dealer.getCurrentHand().printHand());
                            System.out.println("☠️ Dealer Bust ☠️");
                            state = DETERMINE_WINNER;
                        } else {
                            System.out.println("Dealer Hand: " + dealer.getCurrentHand().printHand());
                        }
                    } else {
                        // Move to determine winner if dealer hand greater than 16
                        state = DETERMINE_WINNER;
                    }
                }

            } else if (state == DETERMINE_WINNER) {
                // Output winner and update bet money accordingly
                System.out.println("----- Determine Result -----");
                if (player.getCurrentHand().isBust()) {
                    System.out.println("☠️ Player Loses \uD83D\uDCB0" + player.getCurrentBet() + "☠️");
                    player.setBetMoney(player.getBetMoney() - player.getCurrentBet());
                } else if (dealer.getCurrentHand().isBust()) {
                    System.out.println("⭐Player Wins \uD83D\uDCB0" + player.getCurrentBet() + "⭐");
                    player.setBetMoney(player.getCurrentBet() + player.getBetMoney());
                } else if (player.getCurrentHand().isBlackjack()) {
                    if (dealer.getCurrentHand().isBlackjack()) {
                        System.out.println("\uD83D\uDE10 Player Ties Dealer, No Payout \uD83D\uDE10");
                    } else {
                        System.out.println("⭐Player Wins \uD83D\uDCB0" + player.getCurrentBet() + "⭐");
                        player.setBetMoney(player.getCurrentBet() + player.getBetMoney());
                    }
                } else if (player.getCurrentHand().calculateHandValue() > dealer.getCurrentHand().calculateHandValue()) {
                    System.out.println("⭐Player Wins \uD83D\uDCB0" + player.getCurrentBet() + "⭐");
                    player.setBetMoney(player.getCurrentBet() + player.getBetMoney());
                } else if (player.getCurrentHand().calculateHandValue() < dealer.getCurrentHand().calculateHandValue()) {
                    System.out.println("☠️ Player Loses \uD83D\uDCB0" + player.getCurrentBet() + "☠️");
                    player.setBetMoney(player.getBetMoney() - player.getCurrentBet());
                } else {
                    System.out.println("\uD83D\uDE10 Player Ties Dealer \uD83D\uDE10");
                }

                // Return to initial game state, with deck shuffled and hands reset
                System.out.println("Play again?");
                state = START_GAME;
                bjg.resetGame();
            }

        }
    }
}
