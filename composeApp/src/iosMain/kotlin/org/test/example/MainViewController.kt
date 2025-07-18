package org.test.example

import androidx.compose.ui.window.ComposeUIViewController
import org.koin.compose.KoinApplication
import org.test.example.di.platformNetModule

fun MainViewController() = ComposeUIViewController {
    KoinApplication(application = {
        modules(
            platformNetModule(),
        )
    }) {
        App()
    }
}