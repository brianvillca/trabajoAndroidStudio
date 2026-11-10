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

class FourthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fourth)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.fourth_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etNum1 = findViewById<EditText>(R.id.et_num1_fourth)
        val etNum2 = findViewById<EditText>(R.id.et_num2_fourth)
        val btnAdd = findViewById<Button>(R.id.btn_add)
        val btnSubtract = findViewById<Button>(R.id.btn_subtract)
        val btnMultiply = findViewById<Button>(R.id.btn_multiply)
        val btnDivide = findViewById<Button>(R.id.btn_divide)
        val tvResult = findViewById<TextView>(R.id.tv_result_fourth)
        val btnBack = findViewById<Button>(R.id.btn_go_back_fourth)

        // Acción de Sumar
        btnAdd.setOnClickListener {
            val num1Str = etNum1.text.toString()
            val num2Str = etNum2.text.toString()

            if (num1Str.isNotEmpty() && num2Str.isNotEmpty()) {
                val result = num1Str.toDouble() + num2Str.toDouble()
                tvResult.text = "Resultado: ${if(result % 1 == 0.0) result.toInt() else result}"
            } else {
                Toast.makeText(this, "Por favor, ingresa ambos números", Toast.LENGTH_SHORT).show()
            }
        }

        // Acción de Restar
        btnSubtract.setOnClickListener {
            val num1Str = etNum1.text.toString()
            val num2Str = etNum2.text.toString()

            if (num1Str.isNotEmpty() && num2Str.isNotEmpty()) {
                val result = num1Str.toDouble() - num2Str.toDouble()
                tvResult.text = "Resultado: ${if(result % 1 == 0.0) result.toInt() else result}"
            } else {
                Toast.makeText(this, "Por favor, ingresa ambos números", Toast.LENGTH_SHORT).show()
            }
        }

        // Acción de Multiplicar
        btnMultiply.setOnClickListener {
            val num1Str = etNum1.text.toString()
            val num2Str = etNum2.text.toString()

            if (num1Str.isNotEmpty() && num2Str.isNotEmpty()) {
                val result = num1Str.toDouble() * num2Str.toDouble()
                tvResult.text = "Resultado: ${if(result % 1 == 0.0) result.toInt() else result}"
            } else {
                Toast.makeText(this, "Por favor, ingresa ambos números", Toast.LENGTH_SHORT).show()
            }
        }

        // Acción de Dividir
        btnDivide.setOnClickListener {
            val num1Str = etNum1.text.toString()
            val num2Str = etNum2.text.toString()

            if (num1Str.isNotEmpty() && num2Str.isNotEmpty()) {
                val num2 = num2Str.toDouble()
                
                // Validación crucial: No se puede dividir por cero
                if (num2 == 0.0) {
                    Toast.makeText(this, "Error: No se puede dividir por 0", Toast.LENGTH_SHORT).show()
                    tvResult.text = "Resultado: Error"
                } else {
                    val result = num1Str.toDouble() / num2
                    tvResult.text = "Resultado: ${if(result % 1 == 0.0) result.toInt() else result}"
                }
            } else {
                Toast.makeText(this, "Por favor, ingresa ambos números", Toast.LENGTH_SHORT).show()
            }
        }

        // Volver atrás
        btnBack.setOnClickListener {
            finish()
        }

        // Ir a la actividad de sensores (Pantalla 6)
        val btnGoSixth = findViewById<Button>(R.id.btn_go_sixth)
        btnGoSixth.setOnClickListener {
            val intent = Intent(this, SixthActivity::class.java)
            startActivity(intent)
        }
    }
}