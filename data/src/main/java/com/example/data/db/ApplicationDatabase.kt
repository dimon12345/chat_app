package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.db.dao.UserDao
import com.example.data.entity.db.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}