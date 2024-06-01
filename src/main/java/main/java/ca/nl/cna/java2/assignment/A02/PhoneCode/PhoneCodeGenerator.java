package main.java.ca.nl.cna.java2.assignment.A02.PhoneCode;

import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Scanner;

public class PhoneCodeGenerator {
    private final HashMap<Integer, String[]> phoneCodes = new HashMap<>();

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

    public void generateComboLogs(String userInput) {
        try (Formatter output = new Formatter("./logs/phone/phone_combos_"+ userInput +".txt")) {
            output.format("Original Phone Number: %s \n", userInput);
            generateCombo(output, userInput, "", 0, 0);

        } catch (FileNotFoundException e) {
            System.err.println(e);
        }
    }

    private void generateCombo(Formatter output, String inputCode, String outputCode, int inputIndex, int hashIndex) {
        int currentDigit = Integer.parseInt(inputCode.charAt(inputIndex) + "");
        if (inputIndex == inputCode.length()-1) {
            output.format("%s\n", outputCode);
            return;
        } else {
            for (int i = 0; i < 3; i++) {
                generateCombo(output, inputCode, outputCode + this.getPhoneCodes().get(currentDigit)[i], inputIndex + 1, i);
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

        generator.generateComboLogs(userNumber);
    }
}
