package main.java.ca.nl.cna.java2.exercise.ex5_Serialization.guessingGame;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class AnalyzeLogs {
    public static void main(String[] args) {
        Path path = Paths.get("./logs/xml/");
        if (Files.exists(path))
        {
            System.out.println("Folder exists");

            try {
                int totalFiles;
                int totalGuesses;
                try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path)) {
                    totalFiles = 0;
                    totalGuesses = 0;
                    for (Path p : directoryStream) {
                        totalFiles++;
                        System.out.println(p);
                        Scanner input = new Scanner(p);
                        while (input.hasNext()) {
                            String line = input.nextLine();
                            System.out.println(line);
                            if (line.contains("<guess>")) {
                                totalGuesses++;
                            }
                        }
                    }
                }
                System.out.println("Total guesses " + totalGuesses);
                System.out.println("Total files " + totalFiles);
                System.out.println("Average guesses: " + (float)totalGuesses/totalFiles);
            }
            catch (IOException e) {
                e.printStackTrace();
                System.err.println(e.getMessage());
            }
        }
        else {
            System.out.println("Can't find folder to process logs");
        }


    }
}
