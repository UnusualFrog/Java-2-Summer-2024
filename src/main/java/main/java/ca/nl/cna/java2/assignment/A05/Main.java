package main.java.ca.nl.cna.java2.assignment.A05;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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

        // Create scheduled executor
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(feedUrls.size());

        // Schedule the RSS feed checkers to run periodically
        for (String currentUrl : feedUrls) {
            executorService.scheduleAtFixedRate(new RSSFeedChecker(currentUrl), 0, 30, TimeUnit.SECONDS);
        }

        // Shutdown the executor service
//         executorService.shutdown();

        // Close the scanner
        scanner.close();
    }
}