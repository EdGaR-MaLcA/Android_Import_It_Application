package com.example.android.android_import_it_application.database
import androidx.room.Dao
import androidx.room.Query
import com.example.android.android_import_it_application.models.User

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAllUsers(): List<User>
}
