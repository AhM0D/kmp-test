package org.test.example.presentation

import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState

data class HomeUiState(
    val eventSink: (HomeUiEvent) -> Unit
) : CircuitUiState

sealed class HomeUiEvent : CircuitUiEvent {
}