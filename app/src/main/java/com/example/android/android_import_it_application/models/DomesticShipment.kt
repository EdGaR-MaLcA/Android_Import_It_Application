package com.example.android.android_import_it_application.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "domestic_shipments")
class DomesticShipment (
    @PrimaryKey
    @SerializedName("idDomesticShipment")
    var domestic_shipment_id: Int,

    @SerializedName("imageProduct")
    var image_product: String,

    @SerializedName("productName")
    var product_name: String,

    @SerializedName("userId")
    var userId: String,

    @SerializedName("date")
    var date: String,

    @SerializedName("departureTime")
    var departure_time: String,

    @SerializedName("shippingDate")
    var shipping_date: String,

    @SerializedName("shippingManager")
    var shipping_manager: String,

    @SerializedName("totalCost")
    var total_cost: String,

    @SerializedName("trackingCode")
    var tracking_code: String,

    @SerializedName("destiny")
    var destiny: String
): Serializable