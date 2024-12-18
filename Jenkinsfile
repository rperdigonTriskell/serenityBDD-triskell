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
                // Determina el resultado de la construcción
                def buildResult = currentBuild.result ?: 'SUCCESS'
                def statusColor = (buildResult == 'SUCCESS') ? 'green' : 'red'

                // Ruta del video generado por Zalenium
                def videoDir = "/tmp/videos"
                def videoFiles = sh(script: "ls ${videoDir}/*.mp4", returnStdout: true).trim().split("\n")

                // Copiar los videos al directorio de trabajo de Jenkins
                videoFiles.each { videoFile ->
                    def videoName = videoFile.split("/").last()
                    sh "cp ${videoFile} target/${videoName}"
                }

                // Generar el archivo ZIP del reporte
                def reportPath = "target/site/serenity"
                if (fileExists(reportPath)) {
                    sh "zip -rq target/${env.REPORT_ZIP} ${reportPath}/*"
                } else {
                    echo "No se encontraron archivos en ${reportPath}. Creando un ZIP vacío."
                    sh "zip -rq target/${env.REPORT_ZIP}"
                }

                // Archivar artefactos
                archiveArtifacts artifacts: "target/${env.REPORT_ZIP}, target/*.mp4", allowEmptyArchive: true

                // Definir el cuerpo del correo
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
                        <p class="status">La ejecución del pipeline de Serenity BDD ha finalizado con el estado: ${buildResult}.</p>
                        <p><strong>Entorno:</strong> ${env.ACTUAL_ENVIRONMENT}</p>
                        <p>Puedes ver el reporte de pruebas aquí:</p>
                        <p>{Download Folder}/target/site/serenity/index.html</p>
                        <p>El reporte completo se adjunta como un archivo ZIP.</p>
                    </div>
                    <div class="footer">
                        <p>Testing Message | Administrador de Redes y Sistemas (NSA)</p>
                        <div>
                            <img src="data:image/png;base64,...(tu imagen)" alt="Triskell" />
                            <img src="data:image/png;base64,...(tu imagen)" alt="Logo del Socio" />
                        </div>
                    </div>
                </body>
                </html>
                """

                // Enviar el correo con los videos adjuntos
                emailext(
                    to: "${env.DISTRIBUTION_LIST}",
                    subject: "Ejecución del pipeline: ${buildResult}",
                    body: emailBody,
                    attachLog: true,
                    attachmentsPattern: "target/*.mp4"
                )
            }
        }
    }
}
