package com.example.taller_2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "ProfileActivity"
    }

    private lateinit var txtProfileName: TextView
    private lateinit var txtProfileSurname: TextView
    private lateinit var txtProfileEmail: TextView
    private lateinit var txtProfilePhone: TextView
    private lateinit var txtProfileInitial: TextView
    private lateinit var imgProfilePicture: ImageView
    private lateinit var btnEditProfile: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        Log.d(TAG, "onCreate called")

        txtProfileName = findViewById(R.id.txtProfileName)
        txtProfileSurname = findViewById(R.id.txtProfileSurname)
        txtProfileEmail = findViewById(R.id.txtProfileEmail)
        txtProfilePhone = findViewById(R.id.txtProfilePhone)
        txtProfileInitial = findViewById(R.id.txtProfileInitial)
        imgProfilePicture = findViewById(R.id.imgProfilePicture)
        btnEditProfile = findViewById(R.id.btnEditProfile)

        val firstName = intent.getStringExtra("FirstName")
        val lastName = intent.getStringExtra("LastName")
        val email = intent.getStringExtra("Email")
        val phone = intent.getStringExtra("Phone")

        txtProfileName.text = firstName
        txtProfileSurname.text = lastName
        txtProfileEmail.text = email
        txtProfilePhone.text = phone

        if (!firstName.isNullOrEmpty()) {
            val initial = firstName[0].toString().uppercase()
            txtProfileInitial.text = initial
            txtProfileInitial.visibility = TextView.VISIBLE
            imgProfilePicture.visibility = ImageView.GONE
        } else {
            txtProfileInitial.visibility = TextView.GONE
            imgProfilePicture.visibility = ImageView.VISIBLE
        }

        btnEditProfile.setOnClickListener {
            val returnIntent = Intent(this@ProfileActivity, RegisterActivity::class.java)
            startActivity(returnIntent)
            finish()
        }
    }
}

