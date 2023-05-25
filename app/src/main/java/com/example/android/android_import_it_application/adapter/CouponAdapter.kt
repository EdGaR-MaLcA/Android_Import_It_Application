package com.example.android.android_import_it_application.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.android_import_it_application.R
import com.example.android.android_import_it_application.models.Coupon
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

class CouponAdapter (private val coupons: List<Coupon>, private val context: Context): RecyclerView.Adapter<CouponAdapter.ViewHolder>(){
    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val tvTitle=view.findViewById<TextView>(R.id.tvTitle)
        val tvCode=view.findViewById<TextView>(R.id.tvCode)
        val tvSituation=view.findViewById<TextView>(R.id.tvSituation)
        val tvDescription=view.findViewById<TextView>(R.id.tvDescription)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(context)
            .inflate(R.layout.prototype_coupon, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val coupon=coupons[position]
        holder.tvTitle.text=coupon.title
        holder.tvCode.text=coupon.code
        holder.tvDescription.text=coupon.description
        holder.tvSituation.text=coupon.situation

    }

    override fun getItemCount(): Int {
        return coupons.size
    }

}