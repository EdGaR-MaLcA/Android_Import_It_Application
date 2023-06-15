package com.example.android.android_import_it_application.controllers.activities.SignIn

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.android.android_import_it_application.R
import com.example.android.android_import_it_application.models.User
import com.example.android.android_import_it_application.network.ImportItService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val etName = findViewById<EditText>(R.id.etName)
        val etDni = findViewById<EditText>(R.id.etDni)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etAddress = findViewById<EditText>(R.id.etAddress)
        val etBirthday = findViewById<EditText>(R.id.etBirthday)
        val etAge = findViewById<EditText>(R.id.etAge)
        val etGenre = findViewById<EditText>(R.id.etGenre)
        val etPhone = findViewById<EditText>(R.id.etPhone)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnSignUp = findViewById<Button>(R.id.btnSignUp)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://importitbackend-production-fd05.up.railway.app/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val userService: ImportItService = retrofit.create(ImportItService::class.java)

        btnSignUp.setOnClickListener {
            val name = etName.text.toString()
            val dni = etDni.text.toString()
            val email = etEmail.text.toString()
            val address = etAddress.text.toString()
            val birthday = etBirthday.text.toString()
            val age = etAge.text.toString()
            val genre = etGenre.text.toString()
            val phone = etPhone.text.toString()
            val password = etPassword.text.toString()

            val birthdayParts = birthday.split("/")
            val day = birthdayParts[0]
            val month = birthdayParts[1]
            val year = birthdayParts[2]

            val request = userService.getUsers()
            request.enqueue(object : Callback<List<User>> {
                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    if (response.isSuccessful) {
                        val userList = response.body()
                        val existingUser = userList?.find { it.dni == dni }
                        if (existingUser != null) {
                            Toast.makeText(applicationContext, "El DNI ingresado ya existe", Toast.LENGTH_SHORT).show()
                            return  // Salir del método en caso de DNI duplicado
                        }

                        // El DNI no está duplicado, realizar el registro
                        val user = User(0, name, age, password, dni, genre, "", "", "", email, phone, address)

                        val createUserRequest = userService.createUser(user)
                        createUserRequest.enqueue(object : Callback<User> {
                            override fun onResponse(call: Call<User>, response: Response<User>) {
                                if (response.isSuccessful) {
                                    Toast.makeText(applicationContext, "Registro exitoso", Toast.LENGTH_SHORT).show()
                                    // Realizar acciones adicionales después del registro exitoso
                                    val intent = Intent(this@SignUp, LogIn::class.java)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    Toast.makeText(applicationContext, "Error en el registro", Toast.LENGTH_SHORT).show()
                                    // Manejar el error de registro
                                }
                            }

                            override fun onFailure(call: Call<User>, t: Throwable) {
                                Toast.makeText(applicationContext, "Error en la llamada a la API", Toast.LENGTH_SHORT).show()
                                // Manejar el error en la llamada a la API
                            }
                        })
                    } else {
                        Toast.makeText(applicationContext, "Error en la respuesta de la API", Toast.LENGTH_SHORT).show()
                        // Manejar el error en la respuesta de la API
                    }
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    Toast.makeText(applicationContext, "Error en la llamada a la API", Toast.LENGTH_SHORT).show()
                    // Manejar el error en la llamada a la API
                }
            })
        }
    }
}