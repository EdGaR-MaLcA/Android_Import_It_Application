package com.example.android.android_import_it_application.controllers.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.android_import_it_application.R
import com.example.android.android_import_it_application.controllers.fragments.CustomerOrderFragment
import com.example.android.android_import_it_application.controllers.fragments.MyOrdersFragment

class MyOrdersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_orders)

        val transaction = supportFragmentManager.beginTransaction()

        transaction.add(R.id.flMyOrders, MyOrdersFragment()).commitAllowingStateLoss()
    }
}