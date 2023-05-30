package com.example.android.android_import_it_application.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.android.android_import_it_application.R
import com.example.android.android_import_it_application.database.OrderDatabase
import com.example.android.android_import_it_application.models.Order
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

class WalletAdapter (private val savedOrder: List<Order>, private val context: Context): RecyclerView.Adapter<WalletAdapter.ViewHolder>(){
    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val tvDate=view.findViewById<TextView>(R.id.tvDate)
        val tvNumberOrder=view.findViewById<TextView>(R.id.tvNumberOrder)
        val tvNameW=view.findViewById<TextView>(R.id.tvNameW)
        val tvComisionW=view.findViewById<TextView>(R.id.tvComisionW)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(context)
            .inflate(R.layout.prototype_wallet_traveler, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val order=savedOrder[position]
        holder.tvNameW.text=order.tittle
        holder.tvComisionW.text=order.comision
        /*holder.fabDelete.setOnClickListener{
            deleteAlbum(album)
        }*/
    }

    private fun deleteAlbum(order: Order) {
        OrderDatabase.getInstance(this.context).getOrderDAO().deleteOrder(order)
        Toast.makeText(this.context, "Album deleted, go to home or album section to refresh.", Toast.LENGTH_SHORT).show();
    }


    override fun getItemCount(): Int {
        return savedOrder.size
    }
}