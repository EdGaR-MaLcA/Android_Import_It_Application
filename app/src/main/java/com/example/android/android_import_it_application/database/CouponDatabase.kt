package com.example.android.android_import_it_application.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android.android_import_it_application.models.Coupon

@Database(entities = [Coupon::class], version = 1)
abstract class CouponDatabase : RoomDatabase(){

    abstract fun getCouponDAO(): CouponDao

    companion object{ //patron singleton
        private var INSTANCE : CouponDatabase? = null

        fun getInstance(context: Context): CouponDatabase{
            if (INSTANCE == null){
                //la bd no existe
                INSTANCE = Room
                    .databaseBuilder(context, CouponDatabase::class.java, "coupons.db")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE as CouponDatabase
        }
    }
}