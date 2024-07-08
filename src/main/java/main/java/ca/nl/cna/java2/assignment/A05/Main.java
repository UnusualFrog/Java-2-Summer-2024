package main.java.ca.nl.cna.java2.assignment.A05;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        // Initial Values
        Scanner scanner = new Scanner(System.in);
        List<String> feedUrls = new ArrayList<>();
        final String end = "done";
        String url = "";

        // Receive user URL input
        System.out.println("Enter RSS feed URLs (type 'done' to finish):");
        while (!url.equals(end)) {
            url = scanner.nextLine();
            if (!url.equalsIgnoreCase(end)) {
                feedUrls.add(url);
            }
        }

        // Run feed checker with executor
        try ( ScheduledExecutorService scheduler
                      = Executors.newScheduledThreadPool(11); ) {
            for (String current_url : feedUrls) {
                scheduler.scheduleAtFixedRate(new RSSFeedChecker(current_url), 0, 10, TimeUnit.SECONDS);
            }

            scheduler.shutdown();

        } catch (Exception e) {
            System.err.println(e);
        }





//        for (String feedUrl : feedUrls) {
//            RSSFeedChecker checker = new RSSFeedChecker();
//            checker.checkFeed();
//        }
    }
}
