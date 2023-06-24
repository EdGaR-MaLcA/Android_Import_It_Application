package com.example.android.android_import_it_application.controllers.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.android.android_import_it_application.R
import com.example.android.android_import_it_application.models.Order
import com.example.android.android_import_it_application.network.ImportItService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PlaceOrderForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_order_form)
        val dni = intent.getStringExtra("DNI")

        val etTitle = findViewById<EditText>(R.id.etTitle)
        val etUrl = findViewById<EditText>(R.id.etUrl)
        val etName = findViewById<EditText>(R.id.etName)
        val etAmount = findViewById<EditText>(R.id.etAmount)
        val etPrice = findViewById<EditText>(R.id.etPrice)
        val etWeight = findViewById<EditText>(R.id.etWeight)

        val ibArrow = findViewById<ImageButton>(R.id.ibArrow)
        ibArrow.setOnClickListener {
            val intent: Intent = Intent(this, BuyerActivity::class.java)
            intent.putExtra("DNI", dni)
            startActivity(intent)
            Log.d("DNI", "DNI pasado: $dni")
        }

        val btnPlaceOrder = findViewById<Button>(R.id.btnPlaceOrder)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://importitbackend-production-fd05.up.railway.app/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val importItService: ImportItService = retrofit.create(ImportItService::class.java)

        btnPlaceOrder.setOnClickListener {
            val dniString = dni ?: ""
            val title = etTitle.text.toString()
            val url = etUrl.text.toString()
            val name = etName.text.toString()
            val amount = etAmount.text.toString()
            val price = etPrice.text.toString()
            val weight = etWeight.text.toString()
            val commission = price.toDouble() * 0.3
            val status = "in progress"
            val order = Order(
                order_id = 0,  // Si es una nueva orden, se puede dejar como 0
                name = name,
                amount = amount,
                comision = commission.toString(),
                dni = dniString,
                price = price,
                status = status,
                tittle = title,
                url = url,
                weight = weight
            )

            importItService.placeOrder(order).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        // La orden se creó exitosamente
                        Toast.makeText(applicationContext, "Orden creada correctamente", Toast.LENGTH_SHORT).show()

                        val intent = Intent(this@PlaceOrderForm, BuyerActivity::class.java)
                        intent.putExtra("DNI", dniString)
                        startActivity(intent)
                        finish()
                    } else {
                        // Error en la respuesta de la API
                        Toast.makeText(applicationContext, "Error en la respuesta de la API", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    // Error en la llamada a la API
                    Toast.makeText(applicationContext, "Error en la llamada a la API", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

}

