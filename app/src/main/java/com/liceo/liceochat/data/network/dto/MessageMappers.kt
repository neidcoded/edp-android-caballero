package com.liceo.liceochat.data.network.dto

import com.liceo.liceochat.domain.Message
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.longOrNull

fun MessageDto.toDomain(): Message = Message(
    id = id ?: "",
    sender = sender ?: "Unknown",
    text = text ?: "",
    createdAt = try {
        createdAt?.jsonPrimitive?.longOrNull ?: 0L
    } catch (e: Exception) {
        0L
    }
)

fun List<MessageDto>.toDomain(): List<Message> =
    map { it.toDomain() }
