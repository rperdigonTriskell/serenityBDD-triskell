package starter.tasks.security;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CredentialManager {

    private static final Properties properties = new Properties();

    static {
        try {
            // Cargar el archivo properties
            properties.load(new FileInputStream("src/test/resources/config.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Error cargando el archivo de credenciales", e);
        }
    }

    /**
     * Obtiene la credencial especificada.
     *
     * @param credentialName Nombre de la credencial a obtener
     * @param useAwsSecrets Indica si se debe utilizar AWS Secrets Manager para obtener la credencial (no se usa en este caso)
     * @return El valor de la credencial obtenida
     * @throws RuntimeException Si ocurre algún error durante la obtención de la credencial
     */
    public static String getCredential(String credentialName, boolean useAwsSecrets) {
        // Validar si useAwsSecrets es verdadero y lanzar una excepción si lo es (no implementado)
        if (useAwsSecrets) {
            throw new UnsupportedOperationException("Obtención de credenciales de AWS no implementada aún");
        }

        // Obtener la credencial del archivo properties
        String credentialValue = properties.getProperty(credentialName);
        if (credentialValue == null) {
            throw new RuntimeException("Credencial no encontrada en properties: " + credentialName);
        }
        return credentialValue;
    }
}
