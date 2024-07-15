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

        //https://feeds.bbci.co.uk/news/world/rss.xml
        //https://www.nytimes.com/svc/collections/v1/publish/https://www.nytimes.com/section/world/rss.xml

        // Schedule the RSS feed checkers to run periodically
        for (String currentUrl : feedUrls) {
            executorService.scheduleAtFixedRate(new RSSFeedChecker(currentUrl), 0, 10, TimeUnit.SECONDS);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        // Shutdown the executor service
//         executorService.shutdown();

        // Close the scanner
        scanner.close();
    }
}