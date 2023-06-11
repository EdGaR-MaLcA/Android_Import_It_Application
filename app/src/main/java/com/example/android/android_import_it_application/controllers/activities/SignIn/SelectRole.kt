package com.example.android.android_import_it_application.controllers.activities.SignIn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.android.android_import_it_application.R

class SelectRole : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_role)

        val ivBuyer = findViewById<View>(R.id.ivBuyer)
        val ivTraveler = findViewById<View>(R.id.ivTraveler)
        ivBuyer.setOnClickListener {
            startLogInActivity("buyer")
        }

        ivTraveler.setOnClickListener {
            startLogInActivity("traveler")
        }
    }

    private fun startLogInActivity(role: String) {
        val intent = Intent(this, LogIn::class.java)
        intent.putExtra("role", role)
        startActivity(intent)
    }
}