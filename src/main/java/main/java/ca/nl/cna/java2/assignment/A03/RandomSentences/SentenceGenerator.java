package main.java.ca.nl.cna.java2.assignment.A03.RandomSentences;

import java.util.Random;

public class SentenceGenerator {
    private final String[] articles = {"the", "a", "one", "some", "any"};
    private final String[] nouns = {"boy", "girl", "dog", "town", "car"};
    private final String[] verbs = {"drove", "jumped", "ran", "walked", "skipped"};
    private final String[] prepositions = {"to", "from", "over", "under", "on"};

    public String generateSentence() {
        Random random = new Random();
        String outputSentence = "";

        String firstWord = articles[random.nextInt(0,articles.length)];
        // Set first letter to uppercase
        firstWord = firstWord.substring(0,1).toUpperCase() + firstWord.substring(1);
        outputSentence += firstWord;
        outputSentence += " ";
        outputSentence += nouns[random.nextInt(0,nouns.length)];
        outputSentence += " ";
        outputSentence += verbs[random.nextInt(0,verbs.length)];
        outputSentence += " ";
        outputSentence += prepositions[random.nextInt(0,prepositions.length)];
        outputSentence += " ";
        outputSentence += articles[random.nextInt(0,articles.length)];
        outputSentence += " ";
        outputSentence += nouns[random.nextInt(0,nouns.length)];
        outputSentence += ".";

        return outputSentence;
    }

    public static void main(String[] args) {
        SentenceGenerator generator = new SentenceGenerator();

        for (int i = 0; i < 20; i++) {
            System.out.println(generator.generateSentence());
        }
    }
}
