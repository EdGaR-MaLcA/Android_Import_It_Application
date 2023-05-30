package com.example.android.android_import_it_application.controllers.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.example.android.android_import_it_application.R

class MainScreen : Fragment() {
    lateinit var ibCoupons: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main_screen, container, false)

        // Find the ImageButton by its ID
        ibCoupons = view.findViewById(R.id.ibCoupons)

        // Set an OnClickListener to the ImageButton
        ibCoupons.setOnClickListener {
            // Navigate to another fragment when the ImageButton is clicked
            val anotherFragment = CouponFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.flFragment, anotherFragment)
                .addToBackStack(null)
                .commit()
        }

        return view
    }
}