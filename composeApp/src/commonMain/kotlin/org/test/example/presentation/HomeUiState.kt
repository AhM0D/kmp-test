package org.test.example.presentation

import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import kotlinx.collections.immutable.ImmutableList

data class HomeUiState(
    val items: ImmutableList<UiPostItem>?,
    val isLoading: Boolean = true,
    val error: Throwable? = null,
    val eventSink: (HomeUiEvent) -> Unit
) : CircuitUiState

data class UiPostItem(val userId: Int, val id: Int, val title: String, val body: String)

sealed class HomeUiEvent : CircuitUiEvent {
    data object Retry: HomeUiEvent()
}