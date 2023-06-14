package com.example.android.android_import_it_application.controllers.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.android.android_import_it_application.R

class BuyerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard_buyer)

        val ibDomestic = findViewById<ImageButton>(R.id.ibDomestic)
        ibDomestic.setOnClickListener {
            val intent: Intent = Intent(this, SeeProductActivity::class.java)
            startActivity(intent)
        }

        val ibCoupon = findViewById<ImageButton>(R.id.ibCoupons)
        ibCoupon.setOnClickListener{
            val intent: Intent = Intent(this, CouponActivity::class.java)
            startActivity(intent)
        }

        val ibChats = findViewById<ImageButton>(R.id.ibChats)
        ibChats.setOnClickListener{
            val intent: Intent = Intent(this, ChatBuyerActivity::class.java)
            startActivity(intent)
        }

    }
}