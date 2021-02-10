import groovy.json.JsonOutput

def call(Map args = [:]) {
  List<String> targets = args.targets

  file_spec = [
    files: targets.collect { target ->
      return [
        pattern: "build/$target",
        target: 'example-repo-local/BUILD_${BUILD_NUMBER}/${BUILD_PLATFORM}/'
      ]
    }
  ]
  def file_spec_str = JsonOutput.toJson(file_spec)

  rtUpload(serverId: 'artifactory', spec: file_spec_str)
  rtPublishBuildInfo(serverId: 'artifactory')
}
