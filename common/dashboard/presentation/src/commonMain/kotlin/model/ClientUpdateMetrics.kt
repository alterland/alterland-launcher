package model

data class ClientUpdateMetrics (
    val total: Double = 0.0,
    val received: Double = 0.0,
    val speed: Double? = null,
    val timeLeft: Double? = null,
    val percent: Float = 0.0F,
)