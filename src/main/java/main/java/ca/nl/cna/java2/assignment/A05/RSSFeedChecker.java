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

public class RSSFeedChecker implements Runnable {
    private String feedUrl;
    private RSSItem lastItem;

    public RSSFeedChecker(String feedUrl) {
        this.feedUrl = feedUrl;
    }

    @Override
    public void run() {
        System.out.println("----- Update from RSS feed: " + feedUrl + " ------");

        checkFeed();
    }

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

            // Issue with code is that thread runs from start each time
            lastItem = items.get(0);

            System.out.println("Feed: " + feedUrl);
            if (!Objects.equals(items.get(items.size() - 1).getTitle(), lastItem.getTitle())) {
                for (int i = items.size() - Math.min(3, items.size()); i < items.size(); i++) {
                    RSSItem item = items.get(i);
                    System.out.println("Title: " + item.getTitle());
                    System.out.println("Link: " + item.getLink());
                    System.out.println("Published Date: " + item.getPubDate());
                    System.out.println();
                    lastItem = item;
                }

            } else {
                System.out.println("No new updates from");
            }
            System.out.print("Last item: ");
            System.out.println(lastItem.getTitle());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
