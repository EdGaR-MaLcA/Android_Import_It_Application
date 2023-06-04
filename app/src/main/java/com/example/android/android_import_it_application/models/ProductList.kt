package com.example.android.android_import_it_application.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
class ProductList (
    @PrimaryKey
    @SerializedName("idProductList")
    var product_list_id: Int,

    @SerializedName("name")
    var name: String,

    @SerializedName("imgURL")
    var imgURL: String,

    @SerializedName("price")
    var price: String
): Serializable