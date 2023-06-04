package com.example.android.android_import_it_application.controllers.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.android_import_it_application.R
import com.example.android.android_import_it_application.controllers.fragments.EnterCouponFragment
import com.example.android.android_import_it_application.controllers.fragments.SavedCouponsFragment
import com.example.android.android_import_it_application.database.CouponDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CouponActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coupon)

        val transaction = supportFragmentManager.beginTransaction()

        val couponDao = CouponDatabase.getInstance(this).getCouponDAO()

        GlobalScope.launch {
            val couponCount = couponDao.getCouponsCount()

            val fragment = if (couponCount > 0) {
                SavedCouponsFragment()
            } else {
                EnterCouponFragment()
            }

            transaction.add(R.id.flFragmentCoupon, fragment).commitAllowingStateLoss()
        }
    }
}