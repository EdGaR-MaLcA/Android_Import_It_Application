package com.example.android.android_import_it_application.controllers.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.android.android_import_it_application.R
import com.example.android.android_import_it_application.controllers.fragments.DomesticFragment
import com.example.android.android_import_it_application.controllers.fragments.OrdersFragment

class DomesticActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_domestic)

        val dni = intent.getStringExtra("DNI")
        val ibArrow = findViewById<ImageButton>(R.id.ibArrowDomestic)
        val transaction = supportFragmentManager.beginTransaction()
        val domesticFragment = DomesticFragment()
        val bundle = Bundle()
        bundle.putString("DNI", dni)
        domesticFragment.arguments = bundle


        transaction.add(R.id.flFragmentDomestic, domesticFragment).commitAllowingStateLoss()

        ibArrow.setOnClickListener {
            val intent: Intent = Intent(this, BuyerActivity::class.java)
            startActivity(intent)
        }
    }

}