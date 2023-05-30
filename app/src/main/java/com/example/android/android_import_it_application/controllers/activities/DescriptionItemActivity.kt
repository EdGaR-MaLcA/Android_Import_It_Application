package com.example.android.android_import_it_application.controllers.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.android.android_import_it_application.R

class DescriptionItemActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.description_item)

        val ibArrowD = findViewById<ImageButton>(R.id.ibArrowD)
        val bProceedPay = findViewById<Button>(R.id.bProceedPay)

        ibArrowD.setOnClickListener {
            val intent: Intent = Intent(this, SeeProductActivity::class.java)
            startActivity(intent)
        }

        bProceedPay.setOnClickListener {
            val intent: Intent = Intent(this, PaymentActivity::class.java)
            startActivity(intent)
        }
    }
}