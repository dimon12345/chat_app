package com.example.domain.repository

import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {
    suspend fun setString(key: String, value: String)
    fun getString(key: String): Flow<String?>

    suspend fun setInt(key: String, value: Int)
    fun getInt(key: String): Flow<Int?>

    companion object {
        const val PHONE_NUMBER_KEY = "phone_number"
        const val REFRESH_TOKEN_KEY = "refresh_token"
        const val ACCESS_TOKEN_KEY = "access_token"
        const val USER_ID_KEY = "user_id"
    }
}