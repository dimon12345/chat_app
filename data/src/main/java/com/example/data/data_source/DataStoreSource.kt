package com.example.data.data_source

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreSource @Inject constructor(
    private val dataStorePreferences: DataStore<Preferences>
) {
    suspend fun setString(key: String, value: String) {
        dataStorePreferences.edit { preferences ->
            preferences[stringPreferencesKey(key)] = value
        }
    }

    fun getString(key: String): Flow<String?> {
        try {
            return dataStorePreferences.data.map { preferences ->
                preferences[stringPreferencesKey(key)]
            }
        } catch (_: Exception) {
        }

        assert(false, { "IO Error" })
        return flow { emit(null)}
    }

    suspend fun setInt(key: String, value: Int) {
        dataStorePreferences.edit { preferences ->
            preferences[intPreferencesKey(key)] = value
        }
    }

    fun getInt(key: String): Flow<Int?> {
        try {
            return dataStorePreferences.data.map { preferences ->
                preferences[intPreferencesKey(key)]
            }
        } catch (_: Exception) {
        }

        assert(false, { "IO Error" })
        return flow { emit(null)}
    }
}