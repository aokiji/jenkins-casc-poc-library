
def call(Map args = [:]) {
  node('master') {
    parallelLinux { platform ->
      try {
        checkout(scm)
        buildCpp(platform: platform, targets: args.targets)
        testCpp(platform: platform, targets: args.targets)
        deployCpp(platform: platform, targets: args.targets)
      }
      catch(e) {
        println "Failed while parallel execution:"
        println e
        currentBuild.result = 'FAILURE'
      }
    }

    notifyCppBuildResults()
  }
}
