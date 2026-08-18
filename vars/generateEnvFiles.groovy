def call(Map config = [:]) {
    String serverCredId = config.get('serverCredentialsId', 'ServerEnv')
    String pwaCredId = config.get('pwaCredentialsId', 'PwaEnv')
    String serverDest = config.get('serverDest', 'apps\\server\\.env')
    String pwaDest = config.get('pwaDest', 'apps\\pwa\\.env')

    echo "Generating server .env file..."
    withCredentials([
        file(credentialsId: serverCredId, variable: 'ENV_FILE')
    ]) {
        bat "copy /Y \"%ENV_FILE%\" \"${serverDest}\""
    }
    echo "Server .env file generated"

    echo "Generating PWA .env file..."
    withCredentials([
        file(credentialsId: pwaCredId, variable: 'ENV_FILE')
    ]) {
        bat "copy /Y \"%ENV_FILE%\" \"${pwaDest}\""
    }
    echo "PWA .env file generated"
}
