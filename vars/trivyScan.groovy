def call(Map config = [:]) {
    String imageName = config.get('imageName', env.IMAGE_NAME)
    String imageTag = config.get('imageTag', env.IMAGE_TAG)
    String severity = config.get('severity', 'HIGH,CRITICAL')
    int exitCode = config.get('exitCode', 1)

    echo "Running Trivy security scan..."

    bat """
        docker run --rm ^
        -v //var/run/docker.sock:/var/run/docker.sock ^
        aquasec/trivy:latest image ^
        --no-progress ^
        --exit-code ${exitCode} ^
        --severity ${severity} ^
        ${imageName}:${imageTag}
    """
}
