package com.investaSolutions.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PropertiesManager {

    private static final Logger log = LogManager.getLogger(PropertiesManager.class);
    private static PropertiesManager envProperties = null;
    private final Properties configProperties;
    private final Properties constants;
    private final Properties logMessages;
    private final Properties pagesURL;
    private final Properties env;

    private PropertiesManager() {
        configProperties = loadProperties("Config.properties");
        constants = loadProperties("Constants.properties");
        logMessages = loadProperties("LogMessages.properties");
        pagesURL = loadProperties("URLs.properties");
        env = loadProperties("Environment.properties");
    }

    private Properties loadProperties(String fileName) {
        Properties props = new Properties();
        
        // Using getResourceAsStream to load properties from the class path
        try (InputStream inputStream = PropertiesManager.class.getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream != null) {
                props.load(inputStream);
            } else {
            	log.error("Properties file '" + fileName + "' not found in the classpath");
                throw new RuntimeException("Properties file '" + fileName + "' not found in the classpath");                
            }
        } catch (IOException e) {
            System.err.println("Failed to load properties file: " + fileName);
            log.error("Failed to load properties file: " + fileName);
            e.printStackTrace();
        }
        
        return props;
    }

    public static PropertiesManager getInstance() {
        if (envProperties == null) {
            envProperties = new PropertiesManager();
        }
        return envProperties;
    }

    public String getConfig(String key) {
        return getPropertyWithFallback(configProperties, key, "Config.properties");
    }

    public String getConstant(String key) {
        return getPropertyWithFallback(constants, key, "Constants.properties");
    }

    public String getLogMessage(String key) {
        return getPropertyWithFallback(logMessages, key, "LogMessages.properties");
    }

    public String getPagesURL(String key) {
        return getPropertyWithFallback(pagesURL, key, "URLs.properties");
    }

    public String getEnv(String key) {
        return getPropertyWithFallback(env, key, "Environment.properties");
    }

    private String getPropertyWithFallback(Properties properties, String key, String fileName) {
        String value = properties.getProperty(key);
        if (value == null) {
            log.warn("Property '{}' not found in {}", key, fileName);
        }
        return value;
    }
}
