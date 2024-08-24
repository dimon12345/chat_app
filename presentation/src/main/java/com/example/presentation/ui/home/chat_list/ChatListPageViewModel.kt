package com.example.presentation.ui.home.chat_list

import com.example.domain.chats.GetChatListUseCase
import com.example.presentation.ui.home.HomeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ChatListPageViewModel @Inject constructor(
    private val getChatListUseCase: GetChatListUseCase,
) : HomeViewModel() {
    private val _uiState = MutableStateFlow(ChatListPageState())
    val uiState = _uiState.asStateFlow()

    init{
        _uiState.value = _uiState.value.copy(
            chats = getChatListUseCase()
        )
    }
}