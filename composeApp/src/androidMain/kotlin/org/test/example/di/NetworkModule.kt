package org.test.example.di

import io.ktor.client.engine.okhttp.OkHttp
import okhttp3.OkHttpClient
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformNetModule(): Module = module {
    single {
        OkHttp.create {
            preconfigured = getOkHttpClient()
        }
    }
}

fun getOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .build()
}