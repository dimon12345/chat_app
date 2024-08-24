package com.example.domain.chats

data class ChatListPageItem(
    val chatId: Int,
    val title: String,
    val lastMessage: String,
    val unreadMessages: Int,
)