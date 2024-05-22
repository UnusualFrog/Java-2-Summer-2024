package main.java.ca.nl.cna.java2.exercise.ex4_IO;

/**
 * May 21 Exercise
 * @author Noah Forward
 * @date 2024/05/21
 *
 * Create a program which picks a number between 1 and 100 and asks the user to guess the number.  If the number is incorrect, the program should either indicate if the guess is too high or too low.
 *
 * The program will store information about the guesses the user makes in a file named gamelog.txt
 *
 * Each time a guess is made the program will record the user's guess on a new line.
 *
 * When the correct guess is made, the program should exit.
 * */

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Class which stores a random number to be used in a guessing game
 * */
public class GuessingGame {
    public int randomNumber;

    public GuessingGame() {
        Random random = new Random();
        this.randomNumber = random.nextInt(1,101);
    }

    public static void main(String[] args) {
        GuessingGame game = new GuessingGame();
        Scanner scanner = new Scanner(System.in);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH-mm-ss-SSSZ" 	);
        String formattedDate = sdf.format(date);
        System.out.println(formattedDate);

        int userGuess = 0;
        System.out.println("Pick a number between 1-100:");
        //Initial user choice
        try {
            userGuess = scanner.nextInt();

        } catch (InputMismatchException e) {
            System.err.println(e);
        }
        int guessCounter = 1;

        try (Formatter output = new Formatter("./logs/gamelog"+ formattedDate +".txt")){

            // Continue guessing if initially wrong
            while (game.randomNumber != userGuess){
                // Guess result
                if (game.randomNumber > userGuess){
                    System.out.println("Guess too low");
                }else {
                    System.out.println("Guess too high");
                }
                output.format("Guess #%d: %d \n", guessCounter, userGuess);
                // New guess
                System.out.println("Pick a number between 1-100:");
                userGuess = scanner.nextInt();
                guessCounter++;
            }
            System.out.printf("%d was correct!",userGuess);
            output.format("Correct Guess #%d: %d \n", guessCounter, userGuess);
        }catch (Exception e){
            System.err.println(e);
        }
    }

}


