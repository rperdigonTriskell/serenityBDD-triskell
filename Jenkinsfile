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
                    sh "${MAVEN_HOME}/bin/mvn clean verify -Dserenity.properties=${env.serenityEnvironmentFile} -Dserenity.credentials.file=${CREDENTIALS_FILE}"
                }
            }
        }
        stage('Archive Report') {
            steps {
                script {
                    sh "ls -la target"
                    sh "ls -la target/site/serenity"
                    sh "cd target && zip -r ${env.REPORT_ZIP} site/serenity/* || echo 'Skipping ZIP creation due to error.'"
                }
                archiveArtifacts artifacts: "target/${env.REPORT_ZIP}", allowEmptyArchive: true
            }
        }
    }
    post {
        always {
            script {
                def indexPath = "${env.WORKSPACE}/target/site/serenity/index.html"
                def status = currentBuild.result ?: 'SUCCESS'
                def distributionList = 'rperdigon@triskellsoftware.com,jmprieto@triskellsoftware.com,jburcio@triskellsoftware.com,agarcia@triskellsoftware.com'

                def reportExists = fileExists("target/${env.REPORT_ZIP}")
                if (!reportExists) {
                    echo "Warning: The report ZIP file does not exist. Skipping attachment."
                }

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