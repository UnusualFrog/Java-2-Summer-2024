package main.java.ca.nl.cna.java2.assignment.A03.Redaction;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RedactInfo {

    public static void main(String[] args) {
        ArrayList<String> info = new ArrayList<>();
        StringBuilder redactedOutput = new StringBuilder();

        // Load data into info string
        try(Scanner input = new Scanner(Paths.get("src/main/java/main/java/ca/nl/cna/java2/assignment/A03/Redaction/sampleInfo.txt"))) {
            while (input.hasNextLine()) {
                info.add(input.nextLine());
                info.add(System.lineSeparator());
            }
        } catch (Exception e) {
            System.err.println(e);
        }

        // Create regex patterns
        Pattern pattern = Pattern.compile("([\\d]*-[\\d]*-[\\d]*-*[\\d]*|CODE[\\d]{17})");
        Pattern digitPattern = Pattern.compile("\\d");

        // Loop through each line
        for (String line : info) {
            Matcher matcher = pattern.matcher(line);
            System.out.println(line);
            // If line contains sensitive info
            if (matcher.find()) {
                String[] tokens = line.split("[ .]+");
                System.out.println(Arrays.toString(tokens));
                // Loop through "words" of line to find and replace word containing sensitive info
                for (int i = 0; i < tokens.length; i++) {
                    if (tokens[i].matches(pattern.toString())){
                        Matcher digitMatcher = digitPattern.matcher(tokens[i]);
                        tokens[i] = digitMatcher.replaceAll("█");
                    }
                }
                System.out.println(Arrays.toString(tokens));
                redactedOutput.append(String.join(" ",tokens));
            } else {
                redactedOutput.append(line);
            }

        }

        System.out.println("-".repeat(100));
        System.out.println(redactedOutput);
    }
}
