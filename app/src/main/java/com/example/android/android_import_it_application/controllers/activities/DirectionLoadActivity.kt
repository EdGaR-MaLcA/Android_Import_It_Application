package com.example.android.android_import_it_application.controllers.activities
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.android.android_import_it_application.R
import androidx.room.Room
import com.example.android.android_import_it_application.database.MyDatabase

class DirectionLoadActivity : AppCompatActivity(){
    private lateinit var loadDataButton: Button
    private lateinit var myDatabase: MyDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.buyer_direction)

        myDatabase = Room.databaseBuilder(applicationContext, MyDatabase::class.java, "my-database").build()

        loadDataButton = findViewById(R.id.btnload)
        loadDataButton.setOnClickListener {
            cargarDatosGuardados()
        }
    }

    private fun cargarDatosGuardados() {
        val userList = myDatabase.userDao().getAllUsers()

        // userList contiene los datos cargados desde la base de datos

        // Puedes mostrar los datos en una lista, en una vista de texto, etc.
    }
}

