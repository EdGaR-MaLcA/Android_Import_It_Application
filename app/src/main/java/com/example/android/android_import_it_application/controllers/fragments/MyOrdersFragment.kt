package com.example.android.android_import_it_application.controllers.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.android_import_it_application.R
import com.example.android.android_import_it_application.adapter.CustomerOrderAdapter
import com.example.android.android_import_it_application.adapter.MyOrdersAdapter
import com.example.android.android_import_it_application.models.MyOrder
import com.example.android.android_import_it_application.models.Order
import com.example.android.android_import_it_application.network.ImportItService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyOrdersFragment : Fragment(){
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_orders_traveler, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rvMyOrders)
        loadOrders(view.context)
    }

    private fun loadOrders(context: Context) {
        val retrofit= Retrofit.Builder()
            .baseUrl("https://importitbackend-production-fd05.up.railway.app/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val myOrdersService: ImportItService = retrofit.create(ImportItService::class.java)
        val request=myOrdersService.getMyOrders()
        request.enqueue(object : Callback<List<MyOrder>> {
            override fun onFailure(call: Call<List<MyOrder>>, t: Throwable) {
                Log.d("Activity Fail", "Error: $t")
            }

            override fun onResponse(
                call: Call<List<MyOrder>>,
                response: Response<List<MyOrder>>
            ) {
                if(response.isSuccessful){
                    val myOrders: List<MyOrder> = response.body()!!
                    recyclerView.layoutManager= LinearLayoutManager(context)
                    recyclerView.adapter= MyOrdersAdapter(myOrders, context)
                } else{
                    Log.d("Activity fail", "Error: "+response.code())
                }
            }
        })
    }
}