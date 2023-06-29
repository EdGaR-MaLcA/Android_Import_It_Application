package com.example.android.android_import_it_application.controllers.activities.SignIn

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.android.android_import_it_application.R
import com.example.android.android_import_it_application.controllers.activities.BuyerActivity
import com.example.android.android_import_it_application.controllers.activities.WebViewActivity
import com.example.android.android_import_it_application.models.User
import com.example.android.android_import_it_application.network.ImportItService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream

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
        val checkBoxTerms = findViewById<CheckBox>(R.id.checkBoxTerms)
        val role = intent.getStringExtra("role")
        val tvAceptarTérminos = findViewById<TextView>(R.id.tvAceptarTérminos)
        val ibBackRegistro = findViewById<ImageButton>(R.id.ibBackRegistro)


        val retrofit = Retrofit.Builder()
            .baseUrl("https://importitbackend-production-fd05.up.railway.app/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val userService: ImportItService = retrofit.create(ImportItService::class.java)

        tvAceptarTérminos.setOnClickListener {
            val intent: Intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra("role", role)
            intent.putExtra("file_name", "terms_and_conditions.html")
            startActivity(intent)
        }

        ibBackRegistro.setOnClickListener {
            val intent: Intent = Intent(this, LogIn::class.java)
            intent.putExtra("role", role)
            startActivity(intent)
        }

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

            if (!checkBoxTerms.isChecked) {
                Toast.makeText(applicationContext, "Debes aceptar los términos y condiciones", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

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
                                    intent.putExtra("role", role)
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