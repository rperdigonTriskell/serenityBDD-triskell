pipeline {
    agent any
    environment {
        MAVEN_HOME = tool name: 'Maven 3.9.6', type: 'maven' // Matches the Maven tool name in Jenkins
        JAVA_HOME = tool name: 'jdk-22', type: 'jdk' // Matches the JDK tool name in Jenkins
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'waitImplementation',
                    url: 'https://github.com/rperdigonTriskell/serenityBDD-triskell.git',
                    credentialsId: 'gitCredentials'
            }
        }
        stage('Prepare Credentials') {
            steps {
                script {
                    // Save the credentials as a file in the workspace if in Jenkins
                    def credentialsContent = "${CREDENTIALS_FILE}"
                    def configFilePath = "${WORKSPACE}/config.properties"
                    writeFile file: configFilePath, text: credentialsContent
                    echo "Config file saved to: ${configFilePath}"
                }
            }
        }
        stage('Build') {
            steps {
                // Pass the path of the config.properties file to Maven
                bat "${MAVEN_HOME}\\bin\\mvn clean verify -Dserenity.properties=src/test/resources/environment.properties -Dserenity.credentials.file=${WORKSPACE}\\config.properties"
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
    post {
        always {
            // Clean up workspace after the build
            cleanWs()
        }
    }
}
