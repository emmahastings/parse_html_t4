package com.hastings;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.FileVisitResult.CONTINUE;

/**
 * Created by emmakhastings on 24/06/2017.
 * <p>
 * Concrete implementation of SimpleFileVisitor used to traverse file tree and pass to processing service
 */
class FileVisitorService extends SimpleFileVisitor<Path> {

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes basicFileAttributes) throws IOException {

        // Get all regular HTML files
        if (basicFileAttributes.isRegularFile() && file.getFileName().toString().endsWith("html")) {
            FileProcessingService.processFile(file);
        }
        return CONTINUE;
    }
}