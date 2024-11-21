pipeline {
    agent any
    environment {
        MAVEN_HOME = tool name: 'Maven 3.9.6', type: 'maven'
        JAVA_HOME = tool name: 'jdk-23', type: 'jdk'
        CREDENTIALS_FILE = credentials('CREDENTIALS_FILE')
        serenityEnvironmentFile = 'src/test/resources/environment.properties'
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
                    if (isUnix()) {
                        sh "${MAVEN_HOME}/bin/mvn clean verify -Dserenity.properties=${env.serenityEnvironmentFile} -Dserenity.credentials.file=${CREDENTIALS_FILE}"
                    } else {
                        bat "${MAVEN_HOME}\\bin\\mvn clean verify -Dserenity.properties=${env.serenityEnvironmentFile} -Dserenity.credentials.file=${CREDENTIALS_FILE}"
                    }
                }
            }
        }
        stage('Publish Reports') {
            steps {
                script {
                    def reportDir = isUnix() ? 'target/site/serenity' : 'target\\site\\serenity'
                    def reportFiles = 'index.html'

                    archiveArtifacts artifacts: "${reportDir}/**/*", allowEmptyArchive: true
                    publishHTML(target: [
                        reportDir: reportDir,
                        reportFiles: reportFiles,
                        reportName: 'Serenity BDD Report'
                    ])
                }
            }
        }
    }
    post {
        success {
            emailext(
                to: 'rperdigon@triskellsoftware.com, jmprieto@triskellsoftware.com, jburcio@triskellsoftware.com, agarcia@triskellsoftware.com',
                subject: "Serenity BDD Test Report - ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """
                        Hello,

                        The Serenity BDD test report for ${env.JOB_NAME} #${env.BUILD_NUMBER} has been generated successfully.

                        You can view the report here:
                        ${env.BUILD_URL}target/site/serenity/index.html

                        Best regards,
                        Triskell
                        """,
                attachmentsPattern: '**/target/site/serenity/**/*'
            )
        }
    }
}