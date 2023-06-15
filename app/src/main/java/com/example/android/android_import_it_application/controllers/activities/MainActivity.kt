package com.example.android.android_import_it_application.controllers.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.android.android_import_it_application.R
import com.example.android.android_import_it_application.controllers.activities.SignIn.LogIn
import com.example.android.android_import_it_application.controllers.activities.SignIn.SelectRole

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_role)

        val ivBuyer = findViewById<View>(R.id.ivBuyer)
        val ivTraveler = findViewById<View>(R.id.ivTraveler)
        val tvComprador = findViewById<TextView>(R.id.tvComprador)
        val tvViajero = findViewById<TextView>(R.id.tvViajero)
        ivBuyer.setOnClickListener {
            startLogInActivity("buyer")
        }

        tvComprador.setOnClickListener{
            startLogInActivity("buyer")
        }

        ivTraveler.setOnClickListener {
            startLogInActivity("traveler")
        }

        tvViajero.setOnClickListener {
            startLogInActivity("traveler")
        }

    }
    private fun startLogInActivity(role: String) {
        val intent = Intent(this, LogIn::class.java)
        intent.putExtra("role", role)
        startActivity(intent)
    }

}