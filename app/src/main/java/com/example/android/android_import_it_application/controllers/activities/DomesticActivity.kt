package com.example.android.android_import_it_application.controllers.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.android.android_import_it_application.R
import com.example.android.android_import_it_application.controllers.fragments.DomesticFragment

class DomesticActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_domestic)

        val ibArrow = findViewById<ImageButton>(R.id.ibArrowDomestic)
        val transaction = supportFragmentManager.beginTransaction()

        transaction.add(R.id.flFragmentDomestic, DomesticFragment()).commitAllowingStateLoss()

        ibArrow.setOnClickListener {
            val intent: Intent = Intent(this, BuyerActivity::class.java)
            startActivity(intent)
        }
    }

}