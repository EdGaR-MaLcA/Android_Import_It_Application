package com.example.android.android_import_it_application.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.android.android_import_it_application.R
import com.example.android.android_import_it_application.database.OrderDatabase
import com.example.android.android_import_it_application.models.Order
import com.example.android.android_import_it_application.network.ImportItService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CustomerOrderAdapter (private val customerOrders: List<Order>, private val context: Context): RecyclerView.Adapter<CustomerOrderAdapter.ViewHolder>() {

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        //val ivCustOrders = view.findViewById<ImageView>(R.id.ivCustOrders)
        val tvTitleProduct = view.findViewById<TextView>(R.id.tvTitleProduct)
        val tvLocationProduct = view.findViewById<TextView>(R.id.tvLocationProduct)
        val tvUrl = view.findViewById<TextView>(R.id.tvUrl)
        val tvPrice = view.findViewById<TextView>(R.id.tvPrice)
        val tvWeight1 = view.findViewById<TextView>(R.id.tvWeight1)
        val tvComision = view.findViewById<TextView>(R.id.tvComision)
        val tvCusName = view.findViewById<TextView>(R.id.tvCusName)
        val btnSelect = view.findViewById<Button>(R.id.btnDeleteMyOrders)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val view= LayoutInflater.from(context)
            .inflate(R.layout.prototype_customer_order_traveler, parent, false)
        return ViewHolder(view)
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://importitbackend-production-fd05.up.railway.app/api/") // Ajusta la URL base según tu API
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(ImportItService::class.java)

    interface AdapterCallback {
        fun onCreateOrderClicked(order: Order)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cusOrder = customerOrders[position]
        holder.tvTitleProduct.text = cusOrder.tittle
        holder.tvUrl.text = cusOrder.url
        holder.tvPrice.text = cusOrder.price
        holder.tvWeight1.text = cusOrder.weight
        holder.tvComision.text = cusOrder.comision
        holder.tvCusName.text = cusOrder.name
        holder.btnSelect.setOnClickListener{
            saveOrder(cusOrder)
            val order = customerOrders[position]
            sendOrderToServer(order)
        }
    }

    fun sendOrderToServer(order: Order) {
        val call = api.createMyOrder(order)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    // La solicitud se completó exitosamente
                    Toast.makeText(context, "Orden enviada al servidor.", Toast.LENGTH_SHORT).show()
                } else {
                    // Manejar el caso de error en la solicitud
                    Toast.makeText(context, "Error en la solicitud: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // Manejar el caso de error de comunicación
                Toast.makeText(context, "Error en la comunicación con el servidor.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun saveOrder(cusOrder: Order) {
        val query = OrderDatabase.getInstance(context).getOrderDAO().getById(cusOrder.order_id)
        if (query.isEmpty()) {
            OrderDatabase.getInstance(context).getOrderDAO().insertOrder(cusOrder)
            Toast.makeText(context, "Orden guardada.", Toast.LENGTH_SHORT).show()
        }
        else Toast.makeText(context, "Ya se ha guardado previamente esta orden.", Toast.LENGTH_SHORT).show()
    }

    override fun getItemCount(): Int {
        return customerOrders.size
    }
}