def call(Map config = [:]) {
    int timeoutMinutes = config.get('timeoutMinutes', 2)
    boolean abortPipeline = config.get('abortPipeline', true)

    echo "Checking SonarQube Quality Gate..."

    timeout(time: timeoutMinutes, unit: 'MINUTES') {
        waitForQualityGate(abortPipeline: abortPipeline)
    }
}
