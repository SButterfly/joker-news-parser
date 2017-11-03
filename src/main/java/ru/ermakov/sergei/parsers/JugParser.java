package ru.ermakov.sergei.parsers;

import com.google.common.base.Strings;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author s-ermakov
 */
public class JugParser extends BaseParser {

    @Override
    protected String parseHeader(Document document) {
        Element headerElement = document.select("h1.entry-title").first();
        return headerElement == null ? "" : headerElement.text();
    }

    @Override
    protected List<String> parseParagraphs(Document document) {
        Element body = document.select("article.entry-content").first();
        if (body == null) {
            return Collections.emptyList();
        }

        List<String> paragraphs = body.child(0).children().stream()
                .map(element -> element.text())
                .filter(paragraph -> !Strings.isNullOrEmpty(paragraph))
                .collect(Collectors.toList());
        return paragraphs;
    }
}
