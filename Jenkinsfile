pipeline {
    agent any
    environment {
        MAVEN_HOME = tool name: 'Maven 3.9.6', type: 'maven'
        JAVA_HOME = tool name: 'jdk-23', type: 'jdk'
        CREDENTIALS_FILE = credentials('CREDENTIALS_FILE')
        serenityEnvironmentFile = 'src/test/resources/environment.properties'
        REPORT_ZIP = 'serenity-report.zip'
        LOGO_PATH = 'src/test/resources/images/triskell.png'
        PARTNER_LOGO_PATH = 'src/test/resources/images/partner.png'
        DISTRIBUTION_LIST = 'rperdigon@triskellsoftware.com,jmprieto@triskellsoftware.com,jburcio@triskellsoftware.com,agarcia@triskellsoftware.com'
    }
    stages {
        stage('Determine Environment') {
            steps {
                script {
                    if (env.JOB_NAME.contains('AWS')) {
                        env.ACTUAL_ENVIRONMENT = 'AWS'
                    } else if (env.JOB_NAME.contains('PROD')) {
                        env.ACTUAL_ENVIRONMENT = 'PROD'
                    } else {
                        error "Unable to determine environment. Please ensure job name includes 'AWS' or 'PROD'."
                    }
                    echo "Detected environment: ${env.ACTUAL_ENVIRONMENT}"
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
                        "-Dwebdriver.driver=chrome " +
                        "-Denvironment=${env.ACTUAL_ENVIRONMENT} " +
                        "-Dtags=@${env.ACTUAL_ENVIRONMENT}"

                    echo "Executing Maven command: ${mvnCommand}"
                    sh mvnCommand
                }
            }
        }
    }
    post {
        always {
            script {
                // Determine build result
                def buildResult = currentBuild.result ?: 'SUCCESS'
                def statusColor = (buildResult == 'SUCCESS') ? 'green' : 'red'

                // Generate report zip
                def reportPath = "target/site/serenity"
                if (fileExists(reportPath)) {
                    sh "zip -rq target/${env.REPORT_ZIP} ${reportPath}/*"
                } else {
                    echo "No files found at ${reportPath}. Creating an empty ZIP file."
                    sh "zip -rq target/${env.REPORT_ZIP}"
                }

                // Archive artifacts
                archiveArtifacts artifacts: "target/${env.REPORT_ZIP}", allowEmptyArchive: true

                // Define email body
                def emailBody = """
                <html>
                <head>
                    <style>
                        body { font-family: Arial, sans-serif; line-height: 1.6; }
                        .status { color: ${statusColor}; font-size: 18px; font-weight: bold; }
                        .content { border: 1px solid #ddd; padding: 20px; border-radius: 8px; background-color: #f9f9f9; }
                        .footer { margin-top: 20px; text-align: center; font-size: 12px; color: #555; }
                        .footer img { width: 120px; margin-right: 10px; }
                        .footer a { color: #007bff; text-decoration: none; }
                    </style>
                </head>
                <body>
                    <div class="content">
                        <p class="status">The Serenity BDD pipeline execution has completed with status: ${buildResult}.</p>
                        <p><strong>Environment:</strong> ${env.ACTUAL_ENVIRONMENT}</p>
                        <p>You can view the test report here:</p>
                        <p>{Download Folder}/target/site/serenity/index.html<p>
                        <p>The full report is attached as a ZIP file.</p>
                    </div>
                    <div class="footer">
                        <p>Testing Message | Network and System Administrator (NSA)</p>
                        <div>
                            <img src="cid:triskell-logo" alt="Triskell Logo" />
                            <img src="cid:partner-logo" alt="Partner Logo" />
                        </div>
                        <p>
                            For further information, contact us at <a href="mailto:rgpd@triskellsoftware.com">rgpd@triskellsoftware.com</a>.
                        </p>
                    </div>
                </body>
                </html>
                """

                // Send email
                emailext(
                    subject: "Serenity BDD Pipeline Execution: ${buildResult} [${env.ACTUAL_ENVIRONMENT}]",
                    body: emailBody,
                    mimeType: 'text/html',
                    to: env.DISTRIBUTION_LIST,
                    attachmentsPattern: "target/${env.REPORT_ZIP}",
                    attachLog: true
                )
            }
        }
    }
}
