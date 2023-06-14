package com.example.android.android_import_it_application.controllers.activities

import com.example.android.android_import_it_application.database.FormData
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.buyer_direction_agregate)

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

        val formData = FormData(
            departament = departament,
            direction = direction,
            district = district,
            dni = dni,
            lastname = lastname,
            name = name,
            phone = phone,
            province = province
        )

        // Guardar los datos en la base de datos
        lifecycleScope.launch {
            AppDatabase.getInstance(applicationContext).formDataDao().insert(formData)
        }

        finish()
    }

    private fun discardFormData() {
        // Descartar los datos sin guardarlos
        finish()
    }
}


