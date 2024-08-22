package com.example.presentation.ui.home

data class HomePageState(
    val profileVisible: Boolean = false,
    val contentType: HomePageContentType = HomePageContentType.CHAT_LIST,
)