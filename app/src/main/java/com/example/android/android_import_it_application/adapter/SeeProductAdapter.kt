package com.example.android.android_import_it_application.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.android_import_it_application.R
import com.example.android.android_import_it_application.controllers.activities.SeeProductActivity
import com.example.android.android_import_it_application.models.ProductList
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

class SeeProductAdapter(private val seeProducts: List<ProductList>, private val context: Context, private val itemClickListener: OnItemClickListener): RecyclerView.Adapter<SeeProductAdapter.ViewHolder>() {
    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val ivSeeProduct = view.findViewById<ImageView>(R.id.ivSeeProduct)
        val tvNameSeeProduct = view.findViewById<TextView>(R.id.tvNameSeeProduct)
        val tvPriceSeeProduct = view.findViewById<TextView>(R.id.tvPriceSeeProduct)
        val faSeeProduct = view.findViewById<FloatingActionButton>(R.id.faMoreSeeProduct)
    }

    interface OnItemClickListener{
        fun onItemClicked(seeProduct: ProductList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeeProductAdapter.ViewHolder {
        val view= LayoutInflater.from(context)
            .inflate(R.layout.prototype_see_products, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SeeProductAdapter.ViewHolder, position: Int) {
        val seeProduct = seeProducts[position]
        holder.tvNameSeeProduct.text = seeProduct.name
        holder.tvPriceSeeProduct.text = seeProduct.price
        holder.faSeeProduct.setOnClickListener{
            itemClickListener.onItemClicked(seeProduct)
        }

        val picBuilder = Picasso.Builder(context)
        picBuilder.downloader(OkHttp3Downloader(context))
        picBuilder.build().load(seeProduct.imgURL)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(holder.ivSeeProduct)
    }

    override fun getItemCount(): Int {
        return seeProducts.size
    }
}