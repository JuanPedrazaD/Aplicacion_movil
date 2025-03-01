package com.example.taller_2

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "LoginActivity"
    }

    private lateinit var edtUsername: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var txtRecoverPassword: TextView
    private lateinit var txtRegister: TextView
    private lateinit var btnGoogleLogin: Button

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Log.d(TAG, "onCreate called")

        initializeViews()
        sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE)

        btnLogin.setOnClickListener {
            onLoginClicked()
        }

        txtRecoverPassword.setOnClickListener {
            redirectToRecoverPassword()
        }

        txtRegister.setOnClickListener {
            redirectToRegister()
        }

        btnGoogleLogin.setOnClickListener {
            Toast.makeText(this, "Esta opción aún no está habilitada", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initializeViews() {
        edtUsername = findViewById(R.id.edtUsername)
        edtPassword = findViewById(R.id.edtPassword)
        btnLogin = findViewById(R.id.btnLogin)
        txtRecoverPassword = findViewById(R.id.txtRecoverPassword)
        txtRegister = findViewById(R.id.txtRegister)
        btnGoogleLogin = findViewById(R.id.btnGoogleLogin)
    }

    private fun onLoginClicked() {
        val username = edtUsername.text.toString().trim()
        val passwordInput = edtPassword.text.toString().trim()

        if (username.isEmpty() || passwordInput.isEmpty()) {
            Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        // Recuperamos los datos guardados en SharedPreferences
        val email = sharedPreferences.getString("Email", null)
        val phone = sharedPreferences.getString("Phone", null)
        val password = sharedPreferences.getString("Password", null)
        val firstName = sharedPreferences.getString("FirstName", null)
        val lastName = sharedPreferences.getString("LastName", null)

        Log.d(TAG, "Datos recuperados: Email=$email, Phone=$phone, Password=$password")

        if ((username == email || username == phone) && passwordInput == password) {
            Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "Usuario inició sesión correctamente")

            // Redirigimos al perfil
            val profileIntent = Intent(this, ProfileActivity::class.java).apply {
                putExtra("FirstName", firstName)
                putExtra("LastName", lastName)
                putExtra("Email", email)
                putExtra("Phone", phone)
            }
            startActivity(profileIntent)
            finish()
        } else {
            Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun redirectToRecoverPassword() {
        Log.d(TAG, "Redirigiendo a recuperar contraseña")
        val recoverIntent = Intent(this, PasswordRecoveryActivity::class.java)
        startActivity(recoverIntent)
    }

    private fun redirectToRegister() {
        Log.d(TAG, "Redirigiendo a registro")
        val registerIntent = Intent(this, RegisterActivity::class.java)
        startActivity(registerIntent)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy called")
    }
}

