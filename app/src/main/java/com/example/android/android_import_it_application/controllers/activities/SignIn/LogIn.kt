package com.example.android.android_import_it_application.controllers.activities.SignIn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.android.android_import_it_application.R
import com.example.android.android_import_it_application.controllers.activities.BuyerActivity
import com.example.android.android_import_it_application.controllers.activities.TravelerActivity
import com.example.android.android_import_it_application.models.User
import com.example.android.android_import_it_application.network.ImportItService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LogIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        val role = intent.getStringExtra("role")

        if (role != null && (role == "buyer" || role == "traveler")) {
            if (role == "buyer") {
                Toast.makeText(this, "Inicio de sesión como comprador", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Inicio de sesión como viajero", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Rol no válido", Toast.LENGTH_SHORT).show()
        }

        val etDni = findViewById<EditText>(R.id.etDni)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvSignUp = findViewById<TextView>(R.id.tvSignUp)

        tvSignUp.setOnClickListener {
            val intent = Intent(this@LogIn, SignUp::class.java)
            startActivity(intent)
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://importitbackend-production-fd05.up.railway.app/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val userService: ImportItService = retrofit.create(ImportItService::class.java)

        btnLogin.setOnClickListener {
            val dni = etDni.text.toString()
            val password = etPassword.text.toString()

            // Realizar la llamada a la API para verificar el DNI y obtener la información del usuario
            val request=userService.getUsers()
            request.enqueue(object : Callback<List<User>> {
                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    if (response.isSuccessful) {
                        val userList: List<User> = response.body()!!
                        val user: User? = userList.find { it.dni == dni }
                        if (user != null && user.password == password) {
                            // La contraseña coincide con la información del usuario
                            Toast.makeText(applicationContext, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                            if (role == "buyer") {
                                val intent = Intent(this@LogIn, BuyerActivity::class.java)
                                intent.putExtra("User", user)
                                intent.putExtra("DNI", dni)
                                startActivity(intent)
                            } else {
                                val intent = Intent(this@LogIn, TravelerActivity::class.java)
                                intent.putExtra("User", user)
                                startActivity(intent)
                            }
                            // Realiza las acciones necesarias para el inicio de sesión exitoso
                        } else {
                            // El usuario no existe o la contraseña es incorrecta
                            Toast.makeText(applicationContext, "Credenciales inválidas", Toast.LENGTH_SHORT).show()
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