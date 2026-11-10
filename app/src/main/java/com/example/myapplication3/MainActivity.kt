package com.example.myapplication3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1. Referencias al TextView y al Button que están en el archivo XML
        val texto = findViewById<TextView>(R.id.tv_message)
        val boton = findViewById<Button>(R.id.btn_action)

        // 2. Definir qué pasa al tocar el botón
        boton.setOnClickListener {
            // Se cambia el texto del TextView
            texto.text = "texto 1 cambio"
        }

        // 3. Botón para ir a la segunda pantalla
        val btnNext = findViewById<Button>(R.id.btn_next_screen)
        btnNext.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }
}
