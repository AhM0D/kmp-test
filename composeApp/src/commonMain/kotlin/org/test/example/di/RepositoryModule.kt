package org.test.example.di

import org.koin.core.module.Module
import org.koin.dsl.module
import org.test.example.data.PostRepository
import org.test.example.data.PostRepositoryDefault

fun createRepositoryModule(): Module = module {
    single<PostRepository> { PostRepositoryDefault(get()) }
}