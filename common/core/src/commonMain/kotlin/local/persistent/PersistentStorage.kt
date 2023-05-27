package local.persistent

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import localPath
import local.persistent.structure.Persistent
import local.persistent.structure.PersistentCookie
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

interface PersistentStorage {
    val settingsFlow: MutableSharedFlow<Map<String, String>>
    val cookiesFlow: MutableSharedFlow<Map<String, List<PersistentCookie>>>
    fun getSettings(): MutableMap<String, String>
    fun storeSetting(key: String, value: Any)
    fun getString(key: String): String?
    fun getBoolean(key: String): Boolean?
    fun getDouble(key: String): Double?
    suspend fun storeCookie(domain: String, cookie: PersistentCookie)
    fun getAllCookies(domain: String): List<PersistentCookie>?

    companion object {
        const val REMEMBER = "REMEMBER"
        const val PATH = "PATH"
        const val RAM = "RAM"
    }
}

@OptIn(DelicateCoroutinesApi::class)
internal class PersistentStorageImpl: PersistentStorage {
    companion object {
        private const val fileName = "persistent.json"

        //default launcher settings
        private const val defaultRememberMe = true
        private const val defaultRAM: Double = 4.0 //in GB
    }

    private lateinit var storage: Persistent
    private lateinit var file: File

    init {
        getOrCreate()
    }

    override val settingsFlow = MutableSharedFlow<Map<String, String>>(1, 0, BufferOverflow.DROP_OLDEST)
    override val cookiesFlow = MutableSharedFlow<Map<String, List<PersistentCookie>>>(1, 0, BufferOverflow.DROP_OLDEST)

    override fun getSettings(): MutableMap<String, String> = storage.settings

    override fun storeSetting(key: String, value: Any) {
        storage.settings[key] = value.toString()
        GlobalScope.launch {
            saveStorage()
            settingsFlow.emit(storage.settings)
        }
    }

    override fun getString(key: String) = storage.settings[key]

    override fun getBoolean(key: String) = storage.settings[key]?.toBooleanStrictOrNull()

    override fun getDouble(key: String) = storage.settings[key]?.toDoubleOrNull()


    override suspend fun storeCookie(domain: String, cookie: PersistentCookie) {
        val domainStorage = storage.cookies.getOrPut(domain, defaultValue = { listOf() })
        val cookies = domainStorage.filterNot { it.name == cookie.name }.toMutableList()
        if (cookie.value.isNotEmpty()) {
            //if cookie value is empty then it is a remove cookie call
            cookies.add(cookie)
        }
        storage.cookies[domain] = cookies

        if (cookie.name != "Access-Token") {
            saveStorage()
        } else {
            if (getBoolean(PersistentStorage.REMEMBER) == true) {
                saveStorage()
            }
        }
        cookiesFlow.emit(storage.cookies)
    }

    override fun getAllCookies(domain: String) = storage.cookies[domain]?.filter { it.value.isNotEmpty() }

    private fun getOrCreate() {
        GlobalScope.launch(Dispatchers.IO) {
            val directory = File(localPath)
            file = File("$directory/$fileName")
            val isNewFileCreated = file.createNewFile()

            storage = if (isNewFileCreated) fillNewFile(file) else readFromStorage(file)

            settingsFlow.emit(storage.settings)
            cookiesFlow.emit(storage.cookies)
        }
    }

    private suspend fun readFromStorage(existingFile: File): Persistent = withContext(Dispatchers.IO) {
        val fileContent = existingFile.readText()
        return@withContext try {
            Json.decodeFromString<Persistent>(fileContent)
        } catch (e: SerializationException) {
            migrate(existingFile, fileContent)
        } catch (e: Exception) {
            throw Error(e)
        }
    }

    private suspend fun saveStorage() = withContext(Dispatchers.IO) {
        val json = Json.encodeToString(storage)
        file.writeText(json)
    }

    private suspend fun migrate(existingFile: File, prevContent: String) = withContext(Dispatchers.IO) {
        readFallback(prevContent)
        fillNewFile(existingFile)
    }

    private suspend fun fillNewFile(newFile: File): Persistent = withContext(Dispatchers.IO) {
        val storage = Persistent(
            settings = mutableMapOf<String, String>().apply {
                put(PersistentStorage.REMEMBER, defaultRememberMe.toString())
                put(PersistentStorage.PATH, localPath)
                put(PersistentStorage.RAM, defaultRAM.toString())
            }
        )

        val json = Json.encodeToString(storage)

        newFile.writeText(json)

        println("Persistent storage created on ${newFile.path}")

        return@withContext readFromStorage(newFile)
    }

    private suspend fun readFallback(prevContent: String) = withContext(Dispatchers.IO) {
        val formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss")
        val current = LocalDateTime.now().format(formatter)
        val directory = File(localPath)
        val file = File("$directory/error_backup_${current}_$fileName")
        file.writeText(prevContent)
    }
}