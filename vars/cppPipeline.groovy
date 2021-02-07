def call(Map args = [:]) {
  node('master') {
    def branches = args.get('branches', '*/master')

    parallelLinux { platform ->
      stage("checkout $platform") {
        checkout([
          $class: 'GitSCM',
          branches: [[name: branches]],
          doGenerateSubmoduleConfigurations: false,
          extensions: [],
          submoduleCfg: [],
          userRemoteConfigs: [[url: 'https://github.com/aokiji/jenkins-casc-poc-project']]
        ])
      }
      stage("build $platform") {
        buildCppTarget(platform: platform, target: 'hello')
        buildCppTarget(platform: platform, target: 'good_bye')
        buildCppTarget(platform: platform, target: 'utils_test')
      }
      stage("test $platform") {
        testCppTarget(platform: platform, target: 'utils_test')
        sh """
            test "\$(./build/hello)" = "Hello World!"
            test "\$(./build/good_bye)" = "Good Bye!"
          """
      }
    }
  }
}
