package com.example.android.android_import_it_application.controllers.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.android.android_import_it_application.R

class PaymentActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.payment)

        val ibArrowP1 = findViewById<ImageButton>(R.id.ibArrowP1)
        val bAddTarget = findViewById<Button>(R.id.bAddTarget)
        val ibMasterCard = findViewById<ImageButton>(R.id.ibMasterCard)
        val ibVisa = findViewById<ImageButton>(R.id.ibVisa)

        ibArrowP1.setOnClickListener {
            val intent: Intent = Intent(this, DescriptionItemActivity::class.java)
            startActivity(intent)
        }

        bAddTarget.setOnClickListener {
            val intent: Intent = Intent(this, Payment2Activity::class.java)
            startActivity(intent)
        }

        ibMasterCard.setOnClickListener {
            val intent: Intent = Intent(this, Payment3Activity::class.java)
            startActivity(intent)
        }

        ibVisa.setOnClickListener {
            val intent: Intent = Intent(this, Payment3Activity::class.java)
            startActivity(intent)
        }
    }
}