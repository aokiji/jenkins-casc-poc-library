def call(Map args) {
  String platform = args.platform
  String target = args.target

  echo "testing $target in $platform"
  sh """
    test "\$BUILD_PLATFORM" = "$platform"
  """
  sh(
    printCommands: true,
    script: """
      test HELLO = HELLO
      echo goodbye
    """
  )
}
