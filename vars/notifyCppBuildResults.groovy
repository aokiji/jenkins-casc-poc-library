def call(Map args = [:]) {
  stage('notify build results') {
    if (currentBuild.result == 'FAILURE') {
      emailext body: """
        <html>
          <body>
            <h1>Your build failed</h1>
            <p>See your build info <a href="${env.BUILD_URL}">here</a></p>
          </body>
        </html>
        """,
        mimeType: 'text/html',
        recipientProviders: [requestor()],
        subject: "The build for ${env.JOB_NAME} ${env.BUILD_DISPLAY_NAME} failed"
    }
  }
}
