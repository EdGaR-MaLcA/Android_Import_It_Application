package com.example.android.android_import_it_application.controllers.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageButton
import android.widget.ImageView
import com.example.android.android_import_it_application.R
import com.example.android.android_import_it_application.controllers.activities.SignIn.SignUp

class WebViewActivity : AppCompatActivity() {
    private lateinit var webView: WebView
    private lateinit var ibBackWebView: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        webView = findViewById(R.id.webView)
        ibBackWebView = findViewById(R.id.ibBackWebView)
        val role = intent.getStringExtra("role")

        ibBackWebView.setOnClickListener {
            val intent: Intent = Intent(this, SignUp::class.java)
            intent.putExtra("role", role)
            startActivity(intent)
        }

        val fileUrl = "file:///android_asset/terms_and_conditions.html"

        webView.webViewClient = WebViewClient() // Configurar un WebViewClient para manejar las solicitudes de URL

        val settings = webView.settings
        settings.javaScriptEnabled = true // Habilitar JavaScript si es necesario

        webView.loadUrl(fileUrl)
    }
}

