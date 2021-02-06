def call(Map args = [:]) {
  node('master') {
    parallelLinux { platform ->
      stage("build $platform") {
        echo "building in $platform"
        sh "echo building in \$BUILD_PLATFORM"
      }
      stage("test $platform") {
        echo "testing in $platform"
        sh """
          test "\$BUILD_PLATFORM" = "$platform"
        """
        sh(
          printCommands: true,
          script: """
            test HELLO = HELLO
            echo goodbye
          """
        )
      }
    }
  }
}
