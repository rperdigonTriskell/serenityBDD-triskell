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
                    echo 'Clonando el repositorio...'
                    git url: 'https://github.com/rperdigonTriskell/serenityBDD-triskell.git', credentialsId: 'gitCredentials', branch: 'waitImplementation'
                }
            }
        }

        stage('Install dependencies') {
            steps {
                echo 'Instalando dependencias de Maven...'
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Build and execute tests') {
            steps {
                withCredentials([file(credentialsId: 'serenityConfigFile', variable: 'CREDENTIALS_FILE')]) {
                    echo 'Ejecutando pruebas de Serenity...'
                    sh '''
                        echo "Using credentials file at: $CREDENTIALS_FILE"
                        echo "Contents of CREDENTIALS_FILE:"
                        cat $CREDENTIALS_FILE
                    '''
                    sh 'mvn clean verify -DcredentialsFile=$CREDENTIALS_FILE'
                }
            }
        }
    }

    post {
        always {
            echo 'Publicando el reporte de Serenity y resultados de pruebas...'
            publishHTML(target: [
                reportName: 'Serenity Report',
                reportDir: 'target/site/serenity',
                reportFiles: 'index.html',
                keepAll: true,
                alwaysLinkToLastBuild: true
            ])
            junit '**/target/surefire-reports/*.xml'
        }
        success {
            echo 'Pipeline ejecutado correctamente'
        }
        failure {
            echo 'La ejecución del pipeline falló'
        }
    }
}