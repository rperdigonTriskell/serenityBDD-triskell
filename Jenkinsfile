pipeline {
    agent any
    environment {
        MAVEN_HOME = tool name: 'Maven 3.9.6', type: 'maven' // Matches the Maven tool name in Jenkins
        JAVA_HOME = tool name: 'jdk-22', type: 'jdk' // Matches the JDK tool name in Jenkins
        CREDENTIALS_FILE = credentials('serenityConfigFile')
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'waitImplementation',
                    url: 'https://github.com/rperdigonTriskell/serenityBDD-triskell.git',
                    credentialsId: 'gitCredentials'
            }
        }
        stage('Build') {
            steps {
                // Use relative path for the environment properties file
                bat "${MAVEN_HOME}\\bin\\mvn clean verify -Dserenity.properties=src/test/resources/environment.properties -Dserenity.credentials.file=%CREDENTIALS_FILE%"
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