pipeline {
    agent any
    environment {
        MAVEN_HOME = tool name: 'Maven 3.9.6', type: 'maven' // Matches the Maven tool name in Jenkins
        JAVA_HOME = tool name: 'jdk-22', type: 'jdk' // Matches the JDK tool name in Jenkins
        CREDENTIALS_FILE = credentials('CREDENTIALS_FILE') // Usar el nuevo ID de las credenciales
        serenityEnvironmentFile = 'src/test/resources/environment.properties' // Ruta del archivo de entorno
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'waitImplementation',
                    url: 'https://github.com/rperdigonTriskell/serenityBDD-triskell.git',
                    credentialsId: 'gitCredentials'
            }
        }
        stage('Check Environment Variables') {
            steps {
                script {
                    // Verifica si la variable de entorno CREDENTIALS_FILE está configurada correctamente
                    echo "CREDENTIALS_FILE: ${env.CREDENTIALS_FILE}"
                    if (!env.CREDENTIALS_FILE) {
                        error "CREDENTIALS_FILE variable is not set. Exiting the build."
                    }
                }
            }
        }
        stage('Build') {
            steps {
                // Usamos la variable CREDENTIALS_FILE y serenityEnvironmentFile para pasar las rutas de archivos necesarias
                bat "${MAVEN_HOME}\\bin\\mvn clean verify -Dserenity.properties=${env.serenityEnvironmentFile} -Dserenity.credentials.file=${CREDENTIALS_FILE}"
            }
        }
        stage('Publish Reports') {
            steps {
                // Archive Serenity reports (assuming they are in the target directory)
                archiveArtifacts artifacts: 'target\\site\\serenity\\*.html', allowEmptyArchive: true
                // Publish Serenity HTML report if needed
                publishHTML(target: [
                    reportDir: 'target\\site\\serenity',
                    reportFiles: 'index.html',
                    reportName: 'Serenity BDD Report'
                ])
            }
        }
    }
}