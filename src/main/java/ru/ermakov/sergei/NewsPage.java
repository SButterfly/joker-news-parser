package ru.ermakov.sergei;

import java.util.List;
/**
 * @author s-ermakov
 */
public class NewsPage {
    private final String title;
    private final List<String> paragraphs;

    public NewsPage(List<String> paragraphs) {
        this("", paragraphs);
    }

    public NewsPage(String title, List<String> paragraphs) {
        this.title = title;
        this.paragraphs = paragraphs;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getParagraphs() {
        return paragraphs;
    }
}
