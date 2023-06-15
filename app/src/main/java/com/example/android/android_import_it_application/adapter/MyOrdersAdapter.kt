package com.example.android.android_import_it_application.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.android_import_it_application.R
import com.example.android.android_import_it_application.models.MyOrder
import com.example.android.android_import_it_application.network.ImportItService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyOrdersAdapter(private val myOrders: MutableList<MyOrder>, private val context: Context, private val onDeleteClickListener: OnDeleteClickListener? = null): RecyclerView.Adapter<MyOrdersAdapter.ViewHolder>() {
    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        //val ivCustOrders = view.findViewById<ImageView>(R.id.ivCustOrders)
        val tvTitleProduct = view.findViewById<TextView>(R.id.tvTitleProduct)
        val tvLocationProduct = view.findViewById<TextView>(R.id.tvLocationProduct)
        val tvUrl = view.findViewById<TextView>(R.id.tvUrl)
        val tvPrice = view.findViewById<TextView>(R.id.tvPrice)
        val tvWeight1 = view.findViewById<TextView>(R.id.tvWeight1)
        val tvComision = view.findViewById<TextView>(R.id.tvComision)
        val tvCusName = view.findViewById<TextView>(R.id.tvCusName)
        val btnDeleteMyOrders = view.findViewById<Button>(R.id.btnDeleteMyOrders)
    }

    interface OnDeleteClickListener {
        fun onDeleteClick(myOrder: MyOrder)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val view= LayoutInflater.from(context)
            .inflate(R.layout.prototype_my_orders_traveler, parent, false)
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

        holder.btnDeleteMyOrders.setOnClickListener {
            onDeleteClickListener?.onDeleteClick(myOrder)
        }
    }

    override fun getItemCount(): Int {
        return myOrders.size
    }

    fun deleteOrder(myOrder: MyOrder) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://importitbackend-production-fd05.up.railway.app/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val myOrdersService: ImportItService = retrofit.create(ImportItService::class.java)
        val request = myOrdersService.deleteOrder(myOrder.order_id)

        request.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    // Eliminación exitosa, puedes actualizar la lista de pedidos o notificar al usuario
                    // por ejemplo, puedes eliminar el pedido de la lista:
                    myOrders.remove(myOrder)
                    notifyDataSetChanged()
                } else {
                    // Manejar la respuesta no exitosa
                    Log.d("Delete Order", "Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // Manejar el error de la solicitud
                Log.d("Delete Order", "Error: $t")
            }
        })
    }
}