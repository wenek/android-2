package com.example.nietypowykalendarz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val textViewWelcome = findViewById<TextView>(R.id.textViewWelcome)
        val username = intent.getStringExtra("username")

        textViewWelcome.text = "Witaj na swoim profilu, $username"
    }
}
