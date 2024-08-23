package com.example.data.repository

import com.example.data.data_source.DataStoreSource
import com.example.domain.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PreferencesDataStoreUserPreferencesRepository @Inject constructor(
    private val dataStoreSource: DataStoreSource,
) : UserPreferencesRepository {
    override suspend fun setString(key: String, value: String) = dataStoreSource.setString(key, value)
    override fun getString(key: String): Flow<String?> = dataStoreSource.getString(key)

    override suspend fun setLong(key: String, value: Long) = dataStoreSource.setLong(key, value)
    override fun getLong(key: String): Flow<Long?> = dataStoreSource.getLong(key)
}