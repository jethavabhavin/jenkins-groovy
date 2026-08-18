def call(Map config = [:]) {
    echo "Deploying application with Docker Compose..."

    bat 'docker compose down --remove-orphans'
    bat 'docker compose up -d'
    bat 'docker compose ps'
}
