pipeline {
    agent any
    environment {
        MAVEN_HOME = tool name: 'Maven 3.9.6', type: 'maven'
        JAVA_HOME = tool name: 'jdk-23', type: 'jdk'
        CREDENTIALS_FILE = credentials('CREDENTIALS_FILE')
        serenityEnvironmentFile = 'src/test/resources/environment.properties'
        REPORT_ZIP = 'serenity-report.zip'
        DISTRIBUTION_LIST = 'rperdigon@triskellsoftware.com,jmprieto@triskellsoftware.com,jburcio@triskellsoftware.com,agarcia@triskellsoftware.com'
        VIDEO_PATH = '/tmp/videos'
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

                // Obtener el nombre del escenario desde una variable de entorno o archivo
                // Supongamos que el nombre del escenario está disponible en la variable `env.SCENARIO_NAME`
                def scenarioName = env.SCENARIO_NAME ?: "unknown_scenario"

                // Reemplazar caracteres no válidos para nombres de directorios
                scenarioName = scenarioName.replaceAll("[^a-zA-Z0-9_-]", "_")

                // Generar un nombre de carpeta basado en el escenario
                def videoFolder = "videos_${scenarioName}"

                // Crear el directorio para almacenar los videos
                sh "mkdir -p ${videoFolder}"

                // Generate report zip
                def reportPath = "target/site/serenity"
                if (fileExists(reportPath)) {
                    sh "zip -rq target/${env.REPORT_ZIP} ${reportPath}/*"
                    // Verificar si existen archivos de video en VIDEO_PATH
                    if (fileExists(env.VIDEO_PATH)) {
                        // Mover todos los archivos .mp4 a la carpeta dinámica
                        sh "mv ${env.VIDEO_PATH}/*.mp4 ${videoFolder}/"

                        // Iterar sobre los videos en la carpeta y agregar cada uno al archivo ZIP
                        def videos = sh(
                            script: "ls ${videoFolder}/*.mp4",
                            returnStdout: true
                        ).trim().split("\n")

                        // Agregar cada video al archivo ZIP
                        videos.each { video ->
                            if (video) {
                                echo "Adding video: ${video}"
                                sh "zip -j target/${env.REPORT_ZIP} ${video}"
                            }
                        }
                    } else {
                        echo "No video files found in ${env.VIDEO_PATH}. Skipping video attachment."
                    }
                } else {
                    echo "No files found at ${reportPath}. Creating an empty ZIP file."
                    sh "zip -rq target/${env.REPORT_ZIP}"
                }

                // Eliminar los videos
                //if (fileExists(env.VIDEO_PATH)) {
                //    echo "Eliminando videos del directorio ${env.VIDEO_PATH}."
                //    sh "rm -rf ${env.VIDEO_PATH}/*"
                //} else {
                //    echo "No se encontró el directorio ${env.VIDEO_PATH}."
                //}

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
                            <img src="data:image/png;base64,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" alt="Triskell" />
                            <img src="data:image/png;base64,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" alt="Partner Logo" />
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
