package starter.tasks.security;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CredentialManager {

    private static final Properties properties = new Properties();
    private static final Properties environmentProperties = new Properties();

    static {
        try {
            String credentialsFilePath;

            // Verificar si estamos en Jenkins
            if (System.getenv("CREDENTIALS_FILE") != null && !System.getenv("CREDENTIALS_FILE").isEmpty()) {
                // Si estamos en Jenkins, cargamos el archivo especificado por la variable de entorno
                credentialsFilePath = System.getenv("CREDENTIALS_FILE");
            } else {
                // Si no estamos en Jenkins, usamos el archivo local
                credentialsFilePath = "C:\\Users\\rperdigon\\IdeaProjects\\serenityBDD-triskell\\src\\test\\resources\\config.properties";
            }

            // Cargar el archivo de credenciales
            System.out.println("DEBUG: Loading credentials file from: " + credentialsFilePath);
            properties.load(new FileInputStream(credentialsFilePath));

            // Cargar el archivo environments.properties
            String environmentFilePath = "src/test/resources/environments.properties";
            System.out.println("DEBUG: Loading environments file from: " + environmentFilePath);
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