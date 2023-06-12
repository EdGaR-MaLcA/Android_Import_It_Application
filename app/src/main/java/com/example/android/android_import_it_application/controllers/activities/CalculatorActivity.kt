package com.example.android.android_import_it_application.controllers.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.android.android_import_it_application.R

class CalculatorActivity : AppCompatActivity() {

    private lateinit var categorySpinner: Spinner
    private lateinit var selectedCategoryTextView: TextView
    private lateinit var etnSubTotal: EditText
    private lateinit var tvApproxCost: TextView
    private lateinit var btnCalculate: Button
    private lateinit var tvSymbol: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        val ibBackCalculator = findViewById<ImageButton>(R.id.ibBackCalculator)
        ibBackCalculator.setOnClickListener{
            val intent: Intent = Intent(this, BuyerActivity::class.java)
            startActivity(intent)
        }

        categorySpinner = findViewById(R.id.categorySpinner)
        selectedCategoryTextView = findViewById(R.id.selectedCategoryTextView)
        etnSubTotal = findViewById(R.id.etnSubTotal)
        tvApproxCost = findViewById(R.id.tvApproxCost)
        btnCalculate = findViewById(R.id.btnCalculate)

        val categories = arrayOf("Technology", "Skin Care", "Fashion", "Other Category")

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner.adapter = adapter

        categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedCategory = parent.getItemAtPosition(position) as String
                selectedCategoryTextView.text = selectedCategory
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // No se seleccionó nada
            }
        }

        btnCalculate.setOnClickListener {
            calculateApproximateCost()
        }

        tvSymbol = findViewById(R.id.tvSymbol)
    }

    private fun calculateApproximateCost() {
        val subtotal = etnSubTotal.text.toString().toDoubleOrNull()
        val category = selectedCategoryTextView.text.toString()

        if (subtotal != null && subtotal > 0) {
            val multiplier = when (category) {
                "Technology" -> 0.30
                "Skin Care" -> 0.15
                "Fashion" -> 0.20
                "Other Category" -> 0.10
                else -> 0.0
            }

            val approximateCost = subtotal * multiplier
            tvApproxCost.text = approximateCost.toString()

            val approximateCostInt = approximateCost.toInt()
            val symbolCount = if (approximateCostInt % 2 == 0) {
                approximateCostInt / 2
            } else {
                (approximateCostInt + 1) / 2
            }
            val symbolString = StringBuilder()

            for (i in 1..symbolCount) {
                symbolString.append("█")
            }

            tvSymbol.text = symbolString.toString()

            tvSymbol.text = symbolString.toString()
        } else {
            tvApproxCost.text = ""
            tvSymbol.text = ""
            Toast.makeText(this, "Please enter a valid subtotal", Toast.LENGTH_SHORT).show()
        }
    }

}