package starter.tasks.security;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CredentialManager {

    private static final Properties properties = new Properties();
    private static final Properties environmentProperties = new Properties();

    static {
        try {
            String credentialsFilePath = System.getenv("CREDENTIALS_FILE");
            if (credentialsFilePath == null || credentialsFilePath.isEmpty()) {
                throw new RuntimeException("The CREDENTIALS_FILE environment variable is not set.");
            }
            properties.load(new FileInputStream(credentialsFilePath));

            String environmentFilePath = "src/test/resources/environments.properties";
            environmentProperties.load(new FileInputStream(environmentFilePath));

        } catch (IOException e) {
            throw new RuntimeException("Error loading the configuration files", e);
        }
    }

    public static String getCredential(String credentialName, boolean useAwsSecrets) {
        if (useAwsSecrets) {
            throw new UnsupportedOperationException("AWS credential retrieval not yet implemented");
        }

        String environment = System.getenv("ENVIRONMENT");
        if (environment == null || environment.isEmpty()) {
            environment = "@PROD"; // Valor por defecto
        }

        String environmentUrl = environmentProperties.getProperty(environment);
        if (environmentUrl == null) {
            throw new RuntimeException("Environment URL not found for key: " + environment);
        }

        String credentialValue = properties.getProperty(environmentUrl);
        if (credentialValue == null) {
            throw new RuntimeException("Credential not found for key: " + environmentUrl);
        }

        return credentialValue;
    }
}
