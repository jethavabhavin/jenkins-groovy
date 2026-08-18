def call(Map config = [:]) {
    String imageName = config.get('imageName', env.IMAGE_NAME)
    String imageTag = config.get('imageTag', env.IMAGE_TAG)
    String dockerfilePath = config.get('dockerfile', '.')

    echo "Docker Build started..."

    bat """
        docker build ^
        -t ${imageName}:${imageTag} ^
        -t ${imageName}:latest ${dockerfilePath}
    """

    echo "Docker Build completed"
}
