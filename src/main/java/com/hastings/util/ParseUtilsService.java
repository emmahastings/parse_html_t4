package com.hastings.util;

import com.hastings.model.YamlConfig;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by emmakhastings on 25/06/2017.
 * <p>
 * Utility service to load yaml config file containing base URL
 */
public class ParseUtilsService {

    private static final YamlConfig yamlConfig;

    static {
        InputStream in = ClassLoader.getSystemResourceAsStream("config.yaml");
        Yaml yaml = new Yaml();
        yamlConfig = yaml.loadAs(in, YamlConfig.class);
        try {
            in.close();
        } catch (IOException e) {
            System.err.println("Error closing config file");
        }
    }

    /**
     * Extract absolute URL from config file
     *
     * @return Base URL
     */
    public static String getUrl() {
        return yamlConfig.getUrl();
    }
}
