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
        stage('Set ENVIRONMENT to PROD on Sundays at 17:00') {
            steps {
                script {
                    def dayOfWeek = new Date().format('u')
                    def currentHour = new Date().format('H')

                    if (dayOfWeek == '7' && currentHour == '17') {
                        echo "Es domingo a las 17:00, estableciendo ENVIRONMENT a PROD."
                        params.ENVIRONMENT = 'PROD'
                    }
                }
            }
        }
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
                                     "-Dtags=${params.TAGS}"

                    echo "Ejecutando comando Maven: ${mvnCommand}"
                    sh mvnCommand
                }
            }
        }
    }
    post {
        always {
            script {
                echo "Archiving Serenity report and preparing email notification."

                def buildResult = currentBuild.result ?: 'SUCCESS'

                // Crear el ZIP del reporte si existe el directorio
                if (fileExists("target/site/serenity")) {
                    sh "cd target && zip -rq ${env.REPORT_ZIP} site/serenity/*"
                } else {
                    echo "El directorio de reportes 'target/site/serenity' no existe. No se generará el archivo ZIP."
                }

                // Configurar el path del reporte y los destinatarios
                def indexPath = "${env.WORKSPACE}/target/site/serenity/index.html"
                def distributionList = 'rperdigon@triskellsoftware.com,jmprieto@triskellsoftware.com,jburcio@triskellsoftware.com,agarcia@triskellsoftware.com'

                // Enviar correo con o sin adjunto
                if (fileExists("target/${env.REPORT_ZIP}")) {
                    emailext(
                        subject: "Serenity BDD Pipeline Execution: ${buildResult}",
                        body: """
                            Hello,

                            The Serenity BDD pipeline execution has completed with status: ${buildResult}.

                            Execution details:
                            - Driver: ${params.DRIVER}
                            - Environment: ${params.ENVIRONMENT}
                            - Tags: ${params.TAGS}

                            You can view the test report here:
                            ${indexPath}

                            The full report is attached as a ZIP file.

                            Regards,
                            Triskell
                        """,
                        to: distributionList,
                        attachmentsPattern: "target/${env.REPORT_ZIP}"
                    )
                } else {
                    emailext(
                        subject: "Serenity BDD Pipeline Execution: ${buildResult}",
                        body: """
                            Hello,

                            The Serenity BDD pipeline execution has completed with status: ${buildResult}.

                            Execution details:
                            - Driver: ${params.DRIVER}
                            - Environment: ${params.ENVIRONMENT}
                            - Tags: ${params.TAGS}

                            However, the Serenity report could not be found.

                            Regards,
                            Triskell
                        """,
                        to: distributionList
                    )
                }
            }
        }
    }
}