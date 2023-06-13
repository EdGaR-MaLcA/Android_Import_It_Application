package com.example.android.android_import_it_application.network

import com.example.android.android_import_it_application.models.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ImportItService {

    @GET("coupon")
    fun getCoupons(): Call<List<Coupon>>

    @GET("orders")
    fun getOrders(): Call<List<Order>>

    @GET("productList")
    fun getProductLists(): Call<List<ProductList>>

    @GET("travelerOrders")
    fun getMyOrders(): Call<List<MyOrder>>

    @POST("travelerOrders")
    fun createOrder(@Body myOrder: MyOrder): Call<Void> // Replace with your API endpoint and request model


    @DELETE("travelerOrders/{order_id}")
    fun deleteOrder(@Path("order_id") orderId: Int): Call<Void>

    @GET("orders/{orderId}")
    fun getMyOrderById(@Path("orderId") orderId: String): Call<MyOrder>
}