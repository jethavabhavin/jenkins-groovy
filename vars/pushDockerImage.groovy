def call(Map config = [:]) {
    String credentialsId = config.get('credentialsId', 'DockerHub')
    String imageName = config.get('imageName', env.IMAGE_NAME)
    String imageTag = config.get('imageTag', env.IMAGE_TAG)

    echo "Docker login started..."

    withCredentials([
        usernamePassword(
            credentialsId: credentialsId,
            usernameVariable: 'DOCKER_USER',
            passwordVariable: 'DOCKER_PASS'
        )
    ]) {
        bat '''
            docker logout
            echo %DOCKER_PASS%| docker login -u %DOCKER_USER% --password-stdin
        '''
    }

    echo "Docker login completed"

    echo "Pushing image to Docker Hub..."

    bat "docker push ${imageName}:${imageTag}"
    bat "docker push ${imageName}:latest"

    echo "Docker images pushed successfully"
}
