package main.java.ca.nl.cna.java2.exercise.ex4_IO.guessingGame;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AverageGuesses {
    public static void main(String[] args) {
        Path path = Paths.get("C:\\Users\\noah.forward\\IdeaProjects\\Java II Summer 2024\\logs");
        float totalGuessCount = 0;
        float totalGameCount = 0;

        if (Files.isDirectory(path))
        {
            // object for iterating through a directory's contents
            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path)) {
                for (Path p : directoryStream) {
                    totalGuessCount += Files.lines(p).count();
                    totalGameCount++;
                }
            } catch (IOException | SecurityException e){
                System.err.println(e);
            }
        }
        System.out.println("---");
        System.out.println(totalGameCount);
        System.out.println(totalGuessCount);
        System.out.println("Average guesses for " + totalGameCount + " games is " + totalGuessCount / totalGameCount + " guesses");
    }
}
