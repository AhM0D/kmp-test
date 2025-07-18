package org.test.example

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import com.slack.circuit.overlay.ContentWithOverlays
import com.slack.circuitx.gesturenavigation.GestureNavigationDecorationFactory
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject
import org.test.example.di.createCircuit
import org.test.example.di.createDomainModule
import org.test.example.di.createRepositoryModule
import org.test.example.di.networkModuleConfig
import org.test.example.di.platformNetModule
import org.test.example.presentation.Screens

@Composable
@Preview
fun App() {
    KoinApplication(application = {
        modules(
            platformNetModule(),
            createCircuit(),
            createDomainModule(),
            createRepositoryModule(),
            networkModuleConfig("jsonplaceholder.typicode.com")
        )
    }) {
        val circuit = koinInject<Circuit>()
        val backStack = rememberSaveableBackStack(Screens.HomScreen)
        val navigator = rememberCircuitNavigator(backStack) {}
        ContentWithOverlays {
            CircuitCompositionLocals(circuit) {
                NavigableCircuitContent(
                    navigator = navigator,
                    backStack = backStack,
                    decoratorFactory =
                        remember(navigator) {
                            GestureNavigationDecorationFactory(onBackInvoked = navigator::pop)
                        },
                )
            }
        }
    }
}