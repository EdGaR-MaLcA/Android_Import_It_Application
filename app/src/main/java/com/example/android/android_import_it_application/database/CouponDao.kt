package com.example.android.android_import_it_application.database

import androidx.room.*
import com.example.android.android_import_it_application.models.Coupon

@Dao
interface CouponDao {
    @Query("SELECT * FROM coupons")
    fun getAllCoupons(): List<Coupon>

    @Insert
    fun insertCoupon(vararg coupon: Coupon)

    @Query("SELECT * FROM coupons WHERE code = :couponCode LIMIT 1")
    fun getCouponByCode(couponCode: String): Coupon?

    @Query("SELECT COUNT(*) FROM coupons")
    suspend fun getCouponsCount(): Int

    @Update
    fun updateCoupon(vararg coupon: Coupon)

    @Delete
    fun deleteCoupon(vararg coupon: Coupon)
}