package com.example.android.android_import_it_application.controllers.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.android.android_import_it_application.R
import com.example.android.android_import_it_application.database.OrderDatabase
import com.example.android.android_import_it_application.models.Order
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

class WalletSeeMoreActivity : AppCompatActivity() {

    lateinit var tvProductNameWSM: TextView
    lateinit var tvDateWSM: TextView
    lateinit var tvAuthorWSM: TextView
    lateinit var tvComisionWSM: TextView
    lateinit var tvUrlWSM: TextView
    lateinit var tvStatusWSM: TextView
    lateinit var btnDeleteWallet: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.prototype_wallet_see_more_traveler)

        tvProductNameWSM = findViewById(R.id.tvProductNameWSM)
        tvDateWSM = findViewById(R.id.tvDateWSM)
        tvAuthorWSM = findViewById(R.id.tvAuthorWSM)
        tvComisionWSM = findViewById(R.id.tvComisionWSM)
        tvUrlWSM = findViewById(R.id.tvUrlWSM)
        tvStatusWSM = findViewById(R.id.tvStatusWSM)
        btnDeleteWallet = findViewById(R.id.btnDeleteWallet)

        initFields(this)
    }

    private fun initFields(context: Context) {
        val orderObject: Order = intent.getSerializableExtra("Order") as Order

        tvProductNameWSM.text = orderObject.tittle
        tvAuthorWSM.text = orderObject.name
        tvComisionWSM.text = orderObject.comision
        tvUrlWSM.text = orderObject.url
        tvStatusWSM.text = orderObject.status

        btnDeleteWallet.setOnClickListener {
            deleteOrder(orderObject, context)
            finish()
        }

    }

    private fun deleteOrder(order: Order, context: Context) {
        OrderDatabase.getInstance(context).getOrderDAO().deleteOrder(order)
        //Toast.makeText(this.context, "Album deleted, go to home or album section to refresh.", Toast.LENGTH_SHORT).show();
    }

}