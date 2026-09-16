package com.liceo.liceochat.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class MessageDto(
    val id: String? = null,
    val sender: String? = null,
    val text: String? = null,
    val createdAt: Long? = null
)

@Serializable
data class NewMessageDto( // GIVEN (read it, do not change it)
    val sender: String, // no id here — the server makes it
    val text: String,
    val createdAt: Long
)
