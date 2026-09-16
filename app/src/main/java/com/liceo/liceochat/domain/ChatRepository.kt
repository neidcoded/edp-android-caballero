package com.liceo.liceochat.domain

import com.liceo.liceochat.core.AppResult

interface ChatRepository { // GIVEN (read it, do not change it)
    suspend fun getMessages(): AppResult<List<Message>>
    suspend fun sendMessage(sender: String, text: String): AppResult<Unit>
}
