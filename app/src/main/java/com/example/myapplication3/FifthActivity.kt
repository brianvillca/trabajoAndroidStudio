package com.example.myapplication3

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FifthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fifth)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.fifth_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val imgError = findViewById<ImageView>(R.id.img_error)
        imgError.setOnClickListener {
            // Al hacer clic en la imagen, cerramos esta pantalla para volver al login a reintentar
            finish()
        }
    }
}