package com.example.android.android_import_it_application.models

import com.google.gson.annotations.SerializedName

class ApiResponseDetails (
    var coupons: List<Coupon>,
    var productList: List<ProductList>,
    var domesticShipment: List<DomesticShipment>

    )