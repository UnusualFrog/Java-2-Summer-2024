package main.java.ca.nl.cna.java2.exercise.ex6_StringRegex.ValidatePhone;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class PhoneValidator {


    public static void main(String[] args) {
        ArrayList<String> phoneNumbers = new ArrayList<>();
        try (Scanner input = new Scanner(Paths.get("src/main/java/main/java/ca/nl/cna/java2/exercise/ex6_StringRegex/ValidatePhone/contacts.txt"))) {
            while (input.hasNextLine()) {
                String currentLine = input.nextLine();
                phoneNumbers.add(currentLine);
            }
        } catch (IOException exception) {
            System.err.println(exception);
        }

        try (Formatter output = new Formatter("src/main/java/main/java/ca/nl/cna/java2/exercise/ex6_StringRegex/ValidatePhone/ValidatePhone.txt")) {
            for (String line : phoneNumbers) {
                if (line.matches("[a-zA-z]+\\s[a-zA-z]+\\s(709|613)-[\\d]{3}-[\\d]{4}")) {
                    output.format(line + "\n");
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }

        try (Formatter output = new Formatter("src/main/java/main/java/ca/nl/cna/java2/exercise/ex6_StringRegex/ValidatePhone/InvalidValidatePhone.txt")) {
            for (String line : phoneNumbers) {
                if (!line.matches("[a-zA-z]+\\s[a-zA-z]+\\s(709|613)-[\\d]{3}-[\\d]{4}")) {
                    output.format(line + "\n");
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
