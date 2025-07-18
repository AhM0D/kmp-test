package org.test.example.di

import com.slack.circuit.foundation.Circuit
import org.koin.core.module.Module
import org.koin.dsl.module
import org.test.example.presentation.HomeComposableScreen
import org.test.example.presentation.HomePresenter
import org.test.example.presentation.HomeUiState
import org.test.example.presentation.Screens
import kotlin.text.get

fun createCircuit(): Module = module {
    createPresenterFactory { navigator, screen ->
        when (screen) {
            is Screens.HomScreen -> HomePresenter(navigator)
            else -> throw Exception("Invalid Screen Detected! :: $screen")
        }
    }

    createUiFactory { state, modifier ->
        when (state) {
            is HomeUiState -> HomeComposableScreen(state, modifier)
        }
    }
    single {
        Circuit.Builder()
            .addUiFactories(getAll())
            .addPresenterFactories(getAll())
            .build()
    }
}