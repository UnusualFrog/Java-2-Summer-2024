package main.java.ca.nl.cna.java2.assignment.A03.ReverseSentence;

import java.util.Scanner;

public class ReverseSentence {

    public static String reverseSentence(String sentence) {
        String[] words = sentence.split(" ");
        StringBuilder outputSentence = new StringBuilder();

        for (int i = words.length-1; i >= 0; i--) {
            outputSentence.append(words[i]);
            outputSentence.append(" ");
        }

        return outputSentence.toString().trim();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a sentence to reverse: ");
        String userSentence = scanner.nextLine();
        System.out.println(ReverseSentence.reverseSentence(userSentence));
    }
}
