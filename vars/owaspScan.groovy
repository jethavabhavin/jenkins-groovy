def call(Map config = [:]) {
    String scanPath = config.get('scanPath', './')
    String odcInstallation = config.get('odcInstallation', 'OWASP')
    String reportPattern = config.get('reportPattern', '**/reports/dependency-check-report.xml')

    echo "Running OWASP Dependency Check..."

    dependencyCheck(
        additionalArguments: "--scan ${scanPath}",
        odcInstallation: odcInstallation
    )

    dependencyCheckPublisher(
        pattern: reportPattern
    )

    echo "OWASP Dependency Check completed"
}
