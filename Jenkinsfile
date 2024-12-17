post {
    always {
        script {
            // Determinar el resultado de la construcción
            def buildResult = currentBuild.result ?: 'SUCCESS'
            def statusColor = (buildResult == 'SUCCESS') ? 'green' : 'red'

            // Generar el zip del reporte
            def reportPath = "target/site/serenity"
            if (fileExists(reportPath)) {
                sh "zip -rq target/${env.REPORT_ZIP} ${reportPath}/*"
            } else {
                echo "No se encontraron archivos en ${reportPath}. Creando un archivo ZIP vacío."
                sh "zip -rq target/${env.REPORT_ZIP}"
            }

            // Archivar los artefactos
            archiveArtifacts artifacts: "target/${env.REPORT_ZIP}", allowEmptyArchive: true

            // Subir el reporte al servidor Node.js
            sh '''
            # Copiar el reporte a la máquina host (donde está el servidor Node.js)
            # Asegúrate de tener configurada la clave SSH o usa un método adecuado para transferir el reporte
            scp -r target/site/serenity ec2-user@<your-ec2-instance>:/path/to/serenity_report
            '''

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
                    <p class="status">La ejecución de Serenity BDD ha finalizado con el estado: ${buildResult}.</p>
                    <p><strong>Entorno:</strong> ${env.ACTUAL_ENVIRONMENT}</p>
                    <p>Puedes ver el reporte aquí:</p>
                    <p><a href="http://<your-server-ip>:8080/report/index.html">Ver Reporte</a></p>
                    <p>El reporte completo está adjunto como un archivo ZIP.</p>
                </div>
                <div class="footer">
                    <p>Mensaje de Pruebas | Administrador de Redes y Sistemas (NSA)</p>
                    <div>
                        <img src="cid:triskell-logo" alt="Logo Triskell" />
                        <img src="cid:partner-logo" alt="Logo Partner" />
                    </div>
                    <p>
                        Para más información, contacta con nosotros en <a href="mailto:rgpd@triskellsoftware.com">rgpd@triskellsoftware.com</a>.
                    </p>
                </div>
            </body>
            </html>
            """

            // Enviar correo
            emailext(
                subject: "Ejecución del Pipeline de Serenity BDD: ${buildResult} [${env.ACTUAL_ENVIRONMENT}]",
                body: emailBody,
                mimeType: 'text/html',
                to: env.DISTRIBUTION_LIST,
                attachmentsPattern: "target/${env.REPORT_ZIP}",
                attachLog: true
            )
        }
    }
}