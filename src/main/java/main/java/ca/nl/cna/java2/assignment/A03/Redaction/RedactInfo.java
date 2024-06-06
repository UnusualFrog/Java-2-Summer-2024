package main.java.ca.nl.cna.java2.assignment.A03.Redaction;

import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RedactInfo {

    public static void main(String[] args) {
        StringBuilder info = new StringBuilder();

        // Load data into info string
        try(Scanner input = new Scanner(Paths.get("src/main/java/main/java/ca/nl/cna/java2/assignment/A03/Redaction/sampleInfo.txt"))) {
            while (input.hasNextLine()) {
                info.append(input.nextLine());
                info.append(System.lineSeparator());
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        System.out.println(info);

        Pattern pattern = Pattern.compile("\\d{2}-\\d{2}-\\d{4}");
        Matcher matcher = pattern.matcher(info);

        matcher.find();

        System.out.println(matcher.group(0));
    }
}
