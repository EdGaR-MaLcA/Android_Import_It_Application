package com.example.android.android_import_it_application.controllers.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.android.android_import_it_application.R
import com.example.android.android_import_it_application.models.DomesticShipment
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

class DomesticDetails : AppCompatActivity() {

    lateinit var ivDomesticDetail: ImageView
    lateinit var tvNameDomesticDetail: TextView
    lateinit var tvShippingDateDetail: TextView
    lateinit var tvDepartureDetail: TextView
    lateinit var tvShippingDetail: TextView
    lateinit var tvTotalCostDetail: TextView
    lateinit var tvTrackingDetail: TextView
    lateinit var tvDestinyDetail: TextView
    private lateinit var dni: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_domestic_details)

        val dni = intent.getStringExtra("DNI")

        val ibArrow = findViewById<ImageButton>(R.id.ibArrowDomesticDetails)
        ibArrow.setOnClickListener {
            val intent: Intent = Intent(this, DomesticActivity::class.java)
            intent.putExtra("DNI", dni)
            startActivity(intent)
            Log.d("DNI", "DNI pasado: $dni")
        }

        ivDomesticDetail = findViewById(R.id.ivDetailDomestic)
        tvNameDomesticDetail = findViewById(R.id.tvNameDomesticDetail)
        tvShippingDateDetail = findViewById(R.id.tvShippingDateDetail)
        tvDepartureDetail = findViewById(R.id.tvDepartureDetail)
        tvShippingDetail = findViewById(R.id.tvShippingDetail)
        tvTotalCostDetail = findViewById(R.id.tvTotalCostDetail)
        tvTrackingDetail = findViewById(R.id.tvTrackingDetail)
        tvDestinyDetail = findViewById(R.id.tvDestinyDetail)

        initFields(this)
    }

    private fun initFields(context: Context) {
        val domesticObject: DomesticShipment = intent.getSerializableExtra("DomesticShipment") as DomesticShipment

        val pickBuilder = Picasso.Builder(context)
        pickBuilder.downloader(OkHttp3Downloader(context))

        pickBuilder.build().load(domesticObject.image_product)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(ivDomesticDetail)

        tvNameDomesticDetail.text = domesticObject.product_name
        tvShippingDateDetail.text = domesticObject.shipping_date
        tvDepartureDetail.text = domesticObject.departure_time
        tvShippingDetail.text = domesticObject.shipping_manager
        tvTotalCostDetail.text = domesticObject.total_cost
        tvTrackingDetail.text = domesticObject.tracking_code
        tvDestinyDetail.text = domesticObject.destiny
    }
}