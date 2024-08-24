package com.example.presentation.ui.home.chat_list

import com.example.domain.chats.ChatListPageItem

data class ChatListPageState(
    val chats: List<ChatListPageItem> = listOf(),
)
