package org.test.example.di

import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import org.koin.core.module.Module
import org.koin.core.scope.Scope

class PresenterFactory<PRESENTER : Presenter<*>>(
    private val presenterFactory: (Navigator, Screen) -> PRESENTER
) : Presenter.Factory {
    override fun create(
        screen: Screen,
        navigator: Navigator,
        context: CircuitContext
    ): Presenter<*> {
        return presenterFactory(navigator, screen)
    }
}

fun Module.createPresenterFactory(presenter: Scope.(Navigator, Screen) -> Presenter<*>) {
    factory<Presenter.Factory> {
        PresenterFactory { navigator, screen ->
            presenter(navigator, screen)
        }
    }
}