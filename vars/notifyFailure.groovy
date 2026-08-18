def call(Map config = [:]) {
    String fromEmail = config.get('from', env.EMAIL_FROM ?: env.NOTIFY_FROM)
    String toEmail = config.get('to', env.EMAIL_TO ?: env.NOTIFY_TO)
    String buildNumber = config.get('buildNumber', env.BUILD_NUMBER ?: '')
    String jobName = config.get('jobName', env.JOB_NAME ?: '')
    String buildUrl = config.get('buildUrl', env.BUILD_URL ?: '')

    echo "Pipeline failed for Build #${buildNumber}"

    Map emailParams = [
        mimeType: 'text/html',
        attachLog: true,
        to: toEmail,
        subject: "✖ [FAILURE] ${jobName} - Build #${buildNumber}",
        body: """
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="utf-8">
                <style>
                    body { font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Helvetica, Arial, sans-serif; background-color: #f4f6f9; margin: 0; padding: 20px; color: #1e293b; }
                    .container { max-width: 600px; margin: 0 auto; background: #ffffff; border-radius: 12px; overflow: hidden; box-shadow: 0 4px 16px rgba(0,0,0,0.08); border: 1px solid #e2e8f0; }
                    .header { background: linear-gradient(135deg, #dc2626 0%, #ef4444 100%); color: #ffffff; padding: 28px 24px; text-align: center; }
                    .header h1 { margin: 0; font-size: 24px; font-weight: 700; }
                    .header p { margin: 6px 0 0 0; opacity: 0.9; font-size: 14px; }
                    .content { padding: 24px; }
                    .status-badge { display: inline-block; background-color: #fee2e2; color: #991b1b; font-weight: 600; font-size: 12px; padding: 4px 12px; border-radius: 9999px; margin-bottom: 16px; }
                    .info-table { width: 100%; border-collapse: collapse; margin-top: 16px; margin-bottom: 20px; }
                    .info-table td { padding: 10px 12px; border-bottom: 1px solid #f1f5f9; font-size: 14px; }
                    .info-table td.label { font-weight: 600; color: #64748b; width: 35%; }
                    .info-table td.value { color: #0f172a; word-break: break-all; }
                    .code-badge { background: #f1f5f9; padding: 2px 6px; border-radius: 4px; font-family: monospace; font-size: 13px; color: #0f172a; }
                    .alert-box { background-color: #fef2f2; border: 1px solid #fecaca; border-radius: 8px; padding: 12px 16px; font-size: 13px; color: #991b1b; margin-top: 12px; }
                    .btn-wrapper { text-align: center; margin: 28px 0 12px 0; }
                    .btn { background: #ef4444; color: #ffffff !important; padding: 12px 24px; text-decoration: none; border-radius: 8px; font-weight: 600; font-size: 14px; display: inline-block; }
                    .footer { background: #f8fafc; padding: 16px 24px; text-align: center; font-size: 12px; color: #94a3b8; border-top: 1px solid #e2e8f0; }
                </style>
            </head>
            <body>
                <div class="container">
                    <div class="header">
                        <h1>Pipeline Build Failed</h1>
                        <p>${jobName} &bull; Build #${buildNumber}</p>
                    </div>
                    <div class="content">
                        <span class="status-badge">✖ BUILD FAILED</span>
                        <p style="margin: 0 0 12px 0; font-size: 14px; line-height: 1.5; color: #334155;">
                            The pipeline encountered an error during execution. Please review the attached build logs or console logs to investigate the root cause.
                        </p>
                        <table class="info-table">
                            <tr>
                                <td class="label">Project</td>
                                <td class="value"><strong>${jobName}</strong></td>
                            </tr>
                            <tr>
                                <td class="label">Build Number</td>
                                <td class="value"><span class="code-badge">#${buildNumber}</span></td>
                            </tr>
                        </table>
                        <div class="alert-box">
                            <strong>Action Required:</strong> Build log is attached with this email. Check failure step in Jenkins console.
                        </div>
                        <div class="btn-wrapper">
                            <a href="${buildUrl}" class="btn" target="_blank">View Console Logs</a>
                        </div>
                    </div>
                    <div class="footer">
                        Automated notification sent from Jenkins CI/CD Pipeline
                    </div>
                </div>
            </body>
            </html>
        """
    ]

    if (fromEmail) {
        emailParams.from = fromEmail
    }

    emailext(emailParams)
}
