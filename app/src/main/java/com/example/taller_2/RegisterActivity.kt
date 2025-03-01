package com.example.taller_2

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "RegisterActivity"
    }

    private lateinit var edtFirstName: EditText
    private lateinit var edtLastName: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPhone: EditText
    private lateinit var edtPassword: EditText
    private lateinit var edtRepeatPassword: EditText
    private lateinit var chkTerms: CheckBox
    private lateinit var btnRegister: Button
    private lateinit var txtLoginRedirect: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        Log.d(TAG, "onCreate called")

        initializeViews()

        btnRegister.setOnClickListener {
            onRegisterClicked()
        }

        txtLoginRedirect.setOnClickListener {
            redirectToLogin()
        }
    }

    private fun initializeViews() {
        edtFirstName = findViewById(R.id.edtFirstName)
        edtLastName = findViewById(R.id.edtLastName)
        edtEmail = findViewById(R.id.edtEmail)
        edtPhone = findViewById(R.id.edtPhone)
        edtPassword = findViewById(R.id.edtPassword)
        edtRepeatPassword = findViewById(R.id.edtRepeatPassword)
        chkTerms = findViewById(R.id.chkTerms)
        btnRegister = findViewById(R.id.btnRegister)
        txtLoginRedirect = findViewById(R.id.txtLoginRedirect)
    }

    private fun onRegisterClicked() {
        val firstName = edtFirstName.text.toString().trim()
        val lastName = edtLastName.text.toString().trim()
        val email = edtEmail.text.toString().trim()
        val phone = edtPhone.text.toString().trim()
        val password = edtPassword.text.toString().trim()
        val repeatPassword = edtRepeatPassword.text.toString().trim()

        if (validateInputs(firstName, lastName, email, phone, password, repeatPassword)) {
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "Usuario registrado correctamente")

            // Guardamos los datos con SharedPreferences
            saveUserData(firstName, lastName, email, phone, password)

            // Redireccionamos al Login
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
            finish()
        }
    }

    private fun saveUserData(firstName: String, lastName: String, email: String, phone: String, password: String) {
        val sharedPref: SharedPreferences = getSharedPreferences("userData", MODE_PRIVATE)
        val editor = sharedPref.edit()

        editor.putString("FirstName", firstName)
        editor.putString("LastName", lastName)
        editor.putString("Email", email)
        editor.putString("Phone", phone)
        editor.putString("Password", password)
        editor.apply() // Guarda los datos

        Log.d(TAG, "Datos guardados en SharedPreferences")
    }

    private fun validateInputs(
        firstName: String,
        lastName: String,
        email: String,
        phone: String,
        password: String,
        repeatPassword: String
    ): Boolean {
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || repeatPassword.isEmpty()) {
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!email.contains("@")) {
            Toast.makeText(this, "Correo electrónico inválido", Toast.LENGTH_SHORT).show()
            return false
        }
        if (password != repeatPassword) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!chkTerms.isChecked) {
            Toast.makeText(this, "Debes aceptar los términos y condiciones", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun redirectToLogin() {
        Log.d(TAG, "Redirigiendo al Login")
        val loginIntent = Intent(this, LoginActivity::class.java)
        startActivity(loginIntent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy called")
    }
}
