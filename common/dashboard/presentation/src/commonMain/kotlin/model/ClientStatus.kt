package model

enum class ClientStatus {
    READY,
    CHECKING,
    UPDATE_AVAILABLE,
    DOWNLOADING,
    DOWNLOAD_PAUSED,
    LAUNCHED
}