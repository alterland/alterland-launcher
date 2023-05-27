package model

data class ClientItem(
    val clientName: String,
    val status: ClientStatus = ClientStatus.READY
)
