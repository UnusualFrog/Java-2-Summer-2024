package main.java.ca.nl.cna.java2.assignment.A04;

import java.util.Collections;
import java.util.LinkedList;

public class CopyReverseLinkedListQ4 {
    public static void main(String[] args) {
        char[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
        LinkedList<Character> listForwards = new LinkedList<>();

        for (char letter : chars) {
            listForwards.add(letter);
        }

        LinkedList<Character> listBackwards = new LinkedList<>(listForwards);
        Collections.reverse(listBackwards);

        System.out.println("Original List: ");
        for (char letter : listForwards) {
            System.out.print(letter + " ");
        }
        System.out.println();

        System.out.println("Reversed Copy: ");
        for (char letter : listBackwards) {
            System.out.print(letter + " ");
        }
        System.out.println();
    }
}
