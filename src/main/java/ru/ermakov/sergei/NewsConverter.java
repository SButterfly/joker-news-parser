package ru.ermakov.sergei;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author s-ermakov
 */
public class NewsConverter {
    private static final Logger log = LogManager.getLogger(NewsConverter.class);

    private final PageLoader pageLoader = new PageLoader();
    private final NewsParser newsParser = new NewsParser();
    private final MDRenderer mdRenderer = new MDRenderer();

    public List<String> convert(List<String> newsUrls) {
        log.debug("Start load html pages");

        List<HtmlPage> htmlPages = newsUrls.stream()
                .map(pageLoader::loadPage)
                .collect(Collectors.toList());

        log.debug("Start parsing html page");

        List<NewsPage> newsPages = htmlPages.stream()
                .map(newsParser::parse)
                .collect(Collectors.toList());

        log.debug("Start converting pages");

        List<String> mdFiles = newsPages.stream()
                .map(mdRenderer::render)
                .collect(Collectors.toList());

        log.debug("Convert finished");
        return mdFiles;
    }
}
