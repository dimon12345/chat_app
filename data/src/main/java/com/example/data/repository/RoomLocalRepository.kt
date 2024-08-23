package com.example.data.repository

import com.example.data.db.ApplicationDatabase
import com.example.data.entity.db.UserEntity
import com.example.data.entity.db.toProfileEntity
import com.example.domain.data.ProfileData
import com.example.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomLocalRepository @Inject constructor(
    private val applicationDatabase: ApplicationDatabase,
): LocalRepository {
    override suspend fun addProfileData(profile: ProfileData) {
        applicationDatabase.userDao().insert(profile.toProfileEntity())
    }

    override fun getProfileData(userId: Int): Flow<ProfileData> {
        return applicationDatabase.userDao().get(userId).map { userEntity ->
            userEntity?.toProfileData() ?: ProfileData()
        }
    }
}