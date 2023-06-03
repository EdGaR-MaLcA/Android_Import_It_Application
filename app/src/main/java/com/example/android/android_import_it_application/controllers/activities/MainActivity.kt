package com.example.android.android_import_it_application.controllers.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.android.android_import_it_application.R

class MainActivity : AppCompatActivity() {
    /*
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

    private fun getFragmentFor(item: MenuItem): Fragment {
        return when (item.itemId){
            R.id.menu_home -> SeeProductsFragment()
            else -> CouponFragment()
        }
    }
*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btBuyer = findViewById<Button>(R.id.btBuyer)

        btBuyer.setOnClickListener{
            val intent: Intent = Intent(this, BuyerActivity::class.java)
            startActivity(intent)
        }

    }
    /*override fun onCreate(savedInstanceState: Bundle?) {
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
    }*/

}