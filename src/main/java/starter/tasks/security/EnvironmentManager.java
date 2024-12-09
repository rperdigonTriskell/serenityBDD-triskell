package starter.tasks.security;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EnvironmentManager {

    private static final Properties properties = new Properties();
    private static final boolean isJenkins = System.getenv("JENKINS_HOME") != null;

    // Variable para almacenar el entorno actual
    private static String currentEnvironment;

    static {
        try {
            if (isJenkins) {
                String jenkinsEnvironmentPath = System.getenv("serenityEnvironmentFile");
                if (jenkinsEnvironmentPath != null) {
                    properties.load(new FileInputStream(jenkinsEnvironmentPath));
                    System.out.println("Cargando archivo de propiedades desde Jenkins: " + jenkinsEnvironmentPath);
                } else {
                    throw new RuntimeException("No serenityEnvironmentFile environment variable set in Jenkins");
                }
            } else {
                properties.load(new FileInputStream("src/test/resources/environment.properties"));
                System.out.println("Cargando archivo de propiedades localmente.");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error loading the environments properties file", e);
        }
    }

    public static String getEnvironmentBaseUrl(String tag) {
        String url = properties.getProperty(tag);
        if (url == null || url.isEmpty()) {
            throw new RuntimeException("No URL provided for the environment with tag: " + tag);
        }
        currentEnvironment = tag;
        return url;
    }

    public static String getCurrentEnvironment() {
        return currentEnvironment;
    }
}