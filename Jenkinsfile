pipeline {
    agent any
    environment {
        CREDENTIALS = credentials('serenityCredentials')
    }
    stages {
        stage('Clone repository') {
            steps {
                git url: 'https://github.com/rperdigonTriskell/serenityBDD-triskell.git', credentialsId: 'gitCredentials'
            }
        }
        stage('Build and execute tests') {
            steps {
                script {
                    docker.image('dosel/zalenium').inside {
                        echo "Initializing tests for Serenity BDD:"
                        mvn clean verify -Ddriver=chrome
                    }
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
