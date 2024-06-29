package com.example.nietypowykalendarz

// CalendarActivity.kt

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nietypowykalendarz.databinding.ActivityCalendarBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.nio.charset.Charset

class CalendarActivity : AppCompatActivity() {

    private lateinit var viewModel: CalendarViewModel
    private lateinit var holidaysAdapter: HolidaysAdapter
    private lateinit var binding: ActivityCalendarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(CalendarViewModel::class.java)
        setupRecyclerView()

        viewModel.currentDate.observe(this, Observer { currentDate ->
            binding.textCurrentDate.text = currentDate
        })

        viewModel.holidays.observe(this, Observer { holidays ->
            holidaysAdapter.submitList(holidays)
        })

        binding.buttonPrevDay.setOnClickListener {
            viewModel.changeDateBy(-1)
        }

        binding.buttonNextDay.setOnClickListener {
            viewModel.changeDateBy(1)
        }

        // Ładowanie danych
        val holidays = loadHolidaysFromJson()
        viewModel.setHolidays(holidays)

        // Obsługa kliknięcia przycisku View Holidays
        binding.buttonViewHolidays.setOnClickListener {
            startActivity(Intent(this, HolidaysActivity2::class.java))
        }
    }

    private fun setupRecyclerView() {
        holidaysAdapter = HolidaysAdapter()
        binding.recyclerViewHolidays.apply {
            adapter = holidaysAdapter
            layoutManager = LinearLayoutManager(this@CalendarActivity)
        }
    }

    private fun loadHolidaysFromJson(): List<Holiday> {
        val json: String?
        try {
            val inputStream = resources.openRawResource(R.raw.holidays)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charset.defaultCharset())
        } catch (ex: IOException) {
            ex.printStackTrace()
            return emptyList()
        }

        val type = object : TypeToken<List<Holiday>>() {}.type
        return Gson().fromJson(json, type)
    }
}
