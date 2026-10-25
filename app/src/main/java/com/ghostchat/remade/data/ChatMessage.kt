package com.ghostchat.remade.data

import java.util.UUID

enum class MessageType { TEXT, VOICE }

enum class MessageStatus { SENDING, SENT, DELIVERED, READ }

data class ChatMessage(
    val id: String = UUID.randomUUID().toString(),
    val conversationId: String,
    val senderId: String,            // stable user id (single profile per person)
    val senderDisplayName: String,
    val text: String = "",
    val voiceUri: String? = null,
    val voiceDurationMs: Long = 0L,
    val timestamp: Long = System.currentTimeMillis(),
    val isOutgoing: Boolean = false,
    val status: MessageStatus = MessageStatus.SENT,
    val isBurned: Boolean = false,
    val burnSeconds: Int = 0
)
