package starter.tasks.security;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class EnvironmentManager {

    private static final Properties properties = new Properties();

    /**
     * Initializes the environment properties file.
     */
    static {
        try {
            properties.load(new FileInputStream("src/test/resources/environment.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Error loading the environments properties file", e);
        }
    }

    /**
     * Retrieves the base URL for a given environment tag.
     *
     * @param  tag the tag of the environment
     * @return      the base URL for the environment
     * @throws RuntimeException if no URL is provided for the environment
     */
    public static String getEnviromentBaseUrl(String tag) {
        String url = properties.getProperty(tag);
        if (url == null || url.isEmpty()) {
            throw new RuntimeException("No URL provided for the environment with tag: " + tag);
        }
        return url;
    }
}
