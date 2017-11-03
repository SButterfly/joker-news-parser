package ru.ermakov.sergei;

import com.google.common.base.Strings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private static final Logger log = LogManager.getLogger(Main.class);

    private static final String NEWS_URLS_FILE = "news.urls.file";

    public static void main(String[] args) {
        log.debug("Start parsing");

        String newsUrlsFilePath = System.getProperty(NEWS_URLS_FILE);
        List<String> newsUrls = getNewsUrls(newsUrlsFilePath);
        log.debug(String.format("Got %d urls:\n%s", newsUrls.size(), String.join("\n", newsUrls)));

        NewsConverter newsConverter = new NewsConverter();
        List<String> mdFiles = newsConverter.convert(newsUrls);

        writeMdFiles(mdFiles);

        log.debug("Finished parsing");
    }

    private static List<String> getNewsUrls(String newsUrlsFilePath) {
        if (Strings.isNullOrEmpty(newsUrlsFilePath)) {
            throw new IllegalStateException("No path is provided!");
        }

        try {
            return Files.lines(new File(newsUrlsFilePath).toPath())
                    .filter(string -> !Strings.isNullOrEmpty(string))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file: " + newsUrlsFilePath);
        }
    }

    private static void writeMdFiles(List<String> mds) {
        for (int i = 0; i < mds.size(); i++) {
            saveToFile((i + 1) + ".md", mds.get(i));
        }
    }

    private static void saveToFile(String fileName, String content) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Failed to create file: " + fileName);
        }
    }
}
