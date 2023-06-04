package com.example.android.android_import_it_application.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "my_orders")
class MyOrder (
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    var order_id: Int,

    @SerializedName("name")
    var name: String,

    @SerializedName("amount")
    var amount: String,

    @SerializedName("comision")
    var comision: String,

    @SerializedName("dni")
    var dni: String,

    @SerializedName("price")
    var price: String,

    @SerializedName("status")
    var status: String,

    @SerializedName("tittle")
    var tittle: String,

    @SerializedName("url")
    var url: String,

    @SerializedName("weight")
    var weight: String
): Serializable