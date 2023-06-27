package com.example.android.android_import_it_application.controllers.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.android_import_it_application.R
import com.example.android.android_import_it_application.adapter.DomesticAdapter
import com.example.android.android_import_it_application.adapter.SeeProductAdapter
import com.example.android.android_import_it_application.controllers.activities.DomesticDetails
import com.example.android.android_import_it_application.models.DomesticShipment
import com.example.android.android_import_it_application.models.ProductList
import com.example.android.android_import_it_application.models.User
import com.example.android.android_import_it_application.network.ImportItService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DomesticFragment : Fragment(), DomesticAdapter.OnItemClickListener {
    private lateinit var domesticShips: List<DomesticShipment>
    lateinit var recyclerView: RecyclerView
    private var role: String = ""
    private var user: User? = null
    private var dni: String = ""
    private lateinit var user_id: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_domestic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rvDomestic)

        val bundle = arguments
        if (dni.isEmpty()) {
            dni = bundle?.getString("DNI", "") ?: ""
        }
        role = bundle?.getString("role", "") ?: ""
        user = bundle?.getSerializable("User") as? User


        getUserIDByDNI(dni) { userID ->
            if (userID != null) {
                Log.d("User ID", "ID del usuario con DNI $dni: $userID")
                user_id = userID
            } else {
                Log.d("User ID", "No se encontró ningún usuario con el DNI $dni")
            }
            val svDomestic = view.findViewById<SearchView>(R.id.svDomestic)
            svDomestic.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    filterProducts(view.context, newText)
                    return true
                }
            })
            loadDomestic(view.context, dni)
        }
    }
    private fun loadDomestic(context: Context, dni: String) {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://importitbackend-production-fd05.up.railway.app/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val domesticService: ImportItService = retrofit.create(ImportItService::class.java)
            val request = domesticService.getDomesticShipments()
            request.enqueue(object : Callback<List<DomesticShipment>> {
                override fun onFailure(call: Call<List<DomesticShipment>>, t: Throwable) {
                    Log.d("Activity Fail", "Error: $t")
                }

                override fun onResponse(
                    call: Call<List<DomesticShipment>>,
                    response: Response<List<DomesticShipment>>
                ) {
                    if (response.isSuccessful) {
                        val domesticShipments: List<DomesticShipment> = response.body()!!
                        domesticShips = domesticShipments.filter { it.userId == user_id }
                        recyclerView.layoutManager = LinearLayoutManager(context)
                        recyclerView.adapter =
                            DomesticAdapter(domesticShips, context, this@DomesticFragment)
                        this@DomesticFragment.domesticShips = domesticShips
                    } else {
                        Log.d("Activity fail", "Error: " + response.code())
                    }
                }
            })
        }
    private fun filterProducts(context: Context, query: String) {
        val filteredProducts = mutableListOf<DomesticShipment>()
               for (product in domesticShips) {
                   if (product.product_name.contains(query, ignoreCase = true)) {
                       filteredProducts.add(product)
                   }
               }
               recyclerView.adapter = DomesticAdapter(filteredProducts, context, this@DomesticFragment)
            }
    private fun getUserIDByDNI(dni: String, callback: (String?) -> Unit) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://importitbackend-production-fd05.up.railway.app/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val userService: ImportItService = retrofit.create(ImportItService::class.java)
        val request = userService.getUsers()
        request.enqueue(object : Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.d("Activity Fail", "Error: $t")
                callback(null)
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    val users: List<User> = response.body()!!
                    val user = users.find { it.dni == dni }
                    val userID = user?.user_id
                    callback(userID.toString())
                } else {
                    Log.d("Activity fail", "Error: " + response.code())
                    callback(null)
                }
            }
        })
    }

    override fun onItemClicked(domesticShipment: DomesticShipment) {
        val intent = Intent(context, DomesticDetails::class.java)
        intent.putExtra("DomesticShipment", domesticShipment)
        intent.putExtra("DNI", dni)
        intent.putExtra("role", role)
        intent.putExtra("User", user)
        startActivity(intent)
        Log.d("DNI", "DNI pasado: $dni")
        Log.d("User", "User pasado: $user")
    }
}