package com.example.android.android_import_it_application.controllers.activities
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.android.android_import_it_application.R
import androidx.room.Room
import com.example.android.android_import_it_application.database.MyDatabase


class DirectionLoadActivity : AppCompatActivity(){
    private lateinit var textViewDatos: TextView
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var buttonBuscar: Button
    private var dnifilt: String? = null
    private lateinit var editTextApellido: EditText
    private lateinit var ibBackDirections: ImageButton
    private lateinit var imageViewMap: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.buyerdirectionshow)

        val dni = intent.getStringExtra("DNI")
        val user = intent.getSerializableExtra("User")
        val role = intent.getStringExtra("role")

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)

        textViewDatos = findViewById(R.id.textViewdata)

        editTextApellido = findViewById(R.id.filterdni)
        buttonBuscar = findViewById(R.id.btnsearch)
        imageViewMap = findViewById(R.id.imageViewMap)

        buttonBuscar.setOnClickListener {
            dnifilt = editTextApellido.text.toString()
            mostrarDatosGuardados()
        }

        ibBackDirections = findViewById(R.id.ibBackDirections)

        ibBackDirections.setOnClickListener {
            val intent: Intent = Intent(this, BuyerDirectionActivity::class.java)
            intent.putExtra("DNI", dni)
            intent.putExtra("User", user)
            intent.putExtra("role", role)
            startActivity(intent)
        }

        mostrarDatosGuardados()
    }

    private fun mostrarDatosGuardados() {
        val name = sharedPreferences.getString("nombre", "")
        val lastname = sharedPreferences.getString("apellido", "")
        val phone = sharedPreferences.getString("telefono", "")
        val dni = sharedPreferences.getString("dni", "")
        val district = sharedPreferences.getString("district", "")
        val province = sharedPreferences.getString("province", "")
        val direcccion = sharedPreferences.getString("direccion", "")
        val departamen = sharedPreferences.getString("depaertamento", "")
        val datos = "Nombre: $name \n" +
                "Apellido: $lastname \n" +
                "Telefono: $phone \n" +
                "Dni: $dni \n" +
                "Distrito: $district \n" +
                "Provincia: $province \n"+
                "Direccion: $direcccion \n"+
                "Departamento: $departamen"
        textViewDatos.text = datos
        if (dnifilt != null && dni != null && dni.equals(dnifilt, ignoreCase = true)) {
            textViewDatos.visibility = View.VISIBLE
        } else {
            textViewDatos.visibility = View.GONE
        }
        val apiKey = getString(R.string.google_maps_api_key)
        val mapUrl = "https://maps.googleapis.com/maps/api/staticmap?" +
                "center=$direcccion" +
                "&zoom=15" +
                "&size=600x400" +
                "&markers=color:red%7C$direcccion" +
                "&key=$apiKey"

        // Carga la imagen del mapa en el ImageView usando Glide
        Glide.with(this)
            .load(mapUrl)
            .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
            .into(imageViewMap)
    }
}
