package ktor

import baseUrl
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.cookies.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import json.JSON
import local.CustomCookiesStorage
import local.persistent.PersistentStorage

class HttpClient(private val persistentStorage: PersistentStorage) {
    val client = HttpClient(HttpEngineFactory().createEngine()) {
        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.ALL
        }
        install(HttpCookies) {
            storage = CustomCookiesStorage(persistentStorage)
        }
        install(ContentNegotiation) {
            json(JSON)
        }
        install(HttpTimeout) {
            connectTimeoutMillis = 15000
            requestTimeoutMillis = 30000
        }
        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = baseUrl
            }
            header("Content-Type", "application/json; charset=UTF-8")
        }
    }
}