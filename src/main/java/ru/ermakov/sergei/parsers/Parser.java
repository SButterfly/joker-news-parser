package ru.ermakov.sergei.parsers;

import ru.ermakov.sergei.HtmlPage;
import ru.ermakov.sergei.NewsPage;

/**
 * @author s-ermakov
 */
public interface Parser {
    NewsPage parse(HtmlPage htmlPage);
}
