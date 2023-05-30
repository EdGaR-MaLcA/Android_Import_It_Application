package com.example.android.android_import_it_application.network

import com.example.android.android_import_it_application.models.ApiResponseDetails
import com.example.android.android_import_it_application.models.Coupon
import com.example.android.android_import_it_application.models.Order
import com.example.android.android_import_it_application.models.ProductList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface ImportItService {
    @GET("coupon")
    fun getCoupons(): Call<List<Coupon>>

    @GET("productList")
    fun getProductLists(): Call<List<ProductList>>

    @GET("orders")
    fun getOrders(): Call<List<Order>>

    /*@GET("travelerOrders")
    fun getMyOrders(): Call<List<Order>>

    @POST("")
    fun postMyOrders()*/
}