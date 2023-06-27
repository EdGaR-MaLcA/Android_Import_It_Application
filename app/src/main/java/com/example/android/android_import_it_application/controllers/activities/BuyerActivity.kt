package com.example.android.android_import_it_application.controllers.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.android.android_import_it_application.R

class BuyerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard_buyer)

        val dni = intent.getStringExtra("DNI")
        Log.d("DNI", "DNI obtenido: $dni")
        val user = intent.getSerializableExtra("User")
        val role = intent.getStringExtra("role")

        val ibFasty = findViewById<ImageButton>(R.id.ibFasty)
        ibFasty.setOnClickListener {
            val intent: Intent = Intent(this, SeeProductActivity::class.java)
            intent.putExtra("DNI", dni)
            intent.putExtra("User", user)
            intent.putExtra("role", role)
            startActivity(intent)
        }

        val ibCoupon = findViewById<ImageButton>(R.id.ibCoupons)
        ibCoupon.setOnClickListener{
            val intent: Intent = Intent(this, CouponActivity::class.java)
            intent.putExtra("DNI", dni)
            intent.putExtra("User", user)
            intent.putExtra("role", role)
            startActivity(intent)
        }

        val ibDomestic = findViewById<ImageButton>(R.id.ibDomestic)
        ibDomestic.setOnClickListener {
            val intent: Intent = Intent(this, DomesticActivity::class.java)
            intent.putExtra("DNI", dni)
            intent.putExtra("User", user)
            intent.putExtra("role", role)
            startActivity(intent)
        }

        val ibCalculator = findViewById<ImageButton>(R.id.ibCalculator)
        ibCalculator.setOnClickListener {
            val intent: Intent = Intent(this, CalculatorActivity::class.java)
            intent.putExtra("DNI", dni)
            intent.putExtra("User", user)
            intent.putExtra("role", role)
            startActivity(intent)
        }

        val ibPerfil = findViewById<ImageButton>(R.id.ibPerfil)
        ibPerfil.setOnClickListener {
            val intent: Intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("DNI", dni)
            intent.putExtra("User", user)
            intent.putExtra("role", role)
            startActivity(intent)
        }

        val ibOrders = findViewById<ImageButton>(R.id.ibOrders)
        ibOrders.setOnClickListener {
            val intent: Intent = Intent(this, ShowOrdersActivity::class.java)
            intent.putExtra("DNI", dni)
            intent.putExtra("User", user)
            intent.putExtra("role", role)
            startActivity(intent)
        }

        val ibPlaceOrder = findViewById<ImageButton>(R.id.ibPlaceOrder)
        ibPlaceOrder.setOnClickListener {
            val intent: Intent = Intent(this, PlaceOrderForm::class.java)
            intent.putExtra("DNI", dni)
            intent.putExtra("User", user)
            intent.putExtra("role", role)
            startActivity(intent)
        }

        val ibChats = findViewById<ImageButton>(R.id.ibChats)
        ibChats.setOnClickListener{
            val intent: Intent = Intent(this, ChatBuyerActivity::class.java)
            intent.putExtra("DNI", dni)
            intent.putExtra("User", user)
            intent.putExtra("role", role)
            startActivity(intent)
        }

        val ibDirection = findViewById<ImageButton>(R.id.ibDirections)
        ibDirection.setOnClickListener{
            val intent: Intent = Intent(this, BuyerDirectionActivity::class.java)
            intent.putExtra("DNI", dni)
            intent.putExtra("User", user)
            intent.putExtra("role", role)
            startActivity(intent)
        }

    }
}