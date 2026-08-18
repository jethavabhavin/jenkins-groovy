def call(Map config = [:]) {
    String repoUrl = config.get('url', 'https://github.com/jethavabhavin/ai-demo')
    String branchName = config.get('branch', 'main')

    echo "Git checkout started"

    git(
        url: repoUrl,
        branch: branchName
    )

    echo "Git checkout completed"
}
