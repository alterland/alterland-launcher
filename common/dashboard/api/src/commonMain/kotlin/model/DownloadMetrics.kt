package model

data class DownloadMetrics(
    val timeLeft: Double = 0.0,
    val speed: Double = 0.0,
    val received: Double = 0.0,
    val total: Double = 0.0
)
