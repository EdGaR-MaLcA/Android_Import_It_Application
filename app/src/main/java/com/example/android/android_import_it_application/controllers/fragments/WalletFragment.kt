package com.example.android.android_import_it_application.controllers.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.android_import_it_application.R
import com.example.android.android_import_it_application.adapter.CustomerOrderAdapter
import com.example.android.android_import_it_application.adapter.WalletAdapter
import com.example.android.android_import_it_application.controllers.activities.WalletSeeMoreActivity
import com.example.android.android_import_it_application.database.OrderDatabase
import com.example.android.android_import_it_application.models.Order
import com.example.android.android_import_it_application.models.User

class WalletFragment : Fragment(), WalletAdapter.OnItemClickListener{
    var savedOrders: List<Order> = ArrayList()
    private lateinit var orders: List<Order>
    lateinit var recyclerView: RecyclerView
    lateinit var btnInProcessW2: Button
    lateinit var btnDoneW: Button
    lateinit var btnAll: Button
    lateinit var currentStatus: String
    private var role: String = ""
    private var user: User? = null
    private var dni: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.wallet_traveler, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments

        dni = bundle?.getString("DNI", "") ?: ""
        role = bundle?.getString("role", "") ?: ""
        user = bundle?.getSerializable("User") as? User

        recyclerView=view.findViewById(R.id.rvWalletOrders)
        btnInProcessW2 = view.findViewById(R.id.btnInProcessW2)
        btnDoneW = view.findViewById(R.id.btnDoneW)
        btnAll = view.findViewById(R.id.btnAll)
        loadOrders()

        //loadOrders()
    }

    private fun loadOrders() {
        savedOrders= OrderDatabase.getInstance(requireContext()).getOrderDAO().getAllOrders()
        println(savedOrders)
        recyclerView.layoutManager= LinearLayoutManager(context)
        recyclerView.adapter= WalletAdapter(savedOrders, requireContext(), this@WalletFragment)
        btnInProcessW2.setOnClickListener {
            loadOrders1()
        }
        btnDoneW.setOnClickListener {
            loadOrders2()
        }
        btnAll.setOnClickListener {
            loadOrders()
        }
    }

    private fun loadOrders1() {
        currentStatus = "In Process"
        savedOrders= OrderDatabase.getInstance(requireContext()).getOrderDAO().getOrdersByStatus(currentStatus)
        println(savedOrders)
        recyclerView.layoutManager= LinearLayoutManager(context)
        recyclerView.adapter= WalletAdapter(savedOrders, requireContext(), this@WalletFragment)
    }

    private fun loadOrders2() {
        currentStatus = "Done"
        savedOrders= OrderDatabase.getInstance(requireContext()).getOrderDAO().getOrdersByStatus(currentStatus)
        println(savedOrders)
        recyclerView.layoutManager= LinearLayoutManager(context)
        recyclerView.adapter= WalletAdapter(savedOrders, requireContext(), this@WalletFragment)
    }


    override fun onItemClicked(order: Order){
        Log.d("Principal", "Id: "+order.order_id)
        val intento = Intent(context, WalletSeeMoreActivity::class.java)
        intento.putExtra("Order", order)
        intento.putExtra("DNI", dni)
        intento.putExtra("role", role)
        intento.putExtra("User", user)
        startActivity(intento)
    }
}