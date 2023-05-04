package com.example.android.android_import_it_application

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_traveler)

        val imgOrder = findViewById<ImageView>(R.id.ivCustomerOrder)

        imgOrder.setOnClickListener {
            val intent: Intent = Intent(this, OrderActivity::class.java)
            startActivity(intent)
        }
    }

}