package com.investaSolutions.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static Properties properties = new Properties();

    static {
        String fileName = "URLs.properties"; // Specify the file name
        try (InputStream inputStream = ConfigManager.class.getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new RuntimeException("Properties file '" + fileName + "' not found in the classpath");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load properties file: " + fileName);
        }
    }

    public static String getBaseUri(String environment) {
        return properties.getProperty(environment + ".baseURI", properties.getProperty("baseURI"));
    }

    public static String getBasePath(String environment) {
        return properties.getProperty(environment + ".basePath", properties.getProperty("basePath"));
    }

    // Overloaded for default environment
    public static String getBaseUri() {
        return getBaseUri("");
    }

    public static String getBasePath() {
        return getBasePath("");
    }
}