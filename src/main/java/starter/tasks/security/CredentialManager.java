package starter.tasks.security;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CredentialManager {

    private static final Properties properties = new Properties();
    private static final boolean isJenkins = System.getenv("JENKINS_HOME") != null;

    static {
        try {
            // Si estamos en Jenkins, usa el archivo del workspace de Jenkins
            if (isJenkins) {
                String jenkinsCredentialsPath = System.getenv("serenityConfigFile");
                if (jenkinsCredentialsPath != null) {
                    properties.load(new FileInputStream(jenkinsCredentialsPath));
                } else {
                    throw new RuntimeException("No serenityConfigFile environment variable set in Jenkins");
                }
            } else {
                // Si estamos localmente, usa el archivo de propiedades local
                properties.load(new FileInputStream("src/test/resources/config.properties"));
            }
        } catch (IOException e) {
            throw new RuntimeException("Error loading the credentials file", e);
        }
    }

    /**
     * Get the specified credential.
     *
     * @param credentialName Name of the credential to retrieve
     * @param useAwsSecrets Indicates whether AWS Secrets Manager should be used to retrieve the credential (not used in this case)
     * @return The value of the retrieved credential
     * @throws RuntimeException If an error occurs during credential retrieval
     */
    public static String getCredential(String credentialName, boolean useAwsSecrets) {
        // Check if useAwsSecrets is true and throw an exception if it is (not implemented)
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