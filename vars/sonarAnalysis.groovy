def call(Map config = [:]) {
    String serverName = config.get('serverName', 'SonarQubeServer')
    String projectKey = config.get('projectKey', 'ai-demo')
    String projectName = config.get('projectName', 'ai-demo')
    String sources = config.get('sources', '.')
    String exclusions = config.get('exclusions', '**/node_modules/**,**/dist/**,**/.git/**,**/.husky/**')
    String sonarHome = config.get('sonarHome', env.SONAR_HOME)

    echo "SonarQube analysis started..."

    withSonarQubeEnv(serverName) {
        bat """
            "${sonarHome}/bin/sonar-scanner" ^
            -Dsonar.projectKey=${projectKey} ^
            -Dsonar.projectName=${projectName} ^
            -Dsonar.sources=${sources} ^
            -Dsonar.exclusions=${exclusions}
        """
    }

    echo "SonarQube analysis completed"
}
