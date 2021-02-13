def call(Map args = [:]) {
  node('master') {
    parallelLinux { platform ->
      checkout(scm)
      buildCpp(platform: platform, targets: args.targets)
      testCpp(platform: platform, targets: args.targets)
      deployCpp(platform: platform, targets: args.targets)
    }

    notifyCppBuildResults()
  }
}
