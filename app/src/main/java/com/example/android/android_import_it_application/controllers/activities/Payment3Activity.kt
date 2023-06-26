package com.example.android.android_import_it_application.controllers.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.android.android_import_it_application.R

class Payment3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.payment3)

        val dni = intent.getStringExtra("DNI")
        val user = intent.getSerializableExtra("User")
        val role = intent.getStringExtra("role")

        val ibArrow4 = findViewById<ImageButton>(R.id.ibArrow4)
        val bBack = findViewById<Button>(R.id.bBack)

        ibArrow4.setOnClickListener {
            val intent: Intent = Intent(this, SeeProductActivity::class.java)
            intent.putExtra("DNI", dni)
            intent.putExtra("User", user)
            intent.putExtra("role", role)
            startActivity(intent)
        }
        bBack.setOnClickListener {
            val intent: Intent = Intent(this, SeeProductActivity::class.java)
            intent.putExtra("DNI", dni)
            intent.putExtra("User", user)
            intent.putExtra("role", role)
            startActivity(intent)
        }

    }
}