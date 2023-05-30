package com.example.android.android_import_it_application.controllers.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.android_import_it_application.R
import com.example.android.android_import_it_application.adapter.WalletAdapter
import com.example.android.android_import_it_application.database.OrderDatabase
import com.example.android.android_import_it_application.models.Order

class WalletFragment : Fragment(){
    var savedOrders: List<Order> = ArrayList()
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.wallet_traveler, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedOrders= OrderDatabase.getInstance(view.context).getOrderDAO().getAllOrders()
        recyclerView=view.findViewById(R.id.rvWalletOrders)
        recyclerView.layoutManager= LinearLayoutManager(context)
        recyclerView.adapter= WalletAdapter(savedOrders, view.context)
    }
}