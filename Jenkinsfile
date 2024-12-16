pipeline {
    agent any
    environment {
        MAVEN_HOME = tool(name: 'Maven 3.9.6', type: 'maven')
        JAVA_HOME = tool(name: 'jdk-23', type: 'jdk')
        CREDENTIALS_FILE = credentials('CREDENTIALS_FILE')
        SERENITY_ENV_FILE = 'src/test/resources/environment.properties'
        REPORT_ZIP = 'serenity-report.zip'
        LOGO_PATH = 'src/test/resources/images/triskell.png'
        PARTNER_LOGO_PATH = 'src/test/resources/images/partner.png'
    }
    stages {
        stage('Validate Tools') {
            steps {
                script {
                    if (!MAVEN_HOME || !JAVA_HOME) {
                        error "Maven or JDK tool configuration is missing. Verify Jenkins global tool settings."
                    }
                }
            }
        }
        stage('Determine Environment') {
            steps {
                script {
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
                    def mvnCommand = """
                        ${MAVEN_HOME}/bin/mvn clean verify \
                        -Dserenity.properties=${SERENITY_ENV_FILE} \
                        -Dserenity.credentials.file=${CREDENTIALS_FILE} \
                        -Dwebdriver.driver=chrome \
                        -Denvironment=${env.ACTUAL_ENVIRONMENT} \
                        -Dtags=@PROD
                    """
                    echo "Executing Maven command: ${mvnCommand}"
                    sh mvnCommand
                }
            }
        }
    }
    post {
        always {
            script {
                def buildResult = currentBuild.result ?: 'SUCCESS'
                def statusColor = (buildResult == 'SUCCESS') ? 'green' : 'red'

                // Compress Serenity reports
                sh '''
                    mkdir -p target/site/serenity
                    if [ -d "target/site/serenity" ]; then
                        zip -rq target/${REPORT_ZIP} target/site/serenity/*
                    else
                        echo "No files found in target/site/serenity. Creating an empty ZIP file."
                        zip -rq target/${REPORT_ZIP}
                    fi
                '''

                // Archive artifacts
                archiveArtifacts artifacts: "target/${REPORT_ZIP}", allowEmptyArchive: true

                def indexPath = "target/site/serenity/index.html"
                def distributionList = 'rperdigon@triskellsoftware.com,jmprieto@triskellsoftware.com,jburcio@triskellsoftware.com,agarcia@triskellsoftware.com'

                // Email content
                def emailBody = """
                <html>
                <head>
                    <style>
                        body { font-family: Arial, sans-serif; line-height: 1.6; }
                        .status { color: ${statusColor}; font-size: 18px; font-weight: bold; }
                        .content { padding: 20px; background-color: #f9f9f9; }
                        .footer { font-size: 12px; color: #555; border-top: 1px solid #ddd; padding-top: 10px; }
                        .footer img { width: 120px; margin-right: 10px; }
                    </style>
                </head>
                <body>
                    <p class="status">Serenity BDD pipeline execution completed with status: ${buildResult}.</p>
                    <p><strong>Details:</strong></p>
                    <ul>
                        <li><strong>Driver:</strong> Chrome</li>
                        <li><strong>Environment:</strong> ${env.ACTUAL_ENVIRONMENT}</li>
                        <li><strong>Tags:</strong> @PROD</li>
                    </ul>
                    <p>View the test report: <a href="${indexPath}" style="color: #007bff;">${indexPath}</a></p>
                    <p>The full report is attached as a ZIP file.</p>
                    <div class="footer">
                        <img src="cid:triskell-logo" alt="Triskell Logo" />
                        <img src="cid:partner-logo" alt="Partner Logo" />
                        <p>QA Automation Testing Team | <a href="http://triskellsoftware.com">triskellsoftware.com</a></p>
                    </div>
                </body>
                </html>
                """

                emailext(
                    subject: "Serenity BDD Pipeline Execution: ${buildResult} [${env.ACTUAL_ENVIRONMENT}]",
                    body: emailBody,
                    mimeType: 'text/html',
                    to: distributionList,
                    attachmentsPattern: "target/${REPORT_ZIP}",
                    attachLog: true
                )
            }
        }
    }
}