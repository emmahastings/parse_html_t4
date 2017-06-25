package com.hastings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by emmakhastings on 24/06/2017.
 * <p>
 * Application class that acts as entry point for parsing HTML files.
 * Uses current working dir as location of files
 */
public class ParseApplication {

    public static void main(String args[]) {
        // Get current working directory
        Path cwd = Paths.get(".").toAbsolutePath().normalize();
        System.out.println("Parse application running in  " + cwd.toString());

        // Create file visitor instance used to traverse file tree
        FileVisitorService fileVisitorService = new FileVisitorService();
        try {
            Files.walkFileTree(cwd, fileVisitorService);
        } catch (IOException e) {
            System.err.println("Parse application failed with the following error  " + e.getMessage());
        }
        System.out.println("Parsing complete");
    }
}
