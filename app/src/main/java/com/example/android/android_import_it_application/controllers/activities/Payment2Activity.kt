package com.example.android.android_import_it_application.controllers.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.android.android_import_it_application.R

class Payment2Activity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.payment2)

        val ibArrow3 = findViewById<ImageButton>(R.id.ibArrow3)
        val bAddTarget2 = findViewById<Button>(R.id.bAddTarget2)

        ibArrow3.setOnClickListener {
            val intent: Intent = Intent(this, PaymentActivity::class.java)
            startActivity(intent)
        }
        bAddTarget2.setOnClickListener {
            val intent: Intent = Intent(this, Payment3Activity::class.java)
            startActivity(intent)
        }

    }
}