package com.example.nietypowykalendarz

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button
    private lateinit var buttonLoginToRegister: Button
    private lateinit var buttonLoginToCalendar: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editTextEmail = findViewById(R.id.edit_text_email)
        editTextPassword = findViewById(R.id.edit_text_password)
        buttonLogin = findViewById(R.id.button_login)
        buttonLoginToRegister = findViewById(R.id.button_register)
        buttonLoginToCalendar = findViewById(R.id.button_Calendar)

        buttonLogin.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            // Walidacja pól
            if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                editTextEmail.error = "Podaj poprawny adres email"
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                editTextPassword.error = "Podaj hasło"
                return@setOnClickListener
            }

            // Wysyłanie żądania logowania
            loginUser(email, password)
        }

        buttonLoginToRegister.setOnClickListener {
            // Przekierowanie do ekranu rejestracji
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        buttonLoginToCalendar.setOnClickListener {
            // Przekierowanie do ekranu Kalendarza
            val intent = Intent(this, CalendarActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loginUser(email: String, password: String) {
        val request = LoginRequest(email, password)
        Log.d("LoginActivity", "Sending login request: $request")
        val call = RetrofitClient.instance.loginUser(request)

        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    // Udane logowanie - przekierowanie do głównej aktywności aplikacji
                    val intent = Intent(this@LoginActivity, ProfileActivity::class.java)
                    startActivity(intent)
                    finish() // Zamykamy aktywność logowania, aby użytkownik nie mógł wrócić do niej przyciskiem "Back"
                } else {
                    // Nieudane logowanie - wyświetlenie komunikatu błędu
                    Log.e("LoginActivity", "Login failed with code: ${response.code()}")
                    Toast.makeText(
                        this@LoginActivity,
                        "Nieprawidłowy email lub hasło",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                // Błąd sieci lub inne wyjątki
                Log.e("LoginActivity", "Network error: ${t.message}")
                Toast.makeText(this@LoginActivity, "Błąd sieci: ${t.message}", Toast.LENGTH_SHORT)
                    .show()
                Log.e("LoginActivity", "Login failed: ${t.message}")
            }
        })
    }
}
