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

                // Ruta de los videos generados por Zalenium
                def videoDir = "/tmp/videos"  // Directorio donde Zalenium guarda los videos
                def videoFiles = findFiles(glob: "${videoDir}/video-*.mp4")

                if (videoFiles) {
                    // Seleccionar el primer video (puedes agregar lógica para elegir el correcto si es necesario)
                    def selectedVideo = videoFiles[0].path
                    echo "Video encontrado: ${selectedVideo}"
                    // Copiar el video a la ubicación de trabajo de Jenkins
                    sh "cp ${selectedVideo} target/test_video.mp4"
                } else {
                    echo "No se encontraron videos."
                }

                // Generar el reporte zip
                def reportPath = "target/site/serenity"
                if (fileExists(reportPath)) {
                    sh "zip -rq target/${env.REPORT_ZIP} ${reportPath}/*"
                } else {
                    echo "No se encontraron archivos en ${reportPath}. Creando un ZIP vacío."
                    sh "zip -rq target/${env.REPORT_ZIP}"
                }

                // Archivar los artefactos
                archiveArtifacts artifacts: "target/${env.REPORT_ZIP}, target/test_video.mp4", allowEmptyArchive: true

                // Definir el cuerpo del correo electrónico con el video adjunto
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
                        <p>You can also view the video of the tests here:</p>
                        <p><a href="file://${pwd()}/target/test_video.mp4">Download Video</a></p>
                    </div>
                    <div class="footer">
                        <p>Testing Message | Network and System Administrator (NSA)</p>
                        <div>
                            <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAABECAYAAADEKno9AAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAALiMAAC4jAXilP3YAAAzYSURBVHhe7Z0JjCVFHYdZ1wNB5FDxAoUFVESiiAcRyCIuCEqAoIBCXDUcq4jRiCsLAZVDLvEiGBTx1qiA4BVul1khIiroIggEWI+oCOpyLHLIsX7f6+qXnp5+/eod0/Nmpr7kl6ruqe5X3f3/d909c1avXr1GIpGo5kkhTCQSFSQHSSRqSA6SSNSQHCSRqCE5SCJRQ3KQRKKG5CCJRA3JQRKJGpKDJBI1JAdJJGpIDpJI1NDzXKw5c+aEWPOsOmpDHXpvtBDtgJ6FHkC/QKesc/LdVxEmEl2Jtftp4yA4hw7xJbRVa8dEvJDFOMlnss1EojMzxkFwDH/w40ExVcLdcJJLQzyRqGRGOAjOMZfga8gqVSw3o61wkt4uLDGriLX7kW2kh5Lj66gX55At0WuzaCIxGCNbguAgnyD4ZLbVM8dTgnh8T8yfP/+FBIuzrYF4aNmyZUeFeN9wDzYgsGpZ5BKu7ZIQn/XwzLw/3qec27n3Z4Z4C9L4TH22ObePjY2NS9OJkXQQDGNXAo2g3x+7ECPaJ8Sj4Ua+iuB32dZA3MdDWi/E+4b7sAnBn7KtNsdxbf2+OGYcPLM/E7w422rBrV+2U4i3IM3vCV6ZbbVYhoOMS9OJkatiYRQallWrQTyxeMN6wS7jZR10BypzLapKezUaBg+j8rk1iERDjFwJgoOcRfC+bKtvlvOWtTQYGryFPkzwuWyrzaa8rZLBTiGTXYKMlIPgHC8n+AMatGS7EQfZOsSHwrAdhPOtTfCUbKuNbZdHQtw03odnZlttHiaNJUtXON5j6+7lo5zrvyHehuOeQfDkbKvF46RbFeLjIO3TCJ6ebbW5n/RPhHgUnKdYJe3lGmdVFStmrONudBp6E9oMvQS9E/0S5ZhmpOAhrYuOQFejB9llde6ekt6PirwIldMsQZVw3o3QCeh69Ci77kPl44v6LqriZ6iYrm6GgnkuplXmuxbytwU6Hd2EHmdX8fiH2LcSXYQWoqKzNsrIOAilxzyC/bKtSh5C9gxtQulwJFqKVqDb0PfZ70h73uNzZwhHAh7wbgS3o9PR9qj8xh0YfsNwEjY0uAp1CZ6CxHfoAPm5PHO72X0BqkUoKZ6oofzDdlfdf29PEAK7pEKX+eGmXfNc3Jgzq06g0QZ70wqO+LCNCwM2rTnX9ywd3tctyt3Yp4wRu1h7NsZs5XXJz5uYwT33zfl6KFDtnmptb7dL3q9Ln13H9Fl7Ylgsn1O5+e/9kVsNGV0= />
                            <a href="https://www.triskellsoftware.com">Triskell Software</a>
                        </div>
                    </div>
                </body>
                </html>
                """

                // Enviar correo
                mail to: "${env.DISTRIBUTION_LIST}",
                    subject: "Serenity BDD Test Report - ${buildResult}",
                    body: emailBody,
                    attachLog: true,
                    attachmentsPattern: "target/${env.REPORT_ZIP}, target/test_video.mp4"
            }
        }
    }
}