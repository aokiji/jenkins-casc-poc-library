// override sh to add default behaviours

def call(Map args = [:]) {
  String script = args.script
  Boolean printCommands = args.get('printCommands', false)

  if (printCommands) {
    script = "set -x\n$script"
  }
  return steps.sh(script: script)
}

def call(String script) {
  return call(script: script)
}
