package api

import clientDomain
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*
import model.request.ClientFilesSchemaRequest
import model.response.ClientFilesResponse

class ClientApi(private val httpClient: HttpClient) {

    suspend fun getClients(): List<String> = httpClient.get {
        url {
            protocol = URLProtocol.HTTPS
            host = clientDomain
            path("api/clients")
        }
        contentType(ContentType.Application.Json)
    }.body()

    suspend fun getFiles(clientName: String, schema: ClientFilesSchemaRequest): ClientFilesResponse = httpClient.post {
        url {
            protocol = URLProtocol.HTTPS
            host = clientDomain
            path("api/files/$clientName")
        }
        contentType(ContentType.Application.Json)
        setBody(schema)
    }.body()

    suspend fun downloadFile(filePath: String) = httpClient.prepareGet {
        url {
            protocol = URLProtocol.HTTPS
            host = clientDomain
            path("files/$filePath")
        }
        timeout {
            requestTimeoutMillis = HttpTimeout.INFINITE_TIMEOUT_MS
        }
    }
}