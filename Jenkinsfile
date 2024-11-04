pipeline {
    agent any
    tools {
        maven 'Maven 3.8.1'
    }
    stages {
        stage('Clone repository') {
            steps {
                script {
                    // Clonar el repositorio
                    git url: 'https://github.com/rperdigonTriskell/serenityBDD-triskell.git', credentialsId: 'gitCredentials', branch: 'waitImplementation'
                }
            }
        }
        stage('Build and execute tests') {
            steps {
                withCredentials([file(credentialsId: 'serenityCredentials', variable: 'CREDENTIALS_FILE')]) {
                    // Usar el archivo de credenciales de manera segura
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
            // Publicar informe de Serenity
            publishHTML(target: [
                reportName: 'Serenity Report',
                reportDir: 'target/site/serenity',
                reportFiles: 'index.html',
                keepAll: true,
                alwaysLinkToLastBuild: true
            ])
            // Publicar resultados de pruebas
            junit '**/target/surefire-reports/*.xml'
        }
    }
}