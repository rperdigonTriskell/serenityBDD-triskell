package starter.tasks.security;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EnvironmentManager {

    /**
     * Declaration of a static final Properties object.
     */
    private static final Properties properties = new Properties();

    /**
     * Static block to load properties from the config file.
     */
    static {
        try {
            // Load the properties file
            properties.load(new FileInputStream("src/test/resources/enviroments.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Error loading the credentials file", e);
        }
    }

    /**
     * Retrieves the base URL based on system properties or environment properties.
     *
     * @return the base URL for the webdriver
     * @throws RuntimeException if no URL is provided for the environment and no matching tag is found.
     */
    public static String getBaseUrl() {
        // First, check system properties for a direct URL
        String url = System.getProperty("webdriver.base.url");
        if (url != null && !url.isEmpty()) {
            return url;
        }

        // If no direct URL is provided, check environment tags
        String tag = System.getProperty("cucumber.filter.tags");
        if (tag != null && !tag.isEmpty()) {
            return properties.getProperty(tag);
        }

        // If no URL is found, throw an exception
        throw new RuntimeException("No URL provided for the environment and no matching tag found.");
    }
}
