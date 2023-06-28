package com.example.android.android_import_it_application.network

import com.example.android.android_import_it_application.models.*
import okhttp3.ResponseBody
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

    @GET("domesticShipment")
    fun getDomesticShipments(): Call<List<DomesticShipment>>

    @GET("travelerOrders")
    fun getMyOrders(): Call<List<MyOrder>>

    @POST("travelerOrders")
    fun createMyOrder(@Body order: Order): Call<Void>

    @POST("travelerOrders")
    fun createOrder(@Body myOrder: MyOrder): Call<Void> // Replace with your API endpoint and request model

    @GET("orders/{orderId}")
    fun getMyOrderById(@Path("orderId") orderId: String): Call<MyOrder>

    @DELETE("travelerOrders/{order_id}")
    fun deleteOrder(@Path("order_id") orderId: Int): Call<Void>

    @GET("users/{id}")
    fun getUserById(@Path("id") userId: String): Call<User>

    @GET("users")
    fun getUsers(): Call<List<User>>

    @POST("users")
    fun createUser(@Body user: User): Call<User>

    @PUT("users/{id}")
    fun updateUser(@Path("id") userId: Int, @Body user: User): Call<User>

    @POST("orders")
    fun placeOrder(@Body order: Order): Call<Void>

    @GET("staticmap")
    fun getStaticMap(
        @Query("center") center: String,
        @Query("zoom") zoom: Int,
        @Query("size") size: String,
        @Query("key") apiKey: String
    ): Call<ResponseBody>
}