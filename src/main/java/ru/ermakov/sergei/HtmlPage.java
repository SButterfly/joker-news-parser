package ru.ermakov.sergei;

/**
 * @author s-ermakov
 */
public class HtmlPage {
    private final String url;
    private final String content;

    public HtmlPage(String url, String content) {
        this.url = url;
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public String getContent() {
        return content;
    }
}
