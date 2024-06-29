package com.example.nietypowykalendarz


import android.content.Context
import org.json.JSONArray

object HolidayUtils {

    fun loadHolidays(context: Context): List<Holiday> {
        val inputStream = context.resources.openRawResource(R.raw.holidays)
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        val jsonArray = JSONArray(jsonString)

        val holidays = mutableListOf<Holiday>()
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val id = jsonObject.getInt("id")
            val name = jsonObject.getString("name")
            val month = jsonObject.getInt("month")
            val day = jsonObject.getInt("day")
            val holiday = Holiday(id, name, month, day)
            holidays.add(holiday)
        }

        return holidays
    }
}
