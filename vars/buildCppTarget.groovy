def call(Map args) {
  String platform = args.platform
  String target = args.target

  echo "building $target in $platform"
  sh "echo building $target in \$BUILD_PLATFORM"
}
