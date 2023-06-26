package com.example.android.android_import_it_application.controllers.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.android.android_import_it_application.R

class BuyerDirectionActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.buyer_direction)

        val addAddressButton: Button = findViewById(R.id.btnagregate)
        val loadAddressButton: Button = findViewById(R.id.btnload)
        val ibBackDirectionsP: ImageButton = findViewById(R.id.ibBackDirectionsP)

        ibBackDirectionsP.setOnClickListener {
            val intent: Intent = Intent(this, BuyerActivity::class.java)
            startActivity(intent)
        }

        addAddressButton.setOnClickListener {
            val intent: Intent = Intent(this, DirectionFormAgregateBuyerActivity::class.java)
            startActivity(intent)
        }

        loadAddressButton.setOnClickListener {
            val intent: Intent = Intent(this, DirectionLoadActivity::class.java)
            startActivity(intent)
        }
    }
}