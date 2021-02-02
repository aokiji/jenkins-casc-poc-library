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
          set -e
          test "\$BUILD_PLATFORM" = "$platform"
        """
      }
    }
  }
}
