package com.example.taller_2

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import android.os.Handler
import android.os.Looper

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Usamos un Handler para retrasar el cambio de layout
        Handler(Looper.getMainLooper()).postDelayed({
            // Después de 2 segundos, cambia el layout
            // Si quieres cambiar el layout dentro de la misma actividad
            setContentView(R.layout.activity_main) // Cambiar a otro layout

            // Si quieres navegar a otra actividad, usa un Intent
            // val intent = Intent(this, MainActivity::class.java)
            // startActivity(intent)
            // finish() // Opcionalmente, puedes llamar a finish() si quieres cerrar esta actividad
        }, 5000) // 2000 milisegundos = 2 segundos
        // Usamos un Handler para retrasar el cambio de layout
        Handler(Looper.getMainLooper()).postDelayed({
            // Después de 2 segundos, cambia el layout
            // Si quieres cambiar el layout dentro de la misma actividad
            setContentView(R.layout.activity_login) // Cambiar a otro layout

            // Si quieres navegar a otra actividad, usa un Intent
            // val intent = Intent(this, MainActivity::class.java)
            // startActivity(intent)
            // finish() // Opcionalmente, puedes llamar a finish() si quieres cerrar esta actividad
        }, 5000) // 2000 milisegundos = 2 segundos
    }
}