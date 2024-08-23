package com.example.data.entity.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.data.ProfileData

@Entity(tableName = "users")
data class UserEntity (
    @PrimaryKey
    @ColumnInfo(name = "id")
    val userId: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "username")
    val username: String,

    @ColumnInfo(name = "birthday")
    val birthday: String,

    @ColumnInfo(name = "city")
    val city: String,

    @ColumnInfo(name = "avatar")
    val avatar: String,

    @ColumnInfo(name = "status")
    val status: String,

    @ColumnInfo(name = "phone")
    val phone: String,
) {
    fun toProfileData(): ProfileData {
        return ProfileData(
            userId = userId,
            name = name,
            username = username,
            birthday = birthday,
            city = city,
            avatar = avatar,
            status = status,
            phone = phone,
        )
    }
}

fun ProfileData.toProfileEntity(): UserEntity {
    return UserEntity(
        userId = userId,
        name = name,
        username = username,
        birthday = birthday,
        city = city,
        avatar = avatar,
        status = status,
        phone = phone,
    )
}
