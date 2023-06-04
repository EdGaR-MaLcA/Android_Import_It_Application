package com.example.android.android_import_it_application.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android.android_import_it_application.models.Order

@Database(entities = [Order::class], version = 1)
abstract class OrderDatabase : RoomDatabase(){
    abstract fun getOrderDAO(): OrderDao

    companion object{ //patron singleton
        private var INSTANCE : OrderDatabase? = null

        fun getInstance(context: Context): OrderDatabase{
            if (INSTANCE == null){
                //la bd no existe
                INSTANCE = Room
                    .databaseBuilder(context, OrderDatabase::class.java, "orders1.db")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE as OrderDatabase
        }
    }
}
