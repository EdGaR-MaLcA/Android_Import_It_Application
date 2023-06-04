package com.example.android.android_import_it_application.controllers.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.android.android_import_it_application.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btBuyer = findViewById<Button>(R.id.btBuyer)
        val btTraveler = findViewById<Button>(R.id.btTraveler)

        btBuyer.setOnClickListener{
            val intent: Intent = Intent(this, BuyerActivity::class.java)
            startActivity(intent)
        }

        btTraveler.setOnClickListener{
            val intent: Intent = Intent(this, TravelerActivity::class.java)
            startActivity(intent)
        }

    }

}