package com.example.android.android_import_it_application.controllers.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.android_import_it_application.R
import com.example.android.android_import_it_application.adapter.SeeProductAdapter
import com.example.android.android_import_it_application.controllers.activities.DescriptionItemActivity
import com.example.android.android_import_it_application.models.ProductList
import com.example.android.android_import_it_application.network.ImportItService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SeeProductsFragment : Fragment(), SeeProductAdapter.OnItemClickListener {
    lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.see_products, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView= view.findViewById(R.id.rvSeeProducts)
        loadSeeProducts(view.context)
    }

    private fun loadSeeProducts(context: Context) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://importitbackend-production-fd05.up.railway.app/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val seeProductService: ImportItService = retrofit.create(ImportItService::class.java)
        val request=seeProductService.getProductLists()
        request.enqueue(object : Callback<List<ProductList>> {
            override fun onFailure(call: Call<List<ProductList>>, t: Throwable) {
                Log.d("Activity Fail", "Error: $t")
            }

            override fun onResponse(
                call: Call<List<ProductList>>,
                response: Response<List<ProductList>>
            ) {
                if(response.isSuccessful){
                    val seeProducts: List<ProductList> = response.body()!!
                    recyclerView.layoutManager= LinearLayoutManager(context)
                    recyclerView.adapter= SeeProductAdapter(seeProducts, context, this@SeeProductsFragment)
                } else{
                    Log.d("Activity fail", "Error: "+response.code())
                }
            }
        })
    }

    override fun onItemClicked(seeProduct: ProductList){
        val intent = Intent(context, DescriptionItemActivity::class.java)
        startActivity(intent)
    }


}