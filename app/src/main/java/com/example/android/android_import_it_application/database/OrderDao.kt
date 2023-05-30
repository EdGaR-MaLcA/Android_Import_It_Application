package com.example.android.android_import_it_application.database

import androidx.room.*
import com.example.android.android_import_it_application.models.Order

@Dao
interface OrderDao {
    @Query("SELECT * FROM orders")
    fun getAllOrders(): List<Order>

    @Insert
    fun insertOrder(vararg order: Order)

    @Update
    fun updateOrder(vararg order: Order)

    @Delete
    fun deleteOrder(vararg order: Order)
}