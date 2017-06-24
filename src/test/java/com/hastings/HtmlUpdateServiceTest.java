package com.hastings;

import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * Created by emmakhastings on 24/06/2017.
 * <p>
 * Test HtmlUpdateService method updateHtml
 */
public class HtmlUpdateServiceTest {

    private HtmlUpdateService htmlUpdateService = new HtmlUpdateService();

    @Test
    public void updateHtml_ExpectAbsoluteLinks() throws Exception {

        URL url = this.getClass().getResource("/" + "test.html");
        File file = new File(url.getFile());
        String newHtmlContent = htmlUpdateService.updateHtml(Paths.get(file.toURI()));
        assertThat(newHtmlContent).isNotBlank()
                .contains("https://www.terminalfour.com/index.html")
                .contains("https://www.terminalfour.com/careers/index.html")
                .contains("https://www.terminalfour.com/blog/index.html")
                .contains("mailto:info-uk@terminalfour.com")
                .contains("src=\"https://www.terminalfour.com/index.html\"");
    }
}