package com.example.nietypowykalendarz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.nio.charset.Charset

class HolidaysActivity2 : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var holidaysAdapter: HolidaysAdapter2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_holidays)

        recyclerView = findViewById(R.id.recycler_view_holidays)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val holidays = loadHolidaysFromJson()
        val holidaysByDay = holidays.groupBy { it.day }

        holidaysAdapter = HolidaysAdapter2(holidaysByDay)
        recyclerView.adapter = holidaysAdapter
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
