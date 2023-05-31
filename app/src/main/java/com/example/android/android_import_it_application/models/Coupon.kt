package com.example.android.android_import_it_application.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "coupons")
class Coupon (
    @PrimaryKey
    @SerializedName("idCoupon")
    var coupon_id: Int,
    @SerializedName("title")
    var title: String,
    @SerializedName("discount")
    var discount: String,
    @SerializedName("code")
    var code: String,
    @SerializedName("validDate")
    var validationDate: String,
    @SerializedName("situation")
    var situation: String,
    @SerializedName("description")
    var description: String,
): Serializable