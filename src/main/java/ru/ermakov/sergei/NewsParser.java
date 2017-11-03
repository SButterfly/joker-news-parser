package ru.ermakov.sergei;

import org.jsoup.Jsoup;
import ru.ermakov.sergei.parsers.MedizaParser;
import ru.ermakov.sergei.parsers.Parser;

import java.util.Arrays;

/**
 * @author s-ermakov
 */
public class NewsParser {
    public NewsPage parse(HtmlPage html) {
        Parser parser = getParser(html);
        return parser.parse(html);
    }

    public Parser getParser(HtmlPage htmlPage) {
        if (htmlPage.getUrl().contains("meduza")) {
            return new MedizaParser();
        }
        return htmlPage1 -> new NewsPage(Arrays.asList(Jsoup.parse(htmlPage1.getContent()).text()));
    }

}
