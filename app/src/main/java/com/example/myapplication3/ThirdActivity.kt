package com.example.myapplication3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ThirdActivity : AppCompatActivity() {

    // Credenciales correctas "harcodeadas"
    private val Usuario = "admin"
    private val contraseña = "1234"
    
    // Contador de intentos fallidos
    private var Intentos_fallidos = 0
    private val intentos = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_third)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.third_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etUser = findViewById<EditText>(R.id.et_username)
        val etPass = findViewById<EditText>(R.id.et_password)
        val btnLogin = findViewById<Button>(R.id.btn_login)
        val btnBack = findViewById<Button>(R.id.btn_go_back_third)

        btnLogin.setOnClickListener {
            val user = etUser.text.toString()
            val pass = etPass.text.toString()

            if (user == Usuario && pass == contraseña) {
                // Si es correcto, reiniciamos el contador y mostramos mensaje
                Intentos_fallidos = 0
                Toast.makeText(this, "Bienvenido.", Toast.LENGTH_SHORT).show()
            } else {
                // Si es incorrecto, sumamos 1 al contador
                Intentos_fallidos++
                
                if (Intentos_fallidos >= intentos) {
                    // Bloqueamos los inputs y el botón al tercer error
                    etUser.isEnabled = false
                    etPass.isEnabled = false
                    btnLogin.isEnabled = false
                    Toast.makeText(this, "Cuenta bloqueada por seguridad tras 3 intentos fallidos", Toast.LENGTH_LONG).show()
                } else {
                    // Avisamos los intentos restantes y lanzamos la pantalla de error con la imagen
                    val intentosRestantes = intentos - Intentos_fallidos
                    Toast.makeText(this, "Datos incorrectos. Te quedan $intentosRestantes intentos", Toast.LENGTH_SHORT).show()
                }

                // Usamos Intent para abrir la pantalla de error con la imagen
                val intentError = Intent(this, FifthActivity::class.java)
                startActivity(intentError)
            }
        }
        
        // Botón para volver atrás
        btnBack.setOnClickListener {
            finish()
        }

        // Botón para ir a la CUARTA PANTALLA
        val btnGoFourth = findViewById<Button>(R.id.btn_go_fourth)
        btnGoFourth.setOnClickListener {
            val intent = Intent(this, FourthActivity::class.java)
            startActivity(intent)
        }
    }
}