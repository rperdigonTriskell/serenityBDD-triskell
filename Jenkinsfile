pipeline {
    agent any
    environment {
        MAVEN_HOME = tool name: 'Maven 3.9.6', type: 'maven'
        JAVA_HOME = tool name: 'jdk-23', type: 'jdk'
        CREDENTIALS_FILE = credentials('CREDENTIALS_FILE')
        serenityEnvironmentFile = 'src/test/resources/environment.properties'
        REPORT_ZIP = 'serenity-report.zip'
    }
    parameters {
        string(name: 'DRIVER', defaultValue: 'chrome', description: 'Driver del navegador (chrome, firefox, etc.)')
        choice(name: 'ENVIRONMENT', choices: ['PROD', 'AWS'], description: 'Entorno de ejecución')
        choice(name: 'TAGS', choices: ['@PROD', '@AWS', '@Dashboard'], description: 'Tag de las pruebas a ejecutar')
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'waitImplementation',
                    url: 'https://github.com/rperdigonTriskell/serenityBDD-triskell.git',
                    credentialsId: 'gitCredentials'
            }
        }
        stage('Check Environment Variables') {
            steps {
                script {
                    echo "CREDENTIALS_FILE: ${env.CREDENTIALS_FILE}"
                    if (!env.CREDENTIALS_FILE) {
                        error "CREDENTIALS_FILE variable is not set. Exiting the build."
                    }
                }
            }
        }
        stage('Build') {
            steps {
                script {
                    def mvnCommand = "${MAVEN_HOME}/bin/mvn clean verify " +
                                     "-Dserenity.properties=${env.serenityEnvironmentFile} " +
                                     "-Dserenity.credentials.file=${CREDENTIALS_FILE} " +
                                     "-Ddriver=${params.DRIVER} " +
                                     "-Denvironment=${params.ENVIRONMENT} " +
                                     "-Dtags=${params.TAGS}" // Agrega el parámetro TAGS

                    echo "Ejecutando comando Maven: ${mvnCommand}"
                    sh mvnCommand
                }
            }
        }
    }
    post {
        always {
            script {
                echo "Archiving report and sending email regardless of build result."
                try {
                    sh "cd target && zip -rq ${env.REPORT_ZIP} site/serenity/* || echo 'Skipping ZIP creation due to error.'"
                } catch (Exception e) {
                    echo "Failed to create ZIP file: ${e.message}"
                }
                def reportExists = fileExists("target/${env.REPORT_ZIP}")
                if (!reportExists) {
                    echo "Warning: The report ZIP file does not exist. Skipping attachment."
                }
                def indexPath = "${env.WORKSPACE}/target/site/serenity/index.html"
                def status = currentBuild.result ?: 'SUCCESS'
                def distributionList = 'rperdigon@triskellsoftware.com,jmprieto@triskellsoftware.com,jburcio@triskellsoftware.com,agarcia@triskellsoftware.com'

                emailext(
                    subject: "Serenity BDD Pipeline Execution: ${status}",
                    body: """
                        Hello,

                        The Serenity BDD pipeline execution has completed with status: ${status}.

                        You can find the test report here:
                        ${indexPath}

                        ${reportExists ? 'The full report is also attached as a ZIP file.' : 'Unfortunately, the report ZIP file could not be created.'}

                        Regards,
                        Triskell
                    """,
                    to: distributionList,
                    attachmentsPattern: reportExists ? "target/${env.REPORT_ZIP}" : ''
                )
            }
        }
    }
}