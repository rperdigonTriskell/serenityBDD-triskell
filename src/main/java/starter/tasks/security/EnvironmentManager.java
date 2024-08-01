package starter.tasks.security;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class EnvironmentManager {

    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(new FileInputStream("src/test/resources/environment.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Error loading the environments properties file", e);
        }
    }

    public static String getBaseUrl(String tag) {
        String url = properties.getProperty(tag);
        if (url == null || url.isEmpty()) {
            throw new RuntimeException("No URL provided for the environment with tag: " + tag);
        }
        return url;
    }
}
