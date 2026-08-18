def call() {
    echo "Cleaning workspace..."
    cleanWs()
    bat 'docker logout'
}
