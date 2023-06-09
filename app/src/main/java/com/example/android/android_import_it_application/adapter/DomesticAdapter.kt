package com.example.android.android_import_it_application.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.android_import_it_application.R
import com.example.android.android_import_it_application.models.DomesticShipment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

class DomesticAdapter(private var domesticShipments: List<DomesticShipment>, private val context: Context, private val itemClickListener: OnItemClickListener): RecyclerView.Adapter<DomesticAdapter.ViewHolder>() {
    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val ivProductDomestic = view.findViewById<ImageView>(R.id.ivProductDomestic)
        val tvNameDomestic = view.findViewById<TextView>(R.id.tvNameDomestic)
        val tvDateDomestic = view.findViewById<TextView>(R.id.tvDateDomestic)
        val tvDepartureTimeDomestic = view.findViewById<TextView>(R.id.tvDepartureTimeDomestic)
        val faMoreDomestic = view.findViewById<FloatingActionButton>(R.id.faMoreDomestic)
    }

    interface OnItemClickListener{
        fun onItemClicked(domesticShipment: DomesticShipment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DomesticAdapter.ViewHolder {
        val view= LayoutInflater.from(context)
            .inflate(R.layout.prototype_domestic, parent, false)
        return DomesticAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: DomesticAdapter.ViewHolder, position: Int) {
        val domesticShipment = domesticShipments[position]
        holder.tvNameDomestic.text = domesticShipment.product_name
        holder.tvDateDomestic.text = domesticShipment.date
        holder.tvDepartureTimeDomestic.text = domesticShipment.departure_time
        holder.faMoreDomestic.setOnClickListener{
            itemClickListener.onItemClicked(domesticShipment)
        }

        val picBuilder = Picasso.Builder(context)
        picBuilder.downloader(OkHttp3Downloader(context))
        picBuilder.build().load(domesticShipment.image_product)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(holder.ivProductDomestic)
    }

    override fun getItemCount(): Int {
        return domesticShipments.size
    }
}