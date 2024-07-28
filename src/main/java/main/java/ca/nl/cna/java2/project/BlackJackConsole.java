package main.java.ca.nl.cna.java2.project;

import java.util.Objects;
import java.util.Scanner;

public class BlackJackConsole {
    private static final int START_GAME = 0;
    private static final int PLAYER_TURN = 1;
    private static final int DEALER_TURN = 2;
    private static final int END_GAME = 3;
    private static final int EXIT_GAME = 4;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BlackjackGame bjg = new BlackjackGame();
        int state = START_GAME;
        String userInput = "";
        int userBet = 0;

        System.out.println("★ OPEN THE GAME ★");
        Player player = bjg.getPlayer();
        Dealer dealer = bjg.getDealer();
        while (state != EXIT_GAME) {
            if (state == START_GAME) {

                System.out.println("Enter a bet to begin: ");
                try  {
                    userInput = scanner.nextLine();
                    userBet = Integer.parseInt(userInput);
                } catch (Exception e) {
                    System.err.println("Value must be an integer");
                }

                if (player.getBetMoney() == 0) {
                    System.out.println("Get out of my casino you broke bitch!");
                    state = EXIT_GAME;
                }
                else if (userBet > 0 && userBet <= player.getBetMoney()) {
                    System.out.println("Bet set at: " + userBet);
                    state = PLAYER_TURN;
                } else {
                    System.err.println("You do not have enough chips to make that bet!");
                }
            } else if (state == PLAYER_TURN) {
                System.out.println("Player Hand: " + player.getCurrentHand().printHand());
                System.out.println("Dealer Hand: " + dealer.getCurrentHand().printHand());

                if (player.getCurrentHand().isBlackjack()) {
                    System.out.println("You got ★ BLACKJACK ★");
                    state = DEALER_TURN;
                } else {
                    System.out.println("Hit or Stay: ");
                    try  {
                        userInput = scanner.nextLine().toLowerCase();
                        if (Objects.equals(userInput, "hit")) {
                            player.getCurrentHand().addToHand(bjg.getCurrentDeck().drawCard());
                            if (player.getCurrentHand().isBust()){
                                System.out.println("☠ BUST ☠");
                                state = DEALER_TURN;
                            }
                        } else if (Objects.equals(userInput, "stay")) {
                            state = DEALER_TURN;
                        } else {
                            throw new IllegalArgumentException("Value must be either 'Hit' or 'Stay'");
                        }
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                }
            } else if (state == DEALER_TURN) {
                dealer.getCurrentHand().setFaceup(0);
                System.out.println("Player Hand: " + player.getCurrentHand().printHand());
                System.out.println("Dealer Hand: " + dealer.getCurrentHand().printHand());

                if (dealer.getCurrentHand().isBlackjack()) {
                    System.out.println("Dealer got ★ BLACKJACK ★");
                    state = END_GAME;
                } else {
                    if (dealer.getCurrentHand().calculateHandValue() <= 17) {
                        dealer.getCurrentHand().addToHand(bjg.getCurrentDeck().drawCard());
                        if (dealer.getCurrentHand().isBust()) {
                            state = END_GAME;
                        }
                        System.out.println("Dealer Hit");
                        System.out.println("Dealer Hand: " + dealer.getCurrentHand().printHand());
                    }
                    state = END_GAME;
                }


            } else if (state == END_GAME) {

            } else {

            }

        }
    }
}
