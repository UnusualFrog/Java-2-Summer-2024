package main.java.ca.nl.cna.java2.exercise.ex6_StringRegex.book;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexPractice {
    public static void main(String[] args) {
        String regex = "\\w*abc";
        Pattern expression = Pattern.compile(regex);

        String name = "abcdefghijkabcd";

        Matcher matcher = expression.matcher(name);

        System.out.println(matcher.matches());

        while (matcher.find()) {
            System.out.println(matcher.group());
        }


//        String name = "Eric Stock";
//        String name2 = "ErickStock";
//
//        //String regex = "[A-Za-z\\s]+";
//        //String regex = "[A-Za-z]+\\s[A-Za-z]+";
//        //String regex = "Hi (Eric|Stock)";
//        //String regex = "[A-Za-z]{4}\\s[A-Za-z]{2}";
//        String regex = "[A-Za-z]{4}\\s?[A-Za-z]+";
//
//        System.out.println(name.matches(regex));
//        System.out.println(name2.matches(regex));

    }
}
