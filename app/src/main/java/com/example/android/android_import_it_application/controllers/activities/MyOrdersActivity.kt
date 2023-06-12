package com.example.android.android_import_it_application.controllers.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.android.android_import_it_application.R
import com.example.android.android_import_it_application.controllers.fragments.CustomerOrderFragment
import com.example.android.android_import_it_application.controllers.fragments.MyOrdersFragment

class MyOrdersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_orders)

        val transaction = supportFragmentManager.beginTransaction()
        val ibBackMyOrders = findViewById<ImageButton>(R.id.ibBackMyOrders)

        transaction.add(R.id.flMyOrders, MyOrdersFragment()).commitAllowingStateLoss()

        ibBackMyOrders.setOnClickListener {
            val intent: Intent = Intent(this, TravelerActivity::class.java)
            startActivity(intent)
        }
    }
}