package ru.ermakov.sergei;

import org.jsoup.Jsoup;
import ru.ermakov.sergei.parsers.FontankaParser;
import ru.ermakov.sergei.parsers.JugParser;
import ru.ermakov.sergei.parsers.KomersantParser;
import ru.ermakov.sergei.parsers.MedizaParser;
import ru.ermakov.sergei.parsers.Nplus1Parser;
import ru.ermakov.sergei.parsers.Parser;

import java.util.Collections;

/**
 * @author s-ermakov
 */
public class NewsParser {
    public NewsPage parse(HtmlPage html) {
        Parser parser = getParser(html);
        return parser.parse(html);
    }

    public Parser getParser(HtmlPage htmlPage) {
        String url = htmlPage.getUrl();
        if (url.contains("meduza")) {
            return new MedizaParser();
        }
        if (url.contains("kommersant")) {
            return new KomersantParser();
        }
        if (url.contains("jug")) {
            return new JugParser();
        }
        if (url.contains("nplus1")) {
            return new Nplus1Parser();
        }
        if (url.contains("fontanka")) {
            return new FontankaParser();
        }
        return htmlPage1 -> new NewsPage(Collections.singletonList(Jsoup.parse(htmlPage1.getContent()).text()));
    }

}
