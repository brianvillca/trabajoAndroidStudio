package com.example.myapplication3

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SixthActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null
    private var lightSensor: Sensor? = null
    private var gyroscope: Sensor? = null

    private lateinit var tvAccelVal: TextView
    private lateinit var tvLightVal: TextView
    private lateinit var tvGyroVal: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sixth)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.sixth_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvAccelVal = findViewById(R.id.tv_accel_val)
        tvLightVal = findViewById(R.id.tv_light_val)
        tvGyroVal = findViewById(R.id.tv_gyro_val)
        val btnBack = findViewById<Button>(R.id.btn_go_back_sixth)

        // Inicializamos el gestor de sensores de Android
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        // Obtenemos las referencias de los 3 sensores (Acelerómetro, Luz y Giroscopio)
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

        if (accelerometer == null) tvAccelVal.text = "Acelerómetro no disponible en este dispositivo"
        if (lightSensor == null) tvLightVal.text = "Sensor de luz no disponible en este dispositivo"
        if (gyroscope == null) tvGyroVal.text = "Giroscopio no disponible en este dispositivo"

        btnBack.setOnClickListener { finish() }
    }

    override fun onResume() {
        super.onResume()
        // Registramos los escuchadores para capturar cambios en los sensores
        accelerometer?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
        lightSensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
        gyroscope?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        // Desregistramos para no agotar la batería en segundo plano
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event == null) return

        when (event.sensor.type) {
            Sensor.TYPE_ACCELEROMETER -> {
                val x = String.format("%.2f", event.values[0])
                val y = String.format("%.2f", event.values[1])
                val z = String.format("%.2f", event.values[2])
                tvAccelVal.text = "X: $x  |  Y: $y  |  Z: $z"
            }
            Sensor.TYPE_LIGHT -> {
                val lux = event.values[0]
                tvLightVal.text = "Intensidad de luz: $lux lx"
            }
            Sensor.TYPE_GYROSCOPE -> {
                val x = String.format("%.2f", event.values[0])
                val y = String.format("%.2f", event.values[1])
                val z = String.format("%.2f", event.values[2])
                tvGyroVal.text = "X: $x  |  Y: $y  |  Z: $z rad/s"
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // No se requiere lógica específica de precisión
    }
}