package local.persistent.structure

import kotlinx.serialization.Serializable

@Serializable
data class Persistent(
    val settings: MutableMap<String, String>,
    val cookies: MutableMap<String, List<PersistentCookie>> = mutableMapOf()
)