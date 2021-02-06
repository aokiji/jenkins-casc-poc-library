def call(Map args = [:]) {
  node('master') {
    parallelLinux { platform ->
      stage("build $platform") {
        buildCppTarget(platform: platform, target: 'target1')
        buildCppTarget(platform: platform, target: 'target2')
      }
      stage("test $platform") {
        testCppTarget(platform: platform, target: 'target1')
      }
    }
  }
}
