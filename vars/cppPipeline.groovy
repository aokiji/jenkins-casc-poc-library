def call(Map map) {
  def targets = map.targets
  label 'linux'
  stage('build') {
    for (target in targets) {
      buildCpp(target: target)
    }
  }
  stage('test') {
    for (target in targets) {
      testCpp(target: target)
    }
  }
}
