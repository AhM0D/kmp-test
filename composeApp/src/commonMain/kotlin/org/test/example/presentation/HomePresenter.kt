package org.test.example.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import org.test.example.domain.GetHomeListUseCase

class HomePresenter(
    private val navigator: Navigator,
    private val getHomeListUseCase: GetHomeListUseCase
) : Presenter<HomeUiState> {
    @Composable
    override fun present(): HomeUiState {
        var retry by remember { mutableStateOf(false) }
        var error by remember { mutableStateOf<Throwable?>(null) }
        val postList by produceState<ImmutableList<UiPostItem>?>(null, retry) {
            error = null
            value = null
            val data = getHomeListUseCase.invoke()
            error = data.exceptionOrNull()
            value = data.getOrNull()?.toImmutableList()
        }
        val isLoading by remember(
            error,
            postList
        ) { mutableStateOf(error == null && postList == null) }

        return HomeUiState(items = postList, isLoading = isLoading, error = error) { event ->
            when (event) {
                HomeUiEvent.Retry -> retry = !retry
            }
        }
    }

}