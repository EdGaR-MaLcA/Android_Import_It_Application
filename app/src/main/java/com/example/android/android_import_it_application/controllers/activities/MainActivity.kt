package com.example.android.android_import_it_application.controllers.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.android.android_import_it_application.R
import com.example.android.android_import_it_application.controllers.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
            item -> navigateTo(item)
    }

    private fun navigateTo(item: MenuItem) : Boolean{
        item.isChecked = true
        return supportFragmentManager
            .beginTransaction()
            .replace(R.id.flFragment, getFragmentFor(item))
            .commit() > 0
    }

    /*private fun getFragmentFor(item: MenuItem): Fragment {
        return when (item.itemId){
            R.id.menu_home -> SeeProductsFragment()
            else -> CouponFragment()
        }
    }*/

    private fun getFragmentFor(item: MenuItem): Fragment {
        return when (item.itemId){
            R.id.menu_home -> MyOrdersFragment()
            else -> WalletFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.bnvMenu)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        navigateTo(navView.menu.findItem(R.id.menu_home))
    }
    /*@SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_traveler)


        val imgOrder = findViewById<ImageView>(R.id.ivCustomerOrder)
        val tvCustomerOrder = findViewById<TextView>(R.id.tvCustomerOrder)
        val ivWallet = findViewById<ImageView>(R.id.ivWallet)
        val tvWallet = findViewById<TextView>(R.id.tvWallet)
        //val tvBackWallet = findViewById<TextView>(R.id.tvBackWallet)

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
    }*/

}