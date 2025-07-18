package org.test.example.presentation

import androidx.compose.runtime.Composable
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter

class HomePresenter(
    private val navigator: Navigator,
) : Presenter<HomeUiState> {
    @Composable
    override fun present(): HomeUiState {
        return HomeUiState { event ->
            when(event) {
                else -> {}
            }
        }
    }

}