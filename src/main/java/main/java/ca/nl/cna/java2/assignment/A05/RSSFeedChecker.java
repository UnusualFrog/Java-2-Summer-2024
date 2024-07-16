package main.java.ca.nl.cna.java2.assignment.A05;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * RSSFeedChecker is a Runnable class that periodically checks an RSS feed for updates.
 */
public class RSSFeedChecker implements Runnable {
    private final String feedUrl;
    private RSSItem lastItem;

    /**
     * Constructor for RSSFeedChecker.
     *
     * @param feedUrl The URL of the RSS feed to monitor.
     */
    public RSSFeedChecker(String feedUrl) {
        this.feedUrl = feedUrl;
    }

    /**
     * Implements the run method from the Runnable interface.
     * Initiates the feed checking process.
     */
    @Override
    public void run() {
        checkFeed();
    }

    /**
     * Checks the RSS feed for updates.
     * This method parses the XML content from the feed and prints recent items if updated.
     */
    public void checkFeed() {
        try {
            URL url = new URL(feedUrl);
            //XML Document building
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(url.openStream());

            //This is how you work with XML - you do not need to modify this!
            NodeList itemList = doc.getElementsByTagName("item");
            List<RSSItem> items = new ArrayList<>();

            for (int i = 0; i < itemList.getLength(); i++) {
                Node itemNode = itemList.item(i);
                if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element itemElement = (Element) itemNode;
                    String title = itemElement.getElementsByTagName("title").item(0).getTextContent();
                    String link = itemElement.getElementsByTagName("link").item(0).getTextContent();
                    String pubDate = itemElement.getElementsByTagName("pubDate").item(0).getTextContent();
                    items.add(new RSSItem(title, link, pubDate));
                }
            }

            // Check if this is the first run of the feed or if this is an update of the feed
            if (this.lastItem != null) {
                // Check if the last item viewed differs from the most recent item to determine if the feed updated
                if (!Objects.equals(items.get(Math.min(3, items.size())-1).getTitle(), this.lastItem.getTitle())) {
                    getLastThreeItems(items);

                }
            } else {
                getLastThreeItems(items);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Prints details of the last three RSS items retrieved.
     * Updates the lastItem field with the most recent item for future comparisons.
     *
     * @param items List of RSSItem objects representing the items from the RSS feed.
     */
    private void getLastThreeItems(List<RSSItem> items) {
        System.out.println("----- Update from RSS feed: " + feedUrl + " ------");
        for (int i = 0; i < Math.min(3, items.size()); i++) {
            RSSItem item = items.get(i);
            System.out.println("Title: " + item.getTitle());
            System.out.println("Link: " + item.getLink());
            System.out.println("Published Date: " + item.getPubDate());
            System.out.println();
            this.lastItem = item;
        }
        System.out.println("-".repeat(50));
    }
}
