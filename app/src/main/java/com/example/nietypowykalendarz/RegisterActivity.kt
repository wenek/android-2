package com.example.nietypowykalendarz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextReenterPassword: EditText
    private lateinit var buttonRegister: Button
    private lateinit var buttonLogin: Button
    private lateinit var buttonCalendar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        editTextEmail = findViewById(R.id.edit_text_email)
        editTextPassword = findViewById(R.id.edit_text_password)
        editTextReenterPassword = findViewById(R.id.edit_text_password_confirmation)
        buttonRegister = findViewById(R.id.button_register)
        buttonLogin = findViewById(R.id.button_login)
        buttonCalendar = findViewById(R.id.button_calendar)

        buttonRegister.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            val reenterPassword = editTextReenterPassword.text.toString()

            // Walidacja pól
            if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                editTextEmail.error = "Podaj poprawny adres email"
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                editTextPassword.error = "Podaj hasło"
                return@setOnClickListener
            }

            if (reenterPassword.isEmpty()) {
                editTextReenterPassword.error = "Powtórz hasło"
                return@setOnClickListener
            }

            if (password != reenterPassword) {
                editTextReenterPassword.error = "Hasła nie są identyczne"
                return@setOnClickListener
            }

            // Symulacja rejestracji (tutaj można dodać rzeczywistą logikę rejestracji)
            registerUser(email, password)
        }
        buttonRegister.setOnClickListener {
            // Przekierowanie do ekranu rejestracji
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        buttonLogin.setOnClickListener {
            // Przekierowanie do ekranu rejestracji
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        buttonCalendar.setOnClickListener {
            // Przekierowanie do ekranu rejestracji
            val intent = Intent(this, CalendarActivity::class.java)
            startActivity(intent)
        }
    }

    private fun registerUser(email: String, password: String) {
        // Tutaj można dodać logikę do rejestracji użytkownika, np. zapytanie do API, zapis do bazy danych, itp.

        // Przykład symulacji udanej rejestracji
        Toast.makeText(this, "Udana rejestracja", Toast.LENGTH_SHORT).show()

        // Po udanej rejestracji można np. przekierować użytkownika do ekranu logowania
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish() // Zamykamy aktywność rejestracji, aby użytkownik nie mógł wrócić do niej przyciskiem "Back"
    }

}
