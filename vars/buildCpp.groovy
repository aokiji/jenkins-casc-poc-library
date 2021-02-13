def call(Map args = [:]) {
  String platform = args.platform
  def targets = args.targets

  stage("build $platform") {
    for (target in targets) {
      buildCppTarget(platform: platform, target: target.name)
    }
  }
}
