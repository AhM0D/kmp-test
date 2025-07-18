package org.test.example.di

import org.koin.core.module.Module
import org.koin.dsl.module
import org.test.example.domain.GetHomeListUseCase

fun createDomainModule(): Module = module {
    factory { GetHomeListUseCase(get()) }
}