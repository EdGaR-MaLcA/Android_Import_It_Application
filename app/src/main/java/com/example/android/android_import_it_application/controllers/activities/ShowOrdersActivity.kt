package com.example.android.android_import_it_application.controllers.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import com.example.android.android_import_it_application.R
import com.example.android.android_import_it_application.controllers.fragments.CustomerOrderFragment
import com.example.android.android_import_it_application.controllers.fragments.OrdersFragment

class ShowOrdersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_orders)

        val dni = intent.getStringExtra("DNI")
        val transaction = supportFragmentManager.beginTransaction()
        val ordersFragment = OrdersFragment()

        // Pasar el valor de 'dni' al fragmento
        val bundle = Bundle()
        bundle.putString("DNI", dni)
        ordersFragment.arguments = bundle
        val ibBackDashboard = findViewById<ImageButton>(R.id.ibBackDashboard)

        transaction.add(R.id.flFragmentOrders, ordersFragment).commitAllowingStateLoss()

        ibBackDashboard.setOnClickListener {
            val intent: Intent = Intent(this, BuyerActivity::class.java)
            intent.putExtra("DNI", dni)
            startActivity(intent)
            Log.d("DNI", "DNI pasado: $dni")
        }
    }
}