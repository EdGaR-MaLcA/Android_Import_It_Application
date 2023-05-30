package com.example.android.android_import_it_application.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.android_import_it_application.R
import com.example.android.android_import_it_application.models.Order

class MyOrdersAdapter{ /*(private val myOrders: List<Order>, private val context: Context): RecyclerView.Adapter<MyOrdersAdapter.ViewHolder>() {
    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        //val ivCustOrders = view.findViewById<ImageView>(R.id.ivCustOrders)
        val tvTitleProduct = view.findViewById<TextView>(R.id.tvTitleProduct)
        val tvLocationProduct = view.findViewById<TextView>(R.id.tvLocationProduct)
        val tvUrl = view.findViewById<TextView>(R.id.tvUrl)
        val tvPrice = view.findViewById<TextView>(R.id.tvPrice)
        val tvWeight1 = view.findViewById<TextView>(R.id.tvWeight1)
        val tvComision = view.findViewById<TextView>(R.id.tvComision)
        val tvCusName = view.findViewById<TextView>(R.id.tvCusName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val view= LayoutInflater.from(context)
            .inflate(R.layout.prototype_customer_order_traveler, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val myOrder = myOrders[position]
        holder.tvTitleProduct.text = myOrder.tittle
        holder.tvUrl.text = myOrder.url
        holder.tvPrice.text = myOrder.price
        holder.tvWeight1.text = myOrder.weight
        holder.tvComision.text = myOrder.comision
        holder.tvCusName.text = myOrder.name
    }

    override fun getItemCount(): Int {
        return myOrders.size
    }*/
}