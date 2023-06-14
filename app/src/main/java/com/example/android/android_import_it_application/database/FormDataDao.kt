package com.example.android.android_import_it_application.database

import com.example.android.android_import_it_application.database.FormData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FormDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(formData: FormData)

    @Query("SELECT * FROM form_data")
    suspend fun getAllFormData(): List<FormData>
}
