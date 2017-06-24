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
 * <p>
 * Service to process HTML files and replace relative links with absolute links
 */
class HtmlUpdateService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    String updateHtml(Path file) throws IOException {
        // Read and parse HTML file
        Document doc = Jsoup.parse(file.toFile(), "UTF-8", "");
        logger.info("Processing " + file.toAbsolutePath());

        // Replace links in img and a tags
        processTag("a", "href", doc);
        processTag("img", "src", doc);
        return doc.html();
    }

    /**
     * Replace links for selected tag in supplied HTML document
     *
     * @param tag       HTML tag
     * @param attribute HTML attribute to replace with absolute link
     * @param doc       HTML document
     */
    private void processTag(String tag, String attribute, Document doc) {

        // Extract all links defined with the  tag:
        Elements links = doc.getElementsByTag(tag);
        links.forEach(link -> {

            // Get link destination
            String linkDestination = link.attr(attribute);

            // Replace relative links
            if (!linkDestination.isEmpty()) {
                if (!linkDestination.startsWith("#") && !linkDestination.startsWith("http") && !linkDestination.startsWith("mailto")) {
                    String newLink = createAbsoluteLink(linkDestination);
                    logger.info("Replacing " + linkDestination + " with " + newLink);
                    link.attr(attribute, newLink);
                }
            }
        });
    }

    /**
     * Split link based on ".." character sequence and concatenate path to supplied base URL
     *
     * @param link relative link in HTML file
     */
    private String createAbsoluteLink(String link) {
        if (link.startsWith("../")) {
            String[] splitLink = link.split("\\.\\.");
            return "https://www.terminalfour.com" + splitLink[splitLink.length - 1];
        } else {
            return "https://www.terminalfour.com" + link;
        }
    }
}