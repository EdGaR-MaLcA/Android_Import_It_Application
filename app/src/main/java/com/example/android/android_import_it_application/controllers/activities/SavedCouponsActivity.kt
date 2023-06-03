package com.example.android.android_import_it_application.controllers.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.android_import_it_application.R
import com.example.android.android_import_it_application.controllers.fragments.SavedCouponsFragment
import com.example.android.android_import_it_application.controllers.fragments.SeeProductsFragment

class SavedCouponsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_coupons)

        val transaction = supportFragmentManager.beginTransaction()

        transaction.add(R.id.flFragmentSavedCoupons, SavedCouponsFragment()).commitAllowingStateLoss()


    }
}