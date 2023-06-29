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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CheckDni : AppCompatActivity() {
    private lateinit var etDniPassword: EditText
    private lateinit var btnContinuar: Button
    private lateinit var userService: ImportItService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_dni)

        etDniPassword = findViewById(R.id.etDniPassword)
        btnContinuar = findViewById(R.id.btnContinuar)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://importitbackend-production-fd05.up.railway.app/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        userService = retrofit.create(ImportItService::class.java)

        val dni = intent.getStringExtra("DNI")
        val user = intent.getSerializableExtra("User")
        val role = intent.getStringExtra("role")

        btnContinuar.setOnClickListener {
            val dni = etDniPassword.text.toString()

            // Realizar la llamada a la API para verificar si el DNI existe
            val request = userService.getUsers()
            request.enqueue(object : Callback<List<User>> {
                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    if (response.isSuccessful) {
                        val userList = response.body()
                        val user = userList?.find { it.dni == dni }
                        if (user != null) {
                            // El DNI existe
                            //Toast.makeText(applicationContext, "DNI existe", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@CheckDni, ChangePasswordActivity::class.java)
                            intent.putExtra("userId", user.user_id)
                            //intent.putExtra("DNI", user.dni)
                            //intent.putExtra("User", user)
                            intent.putExtra("role", role)
                            startActivity(intent)
                        } else {
                            // El DNI no existe
                            Toast.makeText(applicationContext, "El DNI no existe", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        // Error en la respuesta de la API
                        Toast.makeText(applicationContext, "Error en la respuesta de la API", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    // Error en la llamada a la API
                    Toast.makeText(applicationContext, "Error en la llamada a la API", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}