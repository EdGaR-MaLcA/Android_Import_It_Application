package com.example.android.android_import_it_application.controllers.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.android.android_import_it_application.R
import com.example.android.android_import_it_application.models.ProductList
import com.google.android.material.textfield.TextInputEditText
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

class DescriptionItemActivity: AppCompatActivity() {

    lateinit var ivDescription: ImageView
    lateinit var tvProductNameDescription: TextView
    lateinit var tvPriceDescription: TextView


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.description_item)

        val dni = intent.getStringExtra("DNI")
        val user = intent.getSerializableExtra("User")
        val role = intent.getStringExtra("role")

        val ibArrowD = findViewById<ImageButton>(R.id.ibArrowD)
        val bProceedPay = findViewById<Button>(R.id.bProceedPay)

        ibArrowD.setOnClickListener {
            val intent: Intent = Intent(this, SeeProductActivity::class.java)
            intent.putExtra("DNI", dni)
            intent.putExtra("User", user)
            intent.putExtra("role", role)
            startActivity(intent)
        }

        bProceedPay.setOnClickListener {
            val intent: Intent = Intent(this, PaymentActivity::class.java)
            intent.putExtra("DNI", dni)
            intent.putExtra("User", user)
            intent.putExtra("role", role)
            startActivity(intent)
        }

        ivDescription = findViewById(R.id.ivDescription)
        tvProductNameDescription = findViewById(R.id.tvProductNameDescription)
        tvPriceDescription = findViewById(R.id.tvPriceDescription)

        initFields(this)
    }

    private fun initFields(context: Context) {
        val seeProductObject: ProductList = intent.getSerializableExtra("ProductList") as ProductList

        val pickBuilder = Picasso.Builder(context)
        pickBuilder.downloader(OkHttp3Downloader(context))

        pickBuilder.build().load(seeProductObject.imgURL)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(ivDescription)

        tvProductNameDescription.text = seeProductObject.name
        tvPriceDescription.text = seeProductObject.price

    }
}