pipeline {
    agent any
    environment {
        ENVIRONMENT = '@PROD'
    }
    tools {
        maven 'Maven 3.8.1'
    }
    stages {
        stage('Clone repository') {
            steps {
                script {
                    git url: 'https://github.com/rperdigonTriskell/serenityBDD-triskell.git', credentialsId: 'gitCredentials', branch: 'waitImplementation'
                }
            }
        }
        stage('Build and execute tests') {
            steps {
                withCredentials([file(credentialsId: 'serenityConfigFile', variable: 'CREDENTIALS_FILE')]) {
                    sh '''
                        echo "Using credentials file at: $CREDENTIALS_FILE"
                        mvn clean verify -DcredentialsFile=$CREDENTIALS_FILE
                    '''
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