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
public class Nplus1 extends BaseParser {

    @Override
    public String parseHeader(Document document) {
        Element headerElement = document.select("header.hero").first();
        return headerElement == null ? "" : headerElement.text();
    }

    @Override
    public List<String> parseParagraphs(Document document) {
        Element body = document.select("div.body").first();
        if (body == null) {
            return Collections.emptyList();
        }

        List<String> paragraphs = body
                .children().stream()
                .filter(element -> element.tag().getName().equals("p"))
                .map(element -> element.text())
                .filter(paragraph -> !Strings.isNullOrEmpty(paragraph))
                .collect(Collectors.toList());
        return paragraphs;
    }
}
