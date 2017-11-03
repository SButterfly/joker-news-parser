package ru.ermakov.sergei.parsers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ru.ermakov.sergei.HtmlPage;
import ru.ermakov.sergei.NewsPage;

import java.util.List;

/**
 * @author s-ermakov
 */
public abstract class BaseParser implements Parser {

    @Override
    public NewsPage parse(HtmlPage htmlPage) {
        Document document = Jsoup.parse(htmlPage.getContent());
        String header = parseHeader(document);
        List<String> paragraphs = parseParagraphs(document);

        return new NewsPage(header, paragraphs);
    }

    protected abstract String parseHeader(Document document);

    protected abstract List<String> parseParagraphs(Document document);
}
