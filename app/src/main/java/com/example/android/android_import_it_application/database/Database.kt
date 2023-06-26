package com.example.android.android_import_it_application.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.android.android_import_it_application.models.User

@Database(entities = [User::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
