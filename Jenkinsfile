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

                // Directorio de los videos generados por Zalenium (ajusta esta ruta si es necesario)
                def videoDirectory = "/tmp/videos"
                def targetVideoDirectory = "target/videos"

                // Crear directorio en el workspace para guardar videos
                sh "mkdir -p ${targetVideoDirectory}"

                // Verifica si hay videos en el directorio y cópialos al directorio target/videos
                if (fileExists(videoDirectory)) {
                    def videoFiles = sh(script: "ls ${videoDirectory}", returnStdout: true).trim()
                    if (videoFiles) {
                        echo "Copiando videos desde ${videoDirectory} a ${targetVideoDirectory}"
                        sh "cp ${videoDirectory}/*.mp4 ${targetVideoDirectory}/"
                    } else {
                        echo "No se encontraron videos en ${videoDirectory}."
                    }
                } else {
                    echo "El directorio de videos (${videoDirectory}) no existe."
                }

                // Generar reporte ZIP
                def reportPath = "target/site/serenity"
                if (fileExists(reportPath)) {
                    sh "zip -rq target/${env.REPORT_ZIP} ${reportPath}/*"
                } else {
                    echo "No files found at ${reportPath}. Creating an empty ZIP file."
                    sh "zip -rq target/${env.REPORT_ZIP}"
                }

                // Archivar artefactos: Reporte y videos
                archiveArtifacts artifacts: "target/${env.REPORT_ZIP}, ${targetVideoDirectory}/*.mp4", allowEmptyArchive: true

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
                        <p>Additionally, videos of test executions are available:</p>
                        <ul>
                            ${sh(script: "ls ${targetVideoDirectory}/*.mp4 | awk '{print \"<li>\" \$1 \"</li>\"}'", returnStdout: true).trim()}
                        </ul>
                    </div>
                    <div class="footer">
                        <p>Testing Message | Network and System Administrator (NSA)</p>
                    </div>
                </body>
                </html>
                """

                // Send email
                emailext body: emailBody,
                         subject: "Pipeline execution result: ${buildResult}",
                         to: "${env.EMAIL_RECIPIENT}",
                         attachLog: true,
                         attachmentsPattern: "target/${env.REPORT_ZIP}, ${targetVideoDirectory}/*.mp4"
            }
        }
    }
}
