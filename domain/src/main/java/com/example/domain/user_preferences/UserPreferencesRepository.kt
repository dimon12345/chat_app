package com.example.domain.user_preferences

import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {
    suspend fun setString(key: String, value: String)
    fun getString(key: String): Flow<String?>

    suspend fun setLong(keh: String, value: Long)
    fun getLong(keh: String): Flow<Long?>

    companion object {
        const val PHONE_NUMBER_KEY = "phone_number"
        const val REFRESH_TOKEN_KEY = "refresh_token"
        const val ACCESS_TOKEN_KEY = "access_token"
        const val USER_ID_KEY = "user_id"
    }
}