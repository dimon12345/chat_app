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

    override suspend fun setInt(key: String, value: Int) = dataStoreSource.setInt(key, value)
    override fun getInt(key: String): Flow<Int?> = dataStoreSource.getInt(key)
}