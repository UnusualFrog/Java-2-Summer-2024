package main.java.ca.nl.cna.java2.assignment.A05;

public class RSSItem {
    private String title;
    private String link;
    private String pubDate;

    public RSSItem(String title, String link, String pubDate) {
        this.title = title;
        this.link = link;
        this.pubDate = pubDate;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getPubDate() {
        return pubDate;
    }
}
