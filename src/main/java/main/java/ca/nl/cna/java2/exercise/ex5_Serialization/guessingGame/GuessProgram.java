package main.java.ca.nl.cna.java2.exercise.ex5_Serialization.guessingGame;

import javax.xml.bind.JAXB;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class GuessProgram {
    public static void main(String[] args) {
        Random rand = new Random();
        Scanner input = new Scanner(System.in);
        Date date = new Date();
        Long timestamp = date.getTime();
        GameLog gameLog = new GameLog();

        try (BufferedWriter output = Files.newBufferedWriter(Paths.get("./logs/xml/output" + timestamp +".xml"))){
            int correct = rand.nextInt( 1, 100);
            int guess = -99;
            int totalGuesses = 0;

            while (correct != guess){
                if( totalGuesses > 0) {
                    if( guess > correct ) {
                        System.out.println("Too high");
                    }
                    else {
                        System.out.println("Too low");
                    }
                }
                System.out.println("Guess number between 1 and 100");
                try {
                    guess = input.nextInt();
                    totalGuesses++;
                    gameLog.getGuessList().add(guess);
                } catch (NoSuchElementException e) {
                    System.err.println("Invalid Number! Please try again");
                    input.next();
                }
            }
            System.out.println("You guessed " + correct + " It took you " + totalGuesses + " guesses");
            JAXB.marshal(gameLog, output);

        } catch (IOException e) {
          System.err.println(e);
        }




    }
}
