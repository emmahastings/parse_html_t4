package com.hastings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * Created by emmakhastings on 24/06/2017.
 * <p>
 * Service to process HTML files and replace relative links with absolute links
 */
class FileProcessingService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    void processFile(Path file) throws IOException {
        // Create a backup of original
        createBackup(file.toAbsolutePath());

        // Update html
        HtmlUpdateService htmlUpdateService = new HtmlUpdateService();
        String content = htmlUpdateService.updateHtml(file);

        // Write changes to file
        try {
            BufferedWriter htmlWriter = Files.newBufferedWriter(file);
            htmlWriter.write(content);
            htmlWriter.close();

        } catch (IOException e) {
            logger.error("Writing to file failed with error: " + e.getMessage());
        }
    }

    /**
     * Create a backup of original file
     *
     * @param originalFile
     */
    private void createBackup(Path originalFile) throws IOException {
        File backUpFile = new File(originalFile + "_orig");
        backUpFile.createNewFile();

        // Make copy of original
        Files.copy(originalFile, Paths.get(backUpFile.toURI()), REPLACE_EXISTING);
    }
}
