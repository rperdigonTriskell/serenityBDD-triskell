pipeline {
    agent any
    environment {
        MAVEN_HOME = tool name: 'Maven 3.9.6', type: 'maven'
        JAVA_HOME = tool name: 'jdk-23', type: 'jdk'
        CREDENTIALS_FILE = credentials('CREDENTIALS_FILE')
        serenityEnvironmentFile = 'src/test/resources/environment.properties'
        REPORT_ZIP = 'serenity-report.zip'
    }
    stages {
        stage('Determine Environment') {
            steps {
                script {
                    // Detectar el entorno basado en el nombre del job
                    if (env.JOB_NAME.contains('AWS')) {
                        env.ACTUAL_ENVIRONMENT = 'AWS'
                        echo "Detected AWS environment based on job name: ${env.JOB_NAME}"
                    } else if (env.JOB_NAME.contains('PROD')) {
                        env.ACTUAL_ENVIRONMENT = 'PROD'
                        echo "Detected PROD environment based on job name: ${env.JOB_NAME}"
                    } else {
                        error "Unable to determine environment. Please ensure job name includes 'AWS' or 'PROD'."
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
        stage('Build') {
            steps {
                script {
                    def mvnCommand = "${MAVEN_HOME}/bin/mvn clean verify " +
                        "-Dserenity.properties=${env.serenityEnvironmentFile} " +
                        "-Dserenity.credentials.file=${CREDENTIALS_FILE} " +
                        "-Dwebdriver.driver=${params.DRIVER} " +
                        "-Denvironment=${env.ACTUAL_ENVIRONMENT} " +
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

                // Asegurar que siempre se genere el ZIP
                sh """
                    mkdir -p target/site/serenity
                    if [ -d "target/site/serenity" ]; then
                        zip -rq target/${env.REPORT_ZIP} target/site/serenity/*
                    else
                        echo "No se encontraron archivos en target/site/serenity, pero se generará un ZIP vacío."
                        zip -rq target/${env.REPORT_ZIP}
                    fi
                """

                // Archivar el archivo ZIP generado
                archiveArtifacts artifacts: "target/${env.REPORT_ZIP}", allowEmptyArchive: true

                // Configurar el path del reporte y los destinatarios
                def indexPath = "${env.WORKSPACE}/target/site/serenity/index.html"
                def distributionList = 'rperdigon@triskellsoftware.com,jmprieto@triskellsoftware.com,jburcio@triskellsoftware.com,agarcia@triskellsoftware.com'

                // Enviar correo con el ZIP siempre, incluso si no hay reporte generado
                emailext(
                    subject: "Serenity BDD Pipeline Execution: ${buildResult} [${env.ACTUAL_ENVIRONMENT}]",
                    body: """
                        Hello,

                        The Serenity BDD pipeline execution has completed with status: ${buildResult}.

                        Execution details:
                        - Driver: ${params.DRIVER}
                        - Environment: ${env.ACTUAL_ENVIRONMENT}
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
            }
        }
    }
}