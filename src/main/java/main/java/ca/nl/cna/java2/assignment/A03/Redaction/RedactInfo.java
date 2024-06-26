package main.java.ca.nl.cna.java2.assignment.A03.Redaction;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;
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
        Pattern sensitivePattern = Pattern.compile("(\\d*-\\d*-\\d*-*\\d*|CODE\\d{17}|\\$\\d+\\.\\d+).");
        Pattern digitPattern = Pattern.compile("\\d");

        // Loop through each line
        for (String line : info) {
            Matcher matcher = sensitivePattern.matcher(line);
            // If line contains sensitive info
            if (matcher.find()) {
                String[] tokens = line.split("\\s+");
                // Loop through "tokens" of line to find and replace "token" containing sensitive info
                for (int i = 0; i < tokens.length; i++) {
                    if (tokens[i].matches(sensitivePattern.toString())){
                        Matcher digitMatcher = digitPattern.matcher(tokens[i]);
                        tokens[i] = digitMatcher.replaceAll("█");
                    }
                }
                redactedOutput.append(String.join(" ",tokens));
            } else {
                redactedOutput.append(line);
            }
        }

        try (Formatter output = new Formatter("src/main/java/main/java/ca/nl/cna/java2/assignment/A03/Redaction/sampleInfoRedacted.txt")){
            output.format(redactedOutput.toString());
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
