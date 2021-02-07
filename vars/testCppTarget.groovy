def call(Map args) {
  String platform = args.platform
  String target = args.target

  sh """
    ./build/$target --log_format=XML --log_sink="results_${target}.xml" --log_level=all --report_level=no -- 2>/dev/null 1>/dev/null || echo "test error are checked later"
  """
  xunit (
    thresholds: [ skipped(failureThreshold: '0'), failed(failureThreshold: '0') ],
    tools: [ BoostTest(pattern: "results_${target}.xml") ]
  )
}
