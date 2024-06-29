package com.example.nietypowykalendarz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class CalendarViewModel : ViewModel() {
    private val _currentDate = MutableLiveData<String>()
    val currentDate: LiveData<String> get() = _currentDate

    private val _holidays = MutableLiveData<List<Holiday>>()
    val holidays: LiveData<List<Holiday>> get() = _holidays

    private val calendar = Calendar.getInstance()
    private var allHolidays = listOf<Holiday>()

    init {
        updateCurrentDate()
    }

    fun setHolidays(holidays: List<Holiday>) {
        allHolidays = holidays
        updateHolidaysForCurrentDate()
    }

    fun changeDateBy(days: Int) {
        calendar.add(Calendar.DAY_OF_MONTH, days)
        updateCurrentDate()
        updateHolidaysForCurrentDate()
    }

    private fun updateCurrentDate() {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val currentDateStr = sdf.format(calendar.time)
        _currentDate.value = currentDateStr
    }

    private fun updateHolidaysForCurrentDate() {
        val currentMonth = calendar.get(Calendar.MONTH) + 1 // Miesiące są indeksowane od 0
        val currentDay = calendar.get(Calendar.DAY_OF_MONTH)

        val filteredHolidays = allHolidays.filter { it.month == currentMonth && it.day == currentDay }
        _holidays.value = filteredHolidays
    }
}

