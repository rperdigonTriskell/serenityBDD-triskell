package starter.tasks.security;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CredentialManager {

    private static final Properties properties = new Properties();

    static {
        try {
            String credentialsFilePath = System.getenv("CREDENTIALS_FILE");

            if (credentialsFilePath == null || credentialsFilePath.isEmpty()) {
                throw new RuntimeException("The CREDENTIALS_FILE environment variable is not set.");
            }

            properties.load(new FileInputStream(credentialsFilePath));
        } catch (IOException e) {
            throw new RuntimeException("Error loading the credentials file", e);
        }
    }

    public static String getCredential(String credentialName, boolean useAwsSecrets) {
        if (useAwsSecrets) {
            throw new UnsupportedOperationException("AWS credential retrieval not yet implemented");
        }

        String credentialValue = properties.getProperty(credentialName);
        if (credentialValue == null) {
            throw new RuntimeException("Credential not found for key: " + credentialName);
        }
        return credentialValue;
    }
}
