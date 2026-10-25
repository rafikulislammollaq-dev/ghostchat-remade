package com.ghostchat.remade

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ghostchat.remade.data.ChatMessage
import com.ghostchat.remade.data.MessageType
import kotlinx.coroutines.launch

@Composable
fun ChatScreen() {
    // sample messages; in real app load from Room
    val messages = remember {
        mutableStateListOf(
            ChatMessage(conversationId = "c1", senderId = "u2", senderDisplayName = "Alice", text = "Hey!"),
            ChatMessage(conversationId = "c1", senderId = "u1", senderDisplayName = "Me", text = "Hi Alice", isOutgoing = true),
            ChatMessage(conversationId = "c1", senderId = "u2", senderDisplayName = "Alice", text = "How are you?"),
        )
    }
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    // Open at the END (latest message), like WhatsApp — not from the top.
    LaunchedEffect(Unit) {
        if (messages.isNotEmpty()) {
            listState.scrollToItem(messages.lastIndex)
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Alice") }) },
        bottomBar = {
            Row(Modifier.fillMaxWidth().padding(8.dp)) {
                var text by remember { mutableStateOf("") }
                TextField(value = text, onValueChange = { text = it }, modifier = Modifier.weight(1f), placeholder = { Text("Message") })
                Spacer(Modifier.width(8.dp))
                Button(onClick = {
                    if (text.isNotBlank()) {
                        messages.add(ChatMessage(conversationId = "c1", senderId = "u1", senderDisplayName = "Me", text = text, isOutgoing = true))
                        text = ""
                        scope.launch { listState.animateScrollToItem(messages.lastIndex) }
                    }
                }) { Text("Send") }
                // Voice button only (no photo sending)
                Spacer(Modifier.width(4.dp))
                Button(onClick = { /* start voice recording */ }) { Text("Mic") }
            }
        }
    ) { padding ->
        LazyColumn(state = listState, modifier = Modifier.fillMaxSize().padding(padding)) {
            items(messages, key = { it.id }) { m ->
                Text(
                    text = (if (m.isOutgoing) "Me: " else "${m.senderDisplayName}: ") + m.text,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}
