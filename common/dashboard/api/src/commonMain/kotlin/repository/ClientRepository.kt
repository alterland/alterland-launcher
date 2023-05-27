package repository

import kotlinx.coroutines.flow.StateFlow
import model.DownloadMetrics
import model.response.ClientFilesResponse

interface ClientRepository {
    val downloadMetrics: StateFlow<DownloadMetrics>
    suspend fun getClients(): List<String>
    suspend fun checkClientUpdates(clientName: String): ClientFilesResponse
    suspend fun updateClient(clientName: String)
    suspend fun launchClient(clientName: String)
}