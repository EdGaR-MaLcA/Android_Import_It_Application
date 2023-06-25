package com.example.android.android_import_it_application.controllers.activities

import android.content.SharedPreferences
import com.example.android.android_import_it_application.database.FormData
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.android.android_import_it_application.R
import kotlinx.coroutines.launch

import androidx.lifecycle.lifecycleScope
import com.example.android.android_import_it_application.database.AppDatabase

class DirectionFormAgregateBuyerActivity : AppCompatActivity() {
    private lateinit var editTextDepartament: EditText
    private lateinit var editTextDirection: EditText
    private lateinit var editTextDistrict: EditText
    private lateinit var editTextDNI: EditText
    private lateinit var editTextLastname: EditText
    private lateinit var editTextName: EditText
    private lateinit var editTextPhone: EditText
    private lateinit var editTextProvince: EditText
    private lateinit var buttonSave: Button
    private lateinit var buttonDiscard: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.buyer_direction_agregate)


        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)

        editTextDepartament = findViewById(R.id.editTextDepartament)
        editTextDirection = findViewById(R.id.editTextDirection)
        editTextDistrict = findViewById(R.id.editTextDistrict)
        editTextDNI = findViewById(R.id.editTextDNI)
        editTextLastname = findViewById(R.id.editTextLastname)
        editTextName = findViewById(R.id.editTextName)
        editTextPhone = findViewById(R.id.editTextPhone)
        editTextProvince = findViewById(R.id.editTextProvince)
        buttonSave = findViewById(R.id.buttonSave)
        buttonDiscard = findViewById(R.id.buttonDiscard)

        buttonSave.setOnClickListener {
            saveFormData()
        }

        buttonDiscard.setOnClickListener {
            discardFormData()
        }
    }

    private fun saveFormData() {
        val departament = editTextDepartament.text.toString()
        val direction = editTextDirection.text.toString()
        val district = editTextDistrict.text.toString()
        val dni = editTextDNI.text.toString()
        val lastname = editTextLastname.text.toString()
        val name = editTextName.text.toString()
        val phone = editTextPhone.text.toString()
        val province = editTextProvince.text.toString()

        val editor = sharedPreferences.edit()
        editor.putString("nombre", name)
        editor.putString("apellido", lastname)
        editor.putString("dni", dni)
        editor.putString("telefono", phone)
        editor.putString("province", province)
        editor.putString("district", district)
        editor.putString("direccion", direction)
        editor.putString("depaertamento", departament)
        editor.apply()
        Toast.makeText(this, "Datos guardados", Toast.LENGTH_SHORT).show()


        finish()
    }

    private fun discardFormData() {
        // Descartar los datos sin guardarlos
        finish()
    }
}


