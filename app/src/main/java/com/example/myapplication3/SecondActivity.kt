package com.example.myapplication3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.second_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // --- Referencias para la Calculadora ---
        val etNumber1 = findViewById<EditText>(R.id.et_number1)
        val etNumber2 = findViewById<EditText>(R.id.et_number2)
        val btnSum = findViewById<Button>(R.id.btn_sum)
        val tvResult = findViewById<TextView>(R.id.tv_result)

        btnSum.setOnClickListener {
            val num1Str = etNumber1.text.toString()
            val num2Str = etNumber2.text.toString()

            if (num1Str.isNotEmpty() && num2Str.isNotEmpty()) {
                val sum = num1Str.toDouble() + num2Str.toDouble()
                // Mostramos el resultado asegurándonos de formatear bien si es un entero
                tvResult.text = "Resultado: ${sum.toString()}"
            } else {
                Toast.makeText(this, "Por favor, ingresa ambos números", Toast.LENGTH_SHORT).show()
            }
        }

        val btnGoBack = findViewById<Button>(R.id.btn_go_back)
        btnGoBack.setOnClickListener {
            // Cierra la actividad actual y vuelve a la anterior
            finish()
        }

        // Botón para ir a la TERCERA PANTALLA (Login)
        val btnGoThird = findViewById<Button>(R.id.btn_go_third)
        btnGoThird.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }
    }
}