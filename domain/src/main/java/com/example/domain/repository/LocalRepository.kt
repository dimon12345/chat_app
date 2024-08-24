package com.example.domain.repository

import com.example.domain.data.ProfileData
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    suspend fun updateProfileData(profile: ProfileData)
    fun getProfileData(userId: Int) : Flow<ProfileData>
}