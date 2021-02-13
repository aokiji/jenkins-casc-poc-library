def call(Map args = [:]) {
  String platform = args.platform
  def targets = args.targets.findAll {
    it.test == true
  }

  if (targets.empty) {
    return;
  }

  stage("test $platform") {
    for (target in targets) {
      buildCppTarget(platform: platform, target: target.name)
    }
  }
}
