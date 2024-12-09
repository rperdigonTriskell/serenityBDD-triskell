package starter.tasks.security;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CredentialManager {

    private static final Properties properties = new Properties();
    private static final boolean isJenkins = System.getenv("JENKINS_HOME") != null;

    static {
        try {
            if (isJenkins) {
                // Jenkins environment: cargar el archivo directamente
                String jenkinsCredentialsPath = System.getenv("CREDENTIALS_FILE");

                if (jenkinsCredentialsPath != null) {
                    // Cargar el archivo de credenciales usando la ruta proporcionada
                    properties.load(new FileInputStream(jenkinsCredentialsPath));
                } else {
                    throw new RuntimeException("No CREDENTIALS_FILE environment variable set in Jenkins");
                }
            } else {
                // Local environment
                properties.load(new FileInputStream("src/test/resources/config.properties"));
            }
        } catch (IOException e) {
            throw new RuntimeException("Error loading the credentials file", e);
        }
    }

    public static String getCredential(String credentialName, boolean useAwsSecrets) {
        if (useAwsSecrets) {
            throw new UnsupportedOperationException("AWS credential retrieval not yet implemented");
        }

        // Get the credential from the properties file
        String credentialValue = properties.getProperty(credentialName);
        if (credentialValue == null) {
            credentialValue = credentialName; // Return the credential name if not found
        }
        return credentialValue;
    }
}