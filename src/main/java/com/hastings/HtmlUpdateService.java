package com.hastings;

import com.hastings.util.ParseUtilsService;
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

    private final String baseUrl = ParseUtilsService.getUrl();

    String updateHtml(Path file) throws IOException {
        // Read and parse HTML file
        Document doc = Jsoup.parse(file.toFile(), "UTF-8", "");
        logger.info("Processing " + file.toAbsolutePath());

        // Replace links in 'img' and 'a' tags
        processTag("a", "href", doc);
        processTag("img", "src", doc);
        processTag("link", "href", doc);
        return doc.html();
    }

    /**
     * Replace links for selected tag in supplied HTML document
     *
     * @param tag       HTML tag
     * @param attribute HTML attribute to replace with absolute link, should be a URL
     * @param doc       HTML document
     */
    private void processTag(String tag, String attribute, Document doc) {

        // Extract all links defined with the  tag:
        Elements links = doc.getElementsByTag(tag);
        links.forEach(link -> {

            // Get existing link destination
            String linkDestination = link.attr(attribute);

            // Replace relative links
            if (!linkDestination.isEmpty()) {
                // Ignore existing absolute links, mail links and links to locations in current file
                if (!linkDestination.startsWith("#") && !linkDestination.startsWith("http") && !linkDestination.startsWith("mailto")) {
                    String newLink = createAbsoluteLink(linkDestination);
                    logger.info("Replacing " + linkDestination + " with " + newLink);
                    link.attr(attribute, newLink);
                }
            }
        });
    }

    /**
     * Creates absolute link based on paths inferred from files
     *
     * @param link relative link in HTML file
     */
    private String createAbsoluteLink(String link) {
        // Handle links that are paths relative to the current file
        if (link.startsWith("../")) {
            String[] splitLink = link.split("\\.\\.");
            return baseUrl + splitLink[splitLink.length - 1];
        } else {
            // Handle links to files in same directory as current file
            return baseUrl + "/" + link;
        }
    }
}