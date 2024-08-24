package com.example.domain.chats

import javax.inject.Inject

class GetChatListUseCase @Inject constructor(
) {
    operator fun invoke() : List<ChatListPageItem> {
        return listOf(
            ChatListPageItem(77,"Первый", "Сегодня вечером. Трудовая книжка звёзд", 0),
            ChatListPageItem(54, "Россия 1", "Мама может", 7),
            ChatListPageItem(31, "Матч!", "Футбол. МИР Российская Премьер-Лига. Прямая трансляция. \"Зенит\" (Санкт-Петербург) - \"Спартак\" (Москва)", 4),
            ChatListPageItem(8, "НТВ", "Секрет на миллион", 1),
            ChatListPageItem(85, "Пятый канал", "Контуженный. \"Холод\", 2-я серия", 8),
            ChatListPageItem(62, "Культура", "Доброе утро!", 5),
            ChatListPageItem(39, "ТВ Центр", "Доброе утро!", 2),
            ChatListPageItem(16, "РЕН ТВ", "Доброе утро!", 9),
            ChatListPageItem(93, "СТС", "Доброе утро!", 6),
            ChatListPageItem(70, "Домашний", "Доброе утро!", 3),
            ChatListPageItem(47, "ТВ-3", "Доброе утро!", 0),
            ChatListPageItem(24, "Пятница", "Доброе утро!", 0),
            ChatListPageItem(1, "Звезда", "Доброе утро!", 0),
            ChatListPageItem(78, "МИР", "Доброе утро!", 0),
            ChatListPageItem(55, "ТНТ", "Доброе утро!", 0),
            ChatListPageItem(32, "Че!", "Доброе утро!", 0),
        )
    }
}