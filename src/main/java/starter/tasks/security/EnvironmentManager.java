package starter.tasks.security;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EnvironmentManager {

    private static final Properties properties = new Properties();
    private static final boolean isJenkins = System.getenv("JENKINS_HOME") != null;

    /**
     * Initializes the environment properties file.
     */
    static {
        try {
            // Si estamos en Jenkins, podemos usar la ruta del archivo de entornos directamente del workspace
            if (isJenkins) {
                String jenkinsEnvironmentPath = System.getenv("serenityEnvironmentFile");
                if (jenkinsEnvironmentPath != null) {
                    properties.load(new FileInputStream(jenkinsEnvironmentPath));
                } else {
                    throw new RuntimeException("No serenityEnvironmentFile environment variable set in Jenkins");
                }
            } else {
                // Si estamos localmente, usamos el archivo de propiedades en el proyecto
                properties.load(new FileInputStream("src/test/resources/environment.properties"));
            }
        } catch (IOException e) {
            throw new RuntimeException("Error loading the environments properties file", e);
        }
    }

    /**
     * Retrieves the base URL for a given environment tag.
     *
     * @param tag the tag of the environment
     * @return the base URL for the environment
     * @throws RuntimeException if no URL is provided for the environment
     */
    public static String getEnvironmentBaseUrl(String tag) {
        String url = properties.getProperty(tag);
        if (url == null || url.isEmpty()) {
            throw new RuntimeException("No URL provided for the environment with tag: " + tag);
        }
        return url;
    }
}