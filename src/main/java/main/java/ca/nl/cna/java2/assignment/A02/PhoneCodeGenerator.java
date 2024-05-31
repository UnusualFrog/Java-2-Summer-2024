package main.java.ca.nl.cna.java2.assignment.A02;

import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Scanner;

public class PhoneCodeGenerator {
    private final HashMap<String, String[]> phoneCodes = new HashMap<>();

    public PhoneCodeGenerator() {
        phoneCodes.put("2",new String[]{"A","B","C"});
        phoneCodes.put("3",new String[]{"D","E","F"});
        phoneCodes.put("4",new String[]{"G","H","I"});
        phoneCodes.put("5",new String[]{"J","K","L"});
        phoneCodes.put("6",new String[]{"M","N","O"});
        phoneCodes.put("7",new String[]{"P","R","S"});
        phoneCodes.put("8",new String[]{"T","U","V"});
        phoneCodes.put("9",new String[]{"W","X","Y"});
    }

    public HashMap<String, String[]> getPhoneCodes() {
        return phoneCodes;
    }

    public void generateComboLogs(String userInput) {
        try (Formatter output = new Formatter("./logs/phone/phone_combos_"+ userInput +".txt")) {
            output.format("Original Phone Number: %s", userInput);
            for (int i = 0; i < userInput.length(); i++) {
                System.out.println(phoneCodes.get(Character.toString(userInput.charAt(i)))[0]);
            }

        } catch (FileNotFoundException e) {
            System.err.println(e);
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PhoneCodeGenerator generator = new PhoneCodeGenerator();

        System.out.println("Enter a 7 digit phone number (XXX-XXXX): ");
        String userInput = scanner.nextLine();
        String[] numberParts = userInput.split("-");

        String userNumber = "";
        if (numberParts.length > 1) {
            userNumber = numberParts[0] + numberParts[1];
        } else {
            userNumber = numberParts[0];
        }


        generator.generateComboLogs(userNumber);
    }
}
