package com.hastings;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Created by emmakhastings on 24/06/2017.
 */
class HtmlUpdateService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    String updateHtml(Path file) throws IOException {
        // Read and parse HTML file
        Document doc = Jsoup.parse(file.toFile(), "UTF-8", "");

        // Extract all links defined with the <a> tag:
        Elements links = doc.getElementsByTag("a");
        links.forEach(link -> {

            // Get link destination
            String linkDestination = link.attr("href");

            // Replace only relative links
            if (linkDestination.startsWith("../")) {
                String newLink = createAbsoluteLink(linkDestination);
                logger.info("Replacing " + linkDestination + " with " + newLink + " in file " + file.toAbsolutePath());
                link.attr("href", newLink);
            }
        });
        return doc.html();
    }

    /**
     * Split link based on ".." character sequence and concatenate path to supplied base URL
     *
     * @param link relative link in HTML file
     */
    private String createAbsoluteLink(String link) {
        String[] splitLink = link.split("\\.\\.");
        return "https://www.terminalfour.com" + splitLink[splitLink.length - 1];
    }

}

