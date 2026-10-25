package com.ghostchat.remade.data

data class ChatConversation(
    val id: String,
    val title: String,
    val isGroup: Boolean = false,
    val participantIds: List<String>,   // deduped stable ids
    val lastMessageText: String = "",
    val lastMessageTimestamp: Long = 0L,
    val unreadCount: Int = 0
)
