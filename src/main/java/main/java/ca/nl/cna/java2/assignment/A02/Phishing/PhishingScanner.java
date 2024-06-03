package main.java.ca.nl.cna.java2.assignment.A02.Phishing;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PhishingScanner {
    // Store frequency value of each key word
    private final HashMap<String, Integer> phishingCodeValues = new HashMap<>();

    // Store count of each key word found in text file/email
    private final HashMap<String, Integer> phishingCodeCounts = new HashMap<>();

    public PhishingScanner() {
        // List of words ranked by likelihood to appear
        phishingCodeValues.put("urgent", 3);
        phishingCodeValues.put("password", 3);
        phishingCodeValues.put("verify your account", 3);
        phishingCodeValues.put("click here", 3);
        phishingCodeValues.put("update", 2);
        phishingCodeValues.put("security alert", 3);
        phishingCodeValues.put("limited time", 2);
        phishingCodeValues.put("confidential", 2);
        phishingCodeValues.put("suspicious activity", 3);
        phishingCodeValues.put("account locked", 3);
        phishingCodeValues.put("login", 2);
        phishingCodeValues.put("payment", 2);
        phishingCodeValues.put("confirm", 2);
        phishingCodeValues.put("alert", 3);
        phishingCodeValues.put("secure", 2);
        phishingCodeValues.put("immediate action required", 3);
        phishingCodeValues.put("bank", 2);
        phishingCodeValues.put("refund", 2);
        phishingCodeValues.put("paypal", 3);
        phishingCodeValues.put("amazon", 3);
        phishingCodeValues.put("credit card", 3);
        phishingCodeValues.put("suspension", 3);
        phishingCodeValues.put("customer support", 2);
        phishingCodeValues.put("action required", 3);
        phishingCodeValues.put("personal information", 2);
        phishingCodeValues.put("billing", 2);
        phishingCodeValues.put("access", 2);
        phishingCodeValues.put("important notice", 3);
        phishingCodeValues.put("microsoft", 3);
        phishingCodeValues.put("invoice", 2);
    }

    public HashMap<String, Integer> getPhishingCodeValues() {
        return phishingCodeValues;
    }

    public HashMap<String, Integer> getPhishingCodeCounts() {
        return phishingCodeCounts;
    }

    public static void main(String[] args) {
        PhishingScanner phishingScanner = new PhishingScanner();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a filename to scan: ");
        Path path = Paths.get("./logs/phishing/" + scanner.nextLine());

        if (Files.exists(path)) {
            try (Scanner input = new Scanner(path)) {
                // Search input file line by line
                while (input.hasNext()) {
                    String currentLine = input.nextLine().toLowerCase();
                    // Loop through keywords and check for presence in each line
                    for (String key : phishingScanner.getPhishingCodeValues().keySet()) {
                        if(currentLine.contains(key)) {
                            // Check if current word is a key word
                            if (phishingScanner.getPhishingCodeValues().containsKey(key)) {
                                //Update key word counts
                                if(phishingScanner.getPhishingCodeCounts().containsKey(key)) {
                                    // Increment existing key word count
                                    phishingScanner.getPhishingCodeCounts().put(key, phishingScanner.getPhishingCodeCounts().get(key) + 1);
                                }
                                else {
                                    // Add key word to count dict with starting count of 1
                                    phishingScanner.getPhishingCodeCounts().put(key, 1);
                                }
                            }
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println(e);
            }

            // Output keywords found with point total and occurrence counts for each keyword to text file
            String filename = (path.getFileName() + "").substring(0, (path.getFileName() + "").indexOf("."));
            int overallPointTotal = 0;
            try (Formatter output = new Formatter("./logs/phishing/"+ filename + "_output" +".txt")) {
                for (Map.Entry<String, Integer> entry : phishingScanner.getPhishingCodeCounts().entrySet()) {
                    String key = entry.getKey();
                    int value = entry.getValue();
                    int pointTotal = value * phishingScanner.getPhishingCodeValues().get(key);
                    overallPointTotal += pointTotal;
                    output.format("Word: %s, Occurrences: %d, Point Total: %d \n", key, value, pointTotal);
                }
                output.format("Overall Point Total: %d", overallPointTotal);
            } catch (FileNotFoundException e) {
                System.err.println(e);
            }
        }
    }
}
