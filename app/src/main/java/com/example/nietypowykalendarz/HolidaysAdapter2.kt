package com.example.nietypowykalendarz

// HolidaysAdapter2.kt

// HolidaysAdapter2.kt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HolidaysAdapter2(private val monthsWithHolidays: Map<String, List<Holiday>>) :
    RecyclerView.Adapter<HolidaysAdapter2.MonthViewHolder>() {

    private val monthList = monthsWithHolidays.keys.toList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonthViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_day_holidays, parent, false)
        return MonthViewHolder(view)
    }

    override fun onBindViewHolder(holder: MonthViewHolder, position: Int) {
        val month = monthList[position]
        val holidays = monthsWithHolidays[month] ?: return

        holder.bind(month, holidays)
    }

    override fun getItemCount(): Int {
        return monthList.size
    }

    class MonthViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textMonth: TextView = itemView.findViewById(R.id.text_month)
        private val recyclerViewDays: RecyclerView = itemView.findViewById(R.id.recycler_view_days)

        fun bind(month: String, holidays: List<Holiday>) {
            textMonth.text = month

            val layoutManager = LinearLayoutManager(itemView.context)
            recyclerViewDays.layoutManager = layoutManager

            val dayAdapter = DayAdapter(holidays)
            recyclerViewDays.adapter = dayAdapter
        }
    }
}
