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
    var age: Int,
    @SerializedName("password")
    var password: String,
    @SerializedName("dni")
    var dni: Int,
    @SerializedName("genre")
    var genre: String,
    @SerializedName("day")
    var day: Int,
    @SerializedName("month")
    var month: Int,
    @SerializedName("year")
    var year: Int,
    @SerializedName("email")
    var email: String,
    @SerializedName("phone")
    var phone: String,
    @SerializedName("address")
    var address: String
    ): Serializable