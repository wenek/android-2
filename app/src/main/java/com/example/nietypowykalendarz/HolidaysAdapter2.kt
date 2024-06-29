package com.example.nietypowykalendarz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HolidaysAdapter2(private val holidaysByDay: Map<Int, List<Holiday>>) :
    RecyclerView.Adapter<HolidaysAdapter2.DayViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_day_holidays, parent, false)
        return DayViewHolder(view)
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        val day = holidaysByDay.keys.elementAt(position)
        val holidays = holidaysByDay[day] ?: return

        // Ustawienie widoczności i tekstu dla miesiąca
        if (position == 0) {
            holder.textMonth.visibility = View.VISIBLE
        } else {
            holder.textMonth.visibility = View.GONE
        }

        holder.bind(day, holidays)
    }

    override fun getItemCount(): Int {
        return holidaysByDay.size
    }

    class DayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textMonth: TextView = itemView.findViewById(R.id.text_month)
        private val textDay: TextView = itemView.findViewById(R.id.text_day)
        private val recyclerViewHolidays: RecyclerView = itemView.findViewById(R.id.recycler_view_days)

        fun bind(day: Int, holidays: List<Holiday>) {
            textDay.text = "Dzień $day:"

            val layoutManager = LinearLayoutManager(itemView.context)
            recyclerViewHolidays.layoutManager = layoutManager

            val dayAdapter = DayAdapter(holidays)
            recyclerViewHolidays.adapter = dayAdapter
        }
    }
}
