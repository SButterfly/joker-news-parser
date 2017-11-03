package ru.ermakov.sergei.parsers;

import com.google.common.base.Strings;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import ru.ermakov.sergei.HtmlPage;
import ru.ermakov.sergei.NewsPage;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author s-ermakov
 */
public class MedizaParser implements Parser {

    @Override
    public NewsPage parse(HtmlPage htmlPage) {
        Document document = Jsoup.parse(htmlPage.getContent());
        String header = parseHeader(document);
        List<String> paragraphs = parseParagraphs(document);

        return new NewsPage(header, paragraphs);
    }

    private String parseHeader(Document document) {
        Element headerElement = document.select("span.NewsMaterialHeader-first").first();
        return headerElement == null ? "" : headerElement.text();
    }

    private List<String> parseParagraphs(Document document) {
        Element dangerousHtml = document.select("div.DangerousHTML").first();
        if (dangerousHtml == null) {
            return Collections.emptyList();
        }

        Element body = dangerousHtml.select("div.Body").first();
        if (body == null) {
            return Collections.emptyList();
        }

        List<String> paragraphs = body
                .children().stream()
                .filter(element -> !element.attr("class").equals("EmbedCode"))
                .flatMap(element -> element.getAllElements().stream())
                .filter(element -> element.children().isEmpty())
                .map(element -> element.text())
                .filter(paragraph -> !Strings.isNullOrEmpty(paragraph))
                .collect(Collectors.toList());
        return paragraphs;
    }
}
