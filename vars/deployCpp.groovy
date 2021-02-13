def call(Map args) {
  String platform = args.platform
  def targets = args.targets.findAll {
    it.deploy == true
  }

  if (targets.empty) {
    return;
  }

  stage("deploy $platform") {
    deployCppTarget(platform: platform, targets: targets*.name)
  }
}
