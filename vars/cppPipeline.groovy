def call(Map args = [:]) {
  node('master') {
    parallelLinux { platform ->
      stage("checkout $platform") {
        checkout(scm)
      }
      stage("build $platform") {
        buildCppTarget(platform: platform, target: 'hello')
        buildCppTarget(platform: platform, target: 'good_bye')
        buildCppTarget(platform: platform, target: 'utils_test')
      }
      stage("test $platform") {
        testCppTarget(platform: platform, target: 'utils_test')
      }
      stage("deploy $platform") {
        deployCppTarget(targets: ['hello', 'good_bye'])
      }
    }

    notifyCppBuildResults()
  }
}
