package com.example.android.android_import_it_application

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class WalletActivity: AppCompatActivity() {
    //@SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.wallet_traveler)

        //val tvBackOrders = findViewById<TextView>(R.id.textView17)

        //tvBackOrders.setOnClickListener {
        //    val intent: Intent = Intent(this, MainActivity::class.java)
        //    startActivity(intent)
        //}

    }
}