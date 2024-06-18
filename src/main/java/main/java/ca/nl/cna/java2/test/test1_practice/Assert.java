package main.java.ca.nl.cna.java2.test.test1_practice;

import java.util.Scanner;

public class Assert {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter a number: ");
        String user = input.nextLine();
        assert (Integer.parseInt(user) > 0) : "Fuck You! =D";
    }
}
