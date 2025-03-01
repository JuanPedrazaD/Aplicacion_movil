package com.example.taller_2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class SplashActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "SplashActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val btnComienza = findViewById<Button>(R.id.start_button)
        btnComienza.setOnClickListener {
            Log.d(TAG, "Redirigiendo al Login")
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        val btnRegistrarse = findViewById<Button>(R.id.register_button)
        btnRegistrarse.setOnClickListener {
            Log.d(TAG, "Redirigiendo al Register")
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}