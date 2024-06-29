package com.example.nietypowykalendarz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class ThemeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Logika zmiany motywu aplikacji na Dark Mode
        // Tutaj zakładamy, że zmiana nastąpi po kliknięciu opcji w Navigation Drawer
        // Możesz tutaj dodać logikę zmiany kolorystyki lub motywu

        // Przykład: Ustawienie aplikacji na Dark Mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        // Zakończenie aktywności
        finish()
    }
}