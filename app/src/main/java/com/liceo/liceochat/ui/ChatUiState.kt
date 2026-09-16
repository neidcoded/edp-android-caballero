package com.liceo.liceochat.ui

import com.liceo.liceochat.domain.Message

sealed interface ChatUiState { // GIVEN (read it, do not change it)
    data object Loading : ChatUiState
    data object Empty : ChatUiState
    data class Ready(val messages: List<Message>) : ChatUiState
    data class Error(val message: String) : ChatUiState
}
