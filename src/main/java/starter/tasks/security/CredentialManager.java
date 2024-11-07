package starter.tasks.security;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Properties;

public class CredentialManager {

    private static final Properties properties = new Properties();
    private static final boolean isJenkins = System.getenv("JENKINS_HOME") != null;

    static {
        try {
            if (isJenkins) {
                // Jenkins environment
                String jenkinsCredentialsPath = System.getenv("serenityConfigFile");

                if (jenkinsCredentialsPath != null) {
                    // Si las credenciales están en base64 (como las almacena Jenkins), las decodificamos
                    byte[] decodedBytes = Base64.getDecoder().decode(jenkinsCredentialsPath);
                    String decodedConfig = new String(decodedBytes);

                    // Guardamos el archivo en el workspace de Jenkins
                    String configFilePath = "config.properties"; // O cualquier otra ruta en el workspace de Jenkins
                    try (FileOutputStream fos = new FileOutputStream(configFilePath)) {
                        fos.write(decodedConfig.getBytes());
                    }

                    properties.load(new FileInputStream(configFilePath));
                } else {
                    throw new RuntimeException("No serenityConfigFile environment variable set in Jenkins");
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
