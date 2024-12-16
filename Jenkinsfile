pipeline {
    agent any

    environment {
        MAVEN_HOME = tool(name: 'Maven 3.9.6', type: 'maven')
        JAVA_HOME = tool(name: 'jdk-23', type: 'jdk')
        CREDENTIALS_FILE = credentials('CREDENTIALS_FILE')
        serenityEnvironmentFile = 'src/test/resources/environment.properties'
        REPORT_ZIP = 'serenity-report.zip'
        LOGO_PATH = 'src/test/resources/images/triskell.png'
        PARTNER_LOGO_PATH = 'src/test/resources/images/partner.png'
    }

    stages {
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
                    def mvnCommand = "${MAVEN_HOME}/bin/mvn clean verify " +
                        "-Dserenity.properties=${env.serenityEnvironmentFile} " +
                        "-Dserenity.credentials.file=${CREDENTIALS_FILE} " +
                        "-Dwebdriver.driver=chrome " +
                        "-Denvironment=${env.ACTUAL_ENVIRONMENT} " +
                        "-Dtags=@PROD"

                    echo "Executing Maven command: ${mvnCommand}"
                    sh mvnCommand
                }
            }
        }
    }

    post {
        always {
            script {
                // Default result handling
                def buildResult = currentBuild.result ?: 'SUCCESS'
                def statusColor = (buildResult == 'SUCCESS') ? 'green' : 'red'

                // Prepare report for archiving
                sh '''
                    mkdir -p target/site/serenity
                    if [ -d "target/site/serenity" ]; then
                        zip -rq target/${REPORT_ZIP} target/site/serenity/*
                    else
                        echo "No files found in target/site/serenity. Generating empty ZIP."
                        zip -rq target/${REPORT_ZIP}
                    fi
                '''

                archiveArtifacts artifacts: "target/${REPORT_ZIP}", allowEmptyArchive: true

                def indexPath = "target/site/serenity/index.html"
                def distributionList = 'rperdigon@triskellsoftware.com,jmprieto@triskellsoftware.com,jburcio@triskellsoftware.com,agarcia@triskellsoftware.com'

                def emailBody = """
                <html>
                <head>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            line-height: 1.6;
                        }
                        .header {
                            text-align: center;
                            margin-bottom: 20px;
                        }
                        .status {
                            color: ${statusColor};
                            font-size: 18px;
                            font-weight: bold;
                        }
                        .content {
                            border: 1px solid #ddd;
                            padding: 20px;
                            border-radius: 8px;
                            background-color: #f9f9f9;
                        }
                        .footer {
                            margin-top: 20px;
                            text-align: center;
                            font-size: 12px;
                            color: #555;
                            border-top: 1px solid #ddd;
                            padding-top: 10px;
                        }
                        .footer img {
                            width: 120px;
                            margin-right: 10px;
                        }
                        .footer a {
                            color: #007bff;
                            text-decoration: none;
                        }
                    </style>
                </head>
                <body>
                    <div class="header">
                        <p class="status">Triskell Report</p>
                    </div>
                    <div class="content">
                        <p class="status">The Serenity BDD pipeline execution has completed with status: ${buildResult}.</p>
                        <p>Execution details:</p>
                        <ul>
                            <li><strong>Driver:</strong> Chrome</li>
                            <li><strong>Environment:</strong> ${env.ACTUAL_ENVIRONMENT}</li>
                            <li><strong>Tags:</strong> @PROD</li>
                        </ul>
                        <p>You can view the test report here:</p>
                        <a href="${indexPath}" style="color: #007bff;">${indexPath}</a>
                        <p>The full report is attached as a ZIP file.</p>
                    </div>
                    <div class="footer">
                        <p>QA Automation Testing Team</p>
                        <p>
                            <a href="mailto:rperdigon@triskellsoftware.com">rperdigon@triskellsoftware.com</a><br>
                            <a href="http://triskellsoftware.com" target="_blank">triskellsoftware.com</a>
                        </p>
                        <div>
                            <img src="cid:triskell-logo" alt="Triskell Logo" width="150" />
                            <img src="cid:partner-logo" alt="Partner Logo" />
                        </div>
                        <p>
                            Information on data protection: The personal data contained in this message are subject to the security measures of Art. 32 of Regulation (EU) 2016/679. For further information, please contact <a href="mailto:rgpd@triskellsoftware.com">rgpd@triskellsoftware.com</a>.
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
                    to: distributionList,
                    attachmentsPattern: "target/${REPORT_ZIP}",
                    attachLog: true
                )
            }
        }
    }
}