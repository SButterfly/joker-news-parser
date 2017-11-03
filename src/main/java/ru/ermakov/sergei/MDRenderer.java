package ru.ermakov.sergei;

import com.google.common.base.Strings;

/**
 * @author s-ermakov
 */
public class MDRenderer {
    public String render(NewsPage newsPage) {
        StringBuilder stringBuilder = new StringBuilder();
        if (!Strings.isNullOrEmpty(newsPage.getTitle())) {
            stringBuilder.append("# ").append(newsPage.getTitle());
        }

        newsPage.getParagraphs().forEach(paragraph -> {
            stringBuilder.append("\n")
                    .append(paragraph)
                    .append("\n");
        });

        return stringBuilder.toString();
    }
}
