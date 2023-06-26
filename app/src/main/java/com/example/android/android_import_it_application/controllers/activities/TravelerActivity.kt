package com.example.android.android_import_it_application.controllers.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.android.android_import_it_application.R

class TravelerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_traveler)

        val user = intent.getSerializableExtra("User")
        val role = intent.getStringExtra("role")

        val ivCustomerOrder = findViewById<ImageView>(R.id.ivCustomerOrder)
        ivCustomerOrder.setOnClickListener {
            val intent: Intent = Intent(this, CustomerOrderActivity::class.java)
            startActivity(intent)
        }

        val ivWallet = findViewById<ImageView>(R.id.ivWallet)
        ivWallet.setOnClickListener {
            val intent: Intent = Intent(this, WalletActivity::class.java)
            startActivity(intent)
        }

        val ivMyOrders = findViewById<ImageView>(R.id.ivMyOrders)
        ivMyOrders.setOnClickListener {
            val intent: Intent = Intent(this, MyOrdersActivity::class.java)
            startActivity(intent)
        }

        val ivProfile = findViewById<ImageView>(R.id.ivProfile)
        ivProfile.setOnClickListener {
            val intent: Intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("User", user)
            intent.putExtra("role", role)
            startActivity(intent)
        }
    }






}