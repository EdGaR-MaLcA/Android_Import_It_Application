package com.example.android.android_import_it_application.controllers.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.android.android_import_it_application.R
import com.example.android.android_import_it_application.controllers.activities.SignIn.LogIn
import com.example.android.android_import_it_application.models.User
import com.example.android.android_import_it_application.network.ImportItService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ChangePasswordActivity : AppCompatActivity() {
    private lateinit var btnCambioContraseña: Button
    private lateinit var etContraseña1: EditText
    private lateinit var etContraseña2: EditText
    private lateinit var userService: ImportItService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        btnCambioContraseña = findViewById(R.id.btnCambioContraseña)
        etContraseña1 = findViewById(R.id.etContraseña1)
        etContraseña2 = findViewById(R.id.etContraseña2)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://importitbackend-production-fd05.up.railway.app/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val role = intent.getStringExtra("role")

        userService = retrofit.create(ImportItService::class.java)

        btnCambioContraseña.setOnClickListener {
            val contraseña1 = etContraseña1.text.toString()
            val contraseña2 = etContraseña2.text.toString()

            if (contraseña1.isNotEmpty() && contraseña2.isNotEmpty() && contraseña1 == contraseña2) {
                val userId = intent.getIntExtra("userId",0)

                if (userId != null) {
                    // Realizar la llamada a la API para cambiar la contraseña del usuario
                    val request = userService.getUserById(userId.toString())
                    request.enqueue(object : Callback<User> {
                        override fun onResponse(call: Call<User>, response: Response<User>) {
                            if (response.isSuccessful) {
                                val user = response.body()
                                if (user != null) {
                                    // Actualizar la contraseña del usuario
                                    user.password = contraseña1

                                    val updateRequest = userService.updateUser(user.user_id, user)
                                    updateRequest.enqueue(object : Callback<User> {
                                        override fun onResponse(call: Call<User>, response: Response<User>) {
                                            if (response.isSuccessful) {
                                                // Contraseña actualizada exitosamente
                                                Toast.makeText(applicationContext, "Contraseña actualizada exitosamente", Toast.LENGTH_SHORT).show()

                                                // Redirigir a la actividad de inicio de sesión
                                                val intent = Intent(applicationContext, LogIn::class.java)
                                                intent.putExtra("role", role)
                                                startActivity(intent)

                                                finish() // Cerrar la actividad actual
                                            } else {
                                                // Error al actualizar la contraseña
                                                Toast.makeText(applicationContext, "Error al actualizar la contraseña", Toast.LENGTH_SHORT).show()
                                            }
                                        }

                                        override fun onFailure(call: Call<User>, t: Throwable) {
                                            // Error en la llamada a la API
                                            Toast.makeText(applicationContext, "Error en la llamada a la API", Toast.LENGTH_SHORT).show()
                                        }
                                    })
                                }
                            } else {
                                // Error al obtener el usuario
                                Toast.makeText(applicationContext, "Error al obtener el usuario", Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onFailure(call: Call<User>, t: Throwable) {
                            // Error en la llamada a la API
                            Toast.makeText(applicationContext, "Error en la llamada a la API", Toast.LENGTH_SHORT).show()
                        }
                    })
                }
            } else {
                // Las contraseñas no coinciden
                Toast.makeText(applicationContext, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            }
        }
    }
}