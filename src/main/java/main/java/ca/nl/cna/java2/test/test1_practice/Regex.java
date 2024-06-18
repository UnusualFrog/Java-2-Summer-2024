package main.java.ca.nl.cna.java2.test.test1_practice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static void main(String[] args) {
        String script = "Hello, I'm the Nostalgia Critic. I remember it so you don't have to. And today, we're diving into something truly personal. Something that really hits home. That's right, I'm reviewing my own sensitive information, including... my phone numbers.\n\n" +
                "Now, you might be thinking, \"Critic, why would you 123-5656 review something so private?\" Well, it's simple. In the age of oversharing and digital footprints, sometimes you have to look back at the past and laugh at the absurdity of it all. Plus, it’s a hilarious reminder of how much information we give away without thinking twice. So, let's start with the basics.\n\n" +
                "Ah, my very first phone number. 555-1234. Classic, right? I remember getting this number when I was just a kid. Back when phone numbers were easy to remember and you didn't have to worry about telemarketers or spam calls. This number saw me through some of the most formative years of my life. Let's take a look at its highs and lows.\n\n" +
                "Remember those days when calling someone was a nerve-wracking experience? Especially if you had to talk to their parents first? \"Hi, Mrs. Smith, can Timmy come out to play?\" It was like navigating a minefield!\n\n" +
                "Simpler times, my friends. Simpler times. Now, let's move on to the next number.";

        Pattern pattern = Pattern.compile("\\d{3}-\\d{4}");
        Matcher matcher = pattern.matcher(script);

        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}
