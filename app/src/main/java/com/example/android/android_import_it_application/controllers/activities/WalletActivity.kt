package com.example.android.android_import_it_application.controllers.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.android.android_import_it_application.R
import com.example.android.android_import_it_application.controllers.fragments.CustomerOrderFragment
import com.example.android.android_import_it_application.controllers.fragments.WalletFragment

class WalletActivity: AppCompatActivity() {
    //@SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet)

        val dni = intent.getStringExtra("DNI")
        val user = intent.getSerializableExtra("User")
        val role = intent.getStringExtra("role")
        val walletFragment = WalletFragment()
        val bundle = Bundle()
        bundle.putString("DNI", dni)
        bundle.putString("role", role)
        bundle.putSerializable("User", user)
        walletFragment.arguments = bundle

        val transaction = supportFragmentManager.beginTransaction()
        val ibBackWallet = findViewById<ImageButton>(R.id.ibBackWallet)

        transaction.add(R.id.flWallet, walletFragment).commitAllowingStateLoss()

        ibBackWallet.setOnClickListener {
            val intent: Intent = Intent(this, TravelerActivity::class.java)
            intent.putExtra("DNI", dni)
            intent.putExtra("User", user)
            intent.putExtra("role", role)
            startActivity(intent)
        }
    }
}