package main.java.ca.nl.cna.java2.assignment.A02.PhoneCode;

import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Scanner;

public class PhoneCodeGenerator {
    private final HashMap<Integer, String[]> phoneCodes = new HashMap<>();

    // Construct phone code hash map
    public PhoneCodeGenerator() {
        phoneCodes.put(2,new String[]{"A","B","C"});
        phoneCodes.put(3,new String[]{"D","E","F"});
        phoneCodes.put(4,new String[]{"G","H","I"});
        phoneCodes.put(5,new String[]{"J","K","L"});
        phoneCodes.put(6,new String[]{"M","N","O"});
        phoneCodes.put(7,new String[]{"P","R","S"});
        phoneCodes.put(8,new String[]{"T","U","V"});
        phoneCodes.put(9,new String[]{"W","X","Y"});
    }

    public HashMap<Integer, String[]> getPhoneCodes() {
        return phoneCodes;
    }

    // Add header to file and start recursion
    public void generateComboLogs(String userInput) {
        try (Formatter output = new Formatter("./logs/phone/phone_combos_"+ userInput +".txt")) {
            output.format("Original Phone Number: %s \n", userInput);
            generateCombo(output, userInput, "", 0);

        } catch (FileNotFoundException e) {
            System.err.println(e);
        }
    }

    private void generateCombo(Formatter output, String inputCode, String outputCode, int inputIndex) {
        // Write output code to file when index reaches end of input code
        if (inputIndex == inputCode.length()) {
            output.format("%s\n", outputCode);
            return;
        } else {
            int currentDigit = Integer.parseInt(inputCode.charAt(inputIndex) + "");
            // Make recursive call using all 3 of the letter codes per digit of the input code
            for (int i = 0; i < 3; i++) {
                generateCombo(output, inputCode, outputCode + this.getPhoneCodes().get(currentDigit)[i], inputIndex + 1);
            }
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PhoneCodeGenerator generator = new PhoneCodeGenerator();

        System.out.println("Enter a 7 digit phone number (XXX-XXXX): ");
        String userInput = scanner.nextLine();
        String[] numberParts = userInput.split("-");

        // Handle dash in phone number
        String userNumber = "";
        if (numberParts.length > 1) {
            userNumber = numberParts[0] + numberParts[1];
        } else {
            userNumber = numberParts[0];
        }

        try {
            generator.generateComboLogs(userNumber);
        } catch (NumberFormatException e) {
            System.err.println("Invalid Input: Input must be length of 7 and include only digits");
        }
    }
}
