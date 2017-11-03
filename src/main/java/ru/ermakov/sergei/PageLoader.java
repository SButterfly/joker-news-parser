package ru.ermakov.sergei;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;

import java.io.IOException;

/**
 * @author s-ermakov
 */
public class PageLoader {
    private static final Logger log = LogManager.getLogger(PageLoader.class);

    public HtmlPage loadPage(String urlStr) {
        try {
            return new HtmlPage(urlStr, Jsoup.connect(urlStr).get().html());
        } catch (IOException e) {
            throw new RuntimeException("Failed to load" + urlStr, e);
        }
    }
}
