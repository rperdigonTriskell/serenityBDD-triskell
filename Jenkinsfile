pipeline {
    agent any
    tools {
        maven 'Maven 3.9.6'
    }
    stages {
        stage('Clone repository') {
            steps {
                git url: 'https://github.com/rperdigonTriskell/serenityBDD-triskell.git', credentialsId: 'gitCredentials', branch: 'waitImplementation'
            }
        }
        stage('Build and execute tests') {
            steps {
                withCredentials([file(credentialsId: 'serenityCredentials', variable: 'CREDENTIALS_FILE')]) {
                    sh 'echo "Using credentials file at: $CREDENTIALS_FILE"' // Verifica la ruta del archivo
                    sh 'mvn clean verify -DcredentialsFile=$CREDENTIALS_FILE -X' // Añade -X para más información de depuración
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
