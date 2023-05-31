package com.example.android.android_import_it_application.controllers.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.android_import_it_application.R
import com.example.android.android_import_it_application.adapter.CouponAdapter
import com.example.android.android_import_it_application.controllers.activities.DescriptionItemActivity
import com.example.android.android_import_it_application.controllers.activities.SavedCouponsActivity
import com.example.android.android_import_it_application.database.CouponDatabase
import com.example.android.android_import_it_application.models.Coupon
import com.example.android.android_import_it_application.models.ProductList
import com.example.android.android_import_it_application.network.ImportItService
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class EnterCouponFragment : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_enter_coupon, container, false)

        val etCouponEntered = view.findViewById<EditText>(R.id.etCouponEntered)
        val btnCheckCoupon = view.findViewById<Button>(R.id.btnCheckCoupon)

        btnCheckCoupon.setOnClickListener {
            val couponCode = etCouponEntered.text.toString()
            checkCoupon(view.context, couponCode)
        }

        return view
    }
    private fun checkCoupon(context: Context, couponCode: String) {
        val retrofit= Retrofit.Builder()
            .baseUrl("https://importitbackend-production-fd05.up.railway.app/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val couponService: ImportItService = retrofit.create(ImportItService::class.java)
        val request=couponService.getCoupons()

        request.enqueue(object : Callback<List<Coupon>> {
            override fun onFailure(call: Call<List<Coupon>>, t: Throwable) {
                Log.d("Activity Fail", "Error: $t")
            }

            override fun onResponse(
                call: Call<List<Coupon>>,
                response: Response<List<Coupon>>
            ) {
                if(response.isSuccessful){
                    val coupons: List<Coupon> = response.body()!!
                    //logica para ver si el couponCode es igual a algun valor del code obtenido de la api
                    val couponExists = coupons.any { coupon -> coupon.code == couponCode }

                    if (couponExists) {
                        // El cupón existe
                        Log.d("Activity", "Cupón válido")
                        val coupon = coupons.first { it.code == couponCode } // Obtener el cupón correspondiente al código

                        saveCoupon(context, coupon)

                        showCoupons()

                    } else {
                        // El cupón no existe
                        Log.d("Activity", "Cupón inválido")
                    }
                } else{
                    Log.d("Activity fail", "Error: "+response.code())
                }
            }
        })
    }
    private fun saveCoupon(context: Context, coupon: Coupon) {
        CouponDatabase.getInstance(context).getCouponDAO().insertCoupon(coupon)
    }

    private fun showCoupons(){
        val intent = Intent(context, SavedCouponsActivity::class.java)
        startActivity(intent)
    }

}