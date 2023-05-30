package com.example.android.android_import_it_application.controllers.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import com.example.android.android_import_it_application.R
import com.example.android.android_import_it_application.controllers.fragments.SeeProductsFragment

class SeeProductActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_see_product)

        val transaction = supportFragmentManager.beginTransaction()

        transaction.add(R.id.flFragmentSeeProduct, SeeProductsFragment()).commitAllowingStateLoss()


    }
}