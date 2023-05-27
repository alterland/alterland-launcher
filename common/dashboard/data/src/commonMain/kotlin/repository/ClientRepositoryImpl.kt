package repository

import api.ClientApi
import hash.HashUtils
import hash.MessageDigestAlgorithm
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.date.*
import io.ktor.utils.io.core.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import localPath
import model.DownloadMetrics
import model.request.ClientFilesSchemaRequest
import model.response.ClientFilesResponse
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.security.MessageDigest

class ClientRepositoryImpl(private val clientApi: ClientApi): ClientRepository {

    private val _downloadMetrics = MutableStateFlow(DownloadMetrics(0.0,0.0,0.0,0.0))
    override val downloadMetrics: StateFlow<DownloadMetrics> = _downloadMetrics.asStateFlow()

    override suspend fun getClients() = withContext(Dispatchers.IO) {
        clientApi.getClients()
    }

    override suspend fun checkClientUpdates(clientName: String): ClientFilesResponse = withContext(Dispatchers.IO) {
        val schema = scanClient(clientName)
        clientApi.getFiles(clientName, schema)
    }

    override suspend fun updateClient(clientName: String) {
        val files = checkClientUpdates(clientName)
        download(clientName, files)
    }

    private fun scanClient(clientName: String): ClientFilesSchemaRequest {
        val filesMap: MutableMap<String, String> = mutableMapOf()
        val clientPath = "$localPath/$clientName/"

        val messageDigest = MessageDigest.getInstance(MessageDigestAlgorithm.MD5)

        val clientDir = File(clientPath)

        if (clientDir.isDirectory) {
            for (file in clientDir.walkTopDown()) {
                var relativePath = file.absolutePath.substring(clientPath.length-1 until file.absolutePath.length)
                if (relativePath.isEmpty()) continue
                if (relativePath[0] == '/') relativePath = relativePath.removeRange(0 until 1)

                val pathFolders = relativePath.split('/')

                if (pathFolders.isEmpty()) continue

                val folderName = pathFolders[0]
                //if (ignoredFiles.contains(folderName)) continue
                val dir = File(file.absolutePath)

                if (dir.isDirectory) continue

                val start = getTimeMillis()

                val fileHash = HashUtils.getCheckSumFromFile(
                    messageDigest,
                    file
                )

                val end = getTimeMillis()
                println("Total hash time of ${relativePath}: ${end-start} ms")

                filesMap[relativePath] = fileHash
            }
        }

        return ClientFilesSchemaRequest(schema = filesMap)
    }

    private suspend fun download(clientName: String, files: ClientFilesResponse) = withContext(Dispatchers.IO) {
        var downloadInProgress = true

        val bytesMeasurePeriod = 1000L
        var bytesTotal = 0.0
        var bytesReceivedPerPeriod = 0.0
        var bytesReceivedTotal = 0.0

        val speedMeasurePeriod = 3000L
        var speedAverage: Double? = null
        var bytesReceivedForSpeedPeriod = 0.0
        var bytePeriods = 0L

        val timer = async {
            while (downloadInProgress) {
                delay(bytesMeasurePeriod)
                bytePeriods += bytesMeasurePeriod
                bytesReceivedForSpeedPeriod += bytesReceivedPerPeriod
                if (bytePeriods >= speedMeasurePeriod) {
                    speedAverage = bytesReceivedForSpeedPeriod / (speedMeasurePeriod / 1000)
                    bytesReceivedForSpeedPeriod = 0.0
                }
                val newMetrics = DownloadMetrics(
                    timeLeft = (bytesTotal-bytesReceivedTotal) / (speedAverage ?: bytesReceivedPerPeriod),
                    speed = bytesReceivedPerPeriod,
                    received = bytesReceivedTotal,
                    total = bytesTotal
                )
                bytesReceivedPerPeriod = 0.0
                _downloadMetrics.tryEmit(newMetrics)
            }
        }

        val download = async {
            files.new.forEach { path ->
                val localSavePath = "$localPath/$clientName/"
                val downloadPath = "$clientName/$path"

                Files.createDirectories(Paths.get(localSavePath))

                val file = File("$localSavePath/${path.split("/").last()}")

                clientApi.downloadFile(downloadPath).execute { response ->
                    val channel = response.bodyAsChannel()

                    bytesTotal = response.contentLength()?.toDouble() ?: 0.0

                    while (!channel.isClosedForRead) {
                        val packet = channel.readRemaining(DEFAULT_BUFFER_SIZE.toLong())
                        while (!packet.isEmpty) {
                            val bytes = packet.readBytes()
                            file.appendBytes(bytes)
                            bytesReceivedPerPeriod += bytes.size
                            bytesReceivedTotal += bytes.size
                        }
                    }
                    println("A file saved to ${file.path}")
                    bytesReceivedTotal = 0.0
                }
            }
            downloadInProgress = false
        }

        timer.await()
        download.await()
    }

    override suspend fun launchClient(clientName: String) {
        println("Launching Minecraft...")
    }
}
