package com.example.taller_2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PasswordRecoveryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_recovery)

        val edtEmailRecovery = findViewById<EditText>(R.id.edtEmailRecovery)
        val btnSendRecovery = findViewById<Button>(R.id.btnSendRecovery)

        btnSendRecovery.setOnClickListener {
            val email = edtEmailRecovery.text.toString()

            if (email.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Correo de recuperación enviado a: $email", Toast.LENGTH_LONG).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Correo inválido, por favor verifica", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
