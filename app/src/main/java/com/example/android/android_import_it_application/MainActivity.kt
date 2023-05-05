package com.example.android.android_import_it_application

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_traveler)

        val imgOrder = findViewById<ImageView>(R.id.ivCustomerOrder)
        val tvCustomerOrder = findViewById<TextView>(R.id.tvCustomerOrder)
        val ivWallet = findViewById<ImageView>(R.id.ivWallet)
        val tvWallet = findViewById<TextView>(R.id.tvWallet)
        //val tvBackWallet = findViewById<TextView>(R.id.textView40)

        imgOrder.setOnClickListener {
            val intent: Intent = Intent(this, OrderActivity::class.java)
            startActivity(intent)
        }

        tvCustomerOrder.setOnClickListener {
            val intent: Intent = Intent(this, OrderActivity::class.java)
            startActivity(intent)
        }



        tvWallet.setOnClickListener {
            val intent: Intent = Intent(this, WalletActivity::class.java);
            startActivity(intent)
        }

        ivWallet.setOnClickListener {
            val intent: Intent = Intent(this, WalletActivity::class.java);
            startActivity(intent)
        }

        /*tvBackWallet.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }*/
    }

}