package com.example.presentation.ui.home.chat_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.domain.chats.ChatListPageItem
import com.example.presentation.R
import com.example.presentation.ui.common.TopBar
import com.example.presentation.ui.home.HomePageViewModel

@Composable
fun ChatListPage(
    homePageViewModel: HomePageViewModel = viewModel(),
    chatListPageViewModel: ChatListPageViewModel = viewModel(),
) {
    val state = chatListPageViewModel.uiState.collectAsState().value
    Column {
        TopBar(
            leftImage = R.drawable.ic_profile,
            onLeftButtonTap = { homePageViewModel.onProfile() }
        )
        LazyColumn{
            items(
                count = state.chats.size,
                key = { state.chats[it].chatId },
                itemContent = { index ->
                    val chatItem = state.chats[index]
                    ChatListItem(chatItem)
                },
            )
        }
    }
}

@Composable
private fun ChatListItem(chatItem: ChatListPageItem) {
    Box(
        modifier = Modifier
            .height(dimensionResource(id = R.dimen.chat_list_item_height))
            .fillMaxWidth(1f)
            .border(
                1.dp,
                colorResource(id = R.color.chat_list_item_border)
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_avatar_default),
                contentDescription = null,
            )
            
            Spacer(Modifier.width(dimensionResource(id = R.dimen.default_padding)))

            Column {
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.half_padding)))
                Text(
                    text = chatItem.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = chatItem.lastMessage,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }

        if (chatItem.unreadMessages > 0) {
            Text(
                text = "${chatItem.unreadMessages}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.default_padding))
                    .align(Alignment.TopEnd)
                    .drawBehind {
                        drawCircle(
                            color = Color.Red,
                            radius = this.size.maxDimension * .7f,
                        )
                    }
            )
        }
    }
}
