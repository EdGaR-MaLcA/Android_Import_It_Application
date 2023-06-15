package com.example.android.android_import_it_application.controllers.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.android.android_import_it_application.R
import com.example.android.android_import_it_application.models.ProductList
import com.example.android.android_import_it_application.models.User
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

class ProfileActivity : AppCompatActivity() {

    lateinit var tvNameProfile: TextView
    lateinit var tvAgeProfile: TextView
    lateinit var tvDNIProfile: TextView
    lateinit var tvDayProfile: TextView
    lateinit var tvMonthProfile: TextView
    lateinit var tvYearProfile: TextView
    lateinit var tvCellProfile: TextView
    lateinit var tvDirectionProfile: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        tvNameProfile = findViewById(R.id.tvNameProfileEdit)
        tvAgeProfile = findViewById(R.id.tvAgeProfileEdit)
        tvDNIProfile = findViewById(R.id.tvDNIProfileEdit)
        tvDayProfile = findViewById(R.id.tvDayProfile)
        tvMonthProfile = findViewById(R.id.tvMonthProfile)
        tvYearProfile = findViewById(R.id.tvYearProfile)
        tvCellProfile = findViewById(R.id.tvCellProfileEdit)
        tvDirectionProfile = findViewById(R.id.tvDirectionProfileEdit)

        initFields(this)
    }

    private fun initFields(context: Context) {
        val userObject: User? = intent.getSerializableExtra("User") as? User

        tvNameProfile.text = userObject?.name_user
        tvAgeProfile.text = userObject?.age
        tvDNIProfile.text = userObject?.dni
        tvDayProfile.text = userObject?.day
        tvMonthProfile.text = userObject?.month
        tvYearProfile.text = userObject?.year
        tvCellProfile.text = userObject?.phone
        tvDirectionProfile.text = userObject?.address






    }
}