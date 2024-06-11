package main.java.ca.nl.cna.java2.exercise.ex6_StringRegex.book;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RegexGreedy {
    public static void main(String[] args) {
        Pattern lazyPattern = Pattern.compile("a.*?c");
        Pattern greedyPattern = Pattern.compile("a.*c");
        Matcher lazyMatcher = lazyPattern.matcher("abcdefghijklmc");
        Matcher greedyMatcher = greedyPattern.matcher("abcdefghijklmc");

        System.out.println("Lazy match: ");
        if(lazyMatcher.find()) {
            System.out.println(lazyMatcher.group());
        }
        System.out.println();

        System.out.println("Greedy match: ");
        if(greedyMatcher.find()) {
            System.out.println(greedyMatcher.group());
        }
        System.out.println();


    }
}
