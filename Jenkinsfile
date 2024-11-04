pipeline {
    agent any
    environment {
        CREDENTIALS = credentials('serenityCredentials')
    }
    tools {
        maven 'Maven 3.8.1'
    }
    stages {
        stage('Clone repository') {
            steps {
                git url: 'https://github.com/rperdigonTriskell/serenityBDD-triskell.git', credentialsId: 'gitCredentials', branch: 'waitImplementation'
            }
        }
        stage('Setup credentials') {
            steps {
                withCredentials([string(credentialsId: 'serenityCredentials', variable: 'CREDENCIALES')]) {
                    writeFile file: 'serenityCredentials.properties', text: "${CREDENCIALES}"
                }
            }
        }
        stage('Build and execute tests') {
            steps {
                withMaven(maven: 'Maven 3.8.1') {
                    sh 'mvn clean verify -DcredentialsFile=serenityCredentials.properties'
                }
            }
        }
    }
    post {
        always {
            publishHTML(target: [
                reportName: 'Serenity Report',
                reportDir: 'target/site/serenity',
                reportFiles: 'index.html',
                keepAll: true,
                alwaysLinkToLastBuild: true
            ])
            junit '**/target/surefire-reports/*.xml'
            sh 'rm -f serenityCredentials.properties'
        }
    }
}
