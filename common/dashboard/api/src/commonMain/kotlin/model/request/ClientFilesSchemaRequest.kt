package model.request

import kotlinx.serialization.Serializable

@Serializable
data class ClientFilesSchemaRequest(
    val schema: Map<String, String>
)