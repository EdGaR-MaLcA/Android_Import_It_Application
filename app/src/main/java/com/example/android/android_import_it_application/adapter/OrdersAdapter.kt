package com.example.android.android_import_it_application.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.android_import_it_application.R
import com.example.android.android_import_it_application.models.Order
import org.w3c.dom.Text

class OrdersAdapter (private val placedOrders: List<Order>, private val context: Context): RecyclerView.Adapter<OrdersAdapter.ViewHolder>() {

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val tvTitleProduct = view.findViewById<TextView>(R.id.tvTitle)
        val tvStatusProduct = view.findViewById<TextView>(R.id.tvStatus)
        val tvUrl = view.findViewById<TextView>(R.id.tvUrl)
        val tvPrice = view.findViewById<TextView>(R.id.tvPrice)
        val tvWeight1 = view.findViewById<TextView>(R.id.tvWeight1)
        val tvComision = view.findViewById<TextView>(R.id.tvComision)
        val tvAmount = view.findViewById<TextView>(R.id.tvAmount)
        val tvNameProduct = view.findViewById<TextView>(R.id.tvNameProduct)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersAdapter.ViewHolder {
        val view= LayoutInflater.from(context)
            .inflate(R.layout.prototype_order_buyer, parent, false)
        return OrdersAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrdersAdapter.ViewHolder, position: Int) {
        val cusOrder = placedOrders[position]
        holder.tvTitleProduct.text = cusOrder.tittle
        holder.tvUrl.text = cusOrder.url
        holder.tvPrice.text = cusOrder.price
        holder.tvWeight1.text = cusOrder.weight
        holder.tvComision.text = cusOrder.comision
        holder.tvAmount.text = cusOrder.amount
        holder.tvStatusProduct.text = cusOrder.status
        holder.tvNameProduct.text = cusOrder.name

    }

    override fun getItemCount(): Int {
        return placedOrders.size
    }




}
