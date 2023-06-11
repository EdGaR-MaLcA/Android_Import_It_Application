package com.example.android.android_import_it_application.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "users")
class User (
    @PrimaryKey
    @SerializedName("id")
    var user_id: Int,
    @SerializedName("name")
    var name_user: String,
    @SerializedName("age")
    var age: String,
    @SerializedName("password")
    var password: String,
    @SerializedName("dni")
    var dni: String,
    @SerializedName("genre")
    var genre: String,
    @SerializedName("day")
    var day: String,
    @SerializedName("month")
    var month: String,
    @SerializedName("year")
    var year: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("phone")
    var phone: String,
    @SerializedName("address")
    var address: String
    ): Serializable