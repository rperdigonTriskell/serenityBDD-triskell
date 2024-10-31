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
                git url: 'https://github.com/rperdigonTriskell/serenityBDD-triskell.git', credentialsId: 'gitCredentials'
            }
        }
        stage('Build and execute tests') {
            steps {
                withMaven(maven: 'Maven 3.8.1') { // Configura el entorno Maven
                    mvn 'clean verify -Ddriver=chrome -Dwebdriver.remote.url=http://localhost:4444/wd/hub'
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
        }
    }
}