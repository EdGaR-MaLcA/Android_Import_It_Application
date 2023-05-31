package com.example.android.android_import_it_application.controllers.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.android_import_it_application.R
import com.example.android.android_import_it_application.controllers.fragments.CouponFragment
import com.example.android.android_import_it_application.controllers.fragments.EnterCouponFragment
import com.example.android.android_import_it_application.controllers.fragments.SeeProductsFragment

class CouponActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coupon)

        val transaction = supportFragmentManager.beginTransaction()

        transaction.add(R.id.flFragmentCoupon, EnterCouponFragment()).commitAllowingStateLoss()

    }
}