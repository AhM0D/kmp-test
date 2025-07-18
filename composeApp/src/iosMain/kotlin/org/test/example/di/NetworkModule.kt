package org.test.example.di

import io.ktor.client.engine.darwin.Darwin
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformNetModule(): Module = module {
    single { Darwin.create() }
}