package org.test.example.di

import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.koin.dsl.module

fun networkModuleConfig(baseUrl: String) = module {
    single<HttpClient> {
        createHttpClient(baseUrl, get())
    }
}

@OptIn(ExperimentalSerializationApi::class)
fun createHttpClient(
    baseUrl: String,
    engine: HttpClientEngine,
): HttpClient = HttpClient(engine) {
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
            explicitNulls = false
        })
    }
    defaultRequest {
        url {
            host = baseUrl
            protocol = URLProtocol.HTTPS
        }
        contentType(ContentType.Application.Json)
    }

    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                Napier.v(message = message, tag = "HTTPS Client")
            }
        }
        level = LogLevel.ALL
    }
}