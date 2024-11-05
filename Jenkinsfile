pipeline {
    agent any
    environment {
        ENVIRONMENT = '@PROD'  // Ajusta según el entorno que quieras usar
    }
    tools {
        maven 'Maven 3.9.6'
    }
    stages {
        stage('Clone repository') {
            steps {
                script {
                    // Clonar el repositorio desde GitHub
                    git url: 'https://github.com/rperdigonTriskell/serenityBDD-triskell.git', credentialsId: 'gitCredentials', branch: 'waitImplementation'
                }
            }
        }
        stage('Build and execute tests') {
            steps {
                withCredentials([file(credentialsId: 'serenityConfigFile', variable: 'CREDENTIALS_FILE')]) {
                    // Paso de depuración para verificar el contenido del archivo de credenciales
                    sh '''
                        echo "Using credentials file at: $CREDENTIALS_FILE"
                        echo "Contents of CREDENTIALS_FILE:"
                        cat $CREDENTIALS_FILE
                    '''

                    // Ejecutar pruebas de Maven
                    sh 'mvn clean verify -DcredentialsFile=$CREDENTIALS_FILE'
                }
            }
        }
    }
    post {
        always {
            // Publicar el informe de Serenity
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