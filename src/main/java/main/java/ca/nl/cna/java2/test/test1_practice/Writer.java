package main.java.ca.nl.cna.java2.test.test1_practice;

import java.io.File;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Writer {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter a filepath: ");

        Path path = Paths.get(userInput.nextLine());
        List lines = new ArrayList<>();

        if (Files.exists(path)) {
            try (DirectoryStream directoryStream  = Files.newDirectoryStream(path)) {
                Iterator<Path> stream =  directoryStream.iterator();
                while (stream.hasNext()) {
                    lines.add(stream.next());
                    lines.add("\n");
                }
            }
            catch (Exception e) {
                System.err.println(e);
            }
        }
        System.out.println(lines);

        try (Formatter output = new Formatter("eatadick.txt")) {
            for (Object line : lines) {
                output.format("%s", line.toString());
            }
        } catch (Exception e) {
            System.err.println();
        }
    }
}
