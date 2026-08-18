def call() {
    echo "Checking Windows environment..."

    bat 'docker --version'
    bat 'docker info'
    bat 'git --version'
}
