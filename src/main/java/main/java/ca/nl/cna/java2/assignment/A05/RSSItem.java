package main.java.ca.nl.cna.java2.assignment.A05;

/**
 * Represents an RSS item with title, link, and publication date.
 */
public class RSSItem {
    private String title;
    private String link;
    private String pubDate;

    /**
     * Constructor for RSSItem.
     *
     * @param title   The title of the RSS item.
     * @param link    The link associated with the RSS item.
     * @param pubDate The publication date of the RSS item.
     */
    public RSSItem(String title, String link, String pubDate) {
        this.title = title;
        this.link = link;
        this.pubDate = pubDate;
    }

    /**
     * Retrieves the title of the RSS item.
     *
     * @return The title of the RSS item.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Retrieves the link associated with the RSS item.
     *
     * @return The link associated with the RSS item.
     */
    public String getLink() {
        return link;
    }

    /**
     * Retrieves the publication date of the RSS item.
     *
     * @return The publication date of the RSS item.
     */
    public String getPubDate() {
        return pubDate;
    }
}
