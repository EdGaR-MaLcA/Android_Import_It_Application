package com.example.android.android_import_it_application.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "form_data")
data class FormData(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val departament: String,
    val direction: String,
    val district: String,
    val dni: String,
    val lastname: String,
    val name: String,
    val phone: String,
    val province: String
)
