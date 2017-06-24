package com.hastings;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by emmakhastings on 24/06/2017.
 */
public class ParseApplication {

    public static void main(String args[]) {
        Path cwd = Paths.get(".").toAbsolutePath().normalize();
        System.out.println("Running in " + cwd.toString());
    }
}
