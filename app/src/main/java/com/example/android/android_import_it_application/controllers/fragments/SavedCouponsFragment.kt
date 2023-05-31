package com.example.android.android_import_it_application.controllers.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.android_import_it_application.R
import com.example.android.android_import_it_application.adapter.SavedCouponsAdapter
import com.example.android.android_import_it_application.database.CouponDatabase
import com.example.android.android_import_it_application.models.Coupon

class SavedCouponsFragment : Fragment() {

    var savedCoupons: List<Coupon> = ArrayList()
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saved_coupons, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedCoupons= CouponDatabase.getInstance(view.context).getCouponDAO().getAllCoupons()
        recyclerView=view.findViewById(R.id.rvSavedCoupons)
        recyclerView.layoutManager= LinearLayoutManager(context)
        recyclerView.adapter= SavedCouponsAdapter(savedCoupons, view.context)
    }

}