@groovy.transform.Field
List platforms = [ 'linux', 'other' ]

def call(Map args = [:], Closure body) {
  def tasks = [:]
  platforms.each { platform ->
    def env = [ "BUILD_PLATFORM=$platform" ]
    tasks[env.toString()] = {
      node(platform) {
        withEnv(env) {
          body(platform)
        }
      }
    }
  }

  parallel tasks
}
