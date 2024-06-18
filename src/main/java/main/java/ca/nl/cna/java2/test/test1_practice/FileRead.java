package main.java.ca.nl.cna.java2.test.test1_practice;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileRead {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter a filepath: ");

        Path path = Paths.get(userInput.nextLine());

        if (Files.exists(path)) {
            if (!Files.isDirectory(path)) {
                Scanner scanner = null;
                try {
                    scanner = new Scanner(path);
                    while (scanner.hasNextLine()) {
                        System.out.print(scanner.nextLine() + "\n");
                    }
                }
                catch (IOException e) {
                    System.err.println(e);
                }
                finally {
                    scanner.close();
                }
            }
        }


    }
}
