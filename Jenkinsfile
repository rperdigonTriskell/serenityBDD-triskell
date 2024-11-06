pipeline {
    agent any

    environment {
        ENVIRONMENT = '@PROD'
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
                bat 'mvn clean install -DskipTests'
            }
        }

        stage('Prepare environment') {
            steps {
                script {
                    echo 'Copying environments.properties to the workspace...'
                    bat 'copy "src\\test\\resources\\environments.properties" .'
                }
            }
        }

        stage('Build and execute tests') {
            steps {
                withCredentials([file(credentialsId: 'serenityConfigFile', variable: 'CREDENTIALS_FILE')]) {
                    echo 'Ejecutando pruebas de Serenity...'
                    bat """
                        echo Using credentials file at: %CREDENTIALS_FILE%
                        type %CREDENTIALS_FILE%
                    """
                    bat "mvn clean verify -DcredentialsFile=%CREDENTIALS_FILE%"
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
            junit 'target/surefire-reports/*.xml'
        }
        success {
            echo 'Pipeline ejecutado correctamente'
        }
        failure {
            echo 'La ejecución del pipeline falló'
        }
    }
}