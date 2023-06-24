package com.example.android.android_import_it_application.controllers.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.android.android_import_it_application.R
import com.example.android.android_import_it_application.models.User
import com.example.android.android_import_it_application.network.ImportItService
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class EditProfileActivity : AppCompatActivity() {

    lateinit var user: User
    lateinit var userService: ImportItService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val etName = findViewById<EditText>(R.id.etNameEdit)
        val etAge = findViewById<EditText>(R.id.etAgeEdit)
        val etCellphone = findViewById<EditText>(R.id.etCellphoneEdit)
        val etAddress = findViewById<EditText>(R.id.etAddressEdit)
        val btSaveEdit = findViewById<Button>(R.id.btSaveEdit)
        val ibArrow = findViewById<ImageButton>(R.id.ibArrowProfileEdit)


        ibArrow.setOnClickListener {
            val intent: Intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("User", user)
            startActivity(intent)
        }

        user = intent.getSerializableExtra("User") as User

        etName.setText(user.name_user)
        etAge.setText(user.age)
        etCellphone.setText(user.phone)
        etAddress.setText(user.address)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://importitbackend-production-fd05.up.railway.app/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        userService = retrofit.create(ImportItService::class.java)


        btSaveEdit.setOnClickListener {
            val newName = etName.text.toString()
            val newAge = etAge.text.toString()
            val newCellphone = etCellphone.text.toString()
            val newAddress = etAddress.text.toString()

            updateUser(newName, newAge, newCellphone, newAddress)

        }
    }

    private fun updateUser(newName: String, newAge: String, newCellphone: String, newAddress: String) {
        user.name_user = newName
        user.age = newAge
        user.phone = newCellphone
        user.address = newAddress

        val request = userService.updateUser(user.user_id, user)
        request.enqueue(object : Callback<User>{
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    Toast.makeText(applicationContext, "Data updated correctly", Toast.LENGTH_SHORT).show()
                } else{
                    Toast.makeText(applicationContext, "Error in API response", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(applicationContext, "Error in the API call", Toast.LENGTH_SHORT).show()
            }
        })
    }
}