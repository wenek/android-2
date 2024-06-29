package com.example.nietypowykalendarz

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicjalizacja Navigation Drawer
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        // Inicjalizacja ActionBarDrawerToggle do obsługi otwierania/zamykania Navigation Drawer
        val toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Obsługa kliknięć elementów Navigation Drawer
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_theme -> {
                    // Wyświetlenie powiadomienia Toast z informacją o wybraniu opcji Tematy
                    showToast("Wybrano opcję Tematy")
                }
                R.id.nav_settings -> {
                    // Wyświetlenie powiadomienia Toast z informacją o wybraniu opcji Ustawienia
                    showToast("Wybrano opcję Ustawienia")
                }
            }
            // Zamknięcie Navigation Drawer po wybraniu opcji
            drawerLayout.closeDrawers()
            true
        }

        // Obsługa kliknięć przycisków głównego widoku
        val buttonLogin = findViewById<Button>(R.id.button_login)
        buttonLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val buttonRegister = findViewById<Button>(R.id.button_register)
        buttonRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        val buttonContinue = findViewById<Button>(R.id.button_Calendar)
        buttonContinue.setOnClickListener {
            val intent = Intent(this, CalendarActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    // Obsługa kliknięcia przycisku w ActionBarze, aby otworzyć Navigation Drawer
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                drawerLayout.openDrawer(navView)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Obsługa naciśnięcia przycisku cofania w przypadku otwartego Navigation Drawer
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(navView)) {
            drawerLayout.closeDrawer(navView)
        } else {
            super.onBackPressed()
        }
    }
}
