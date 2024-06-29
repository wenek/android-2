package com.example.nietypowykalendarz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DayAdapter(private val holidays: List<Holiday>) :
    RecyclerView.Adapter<DayAdapter.HolidayViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolidayViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_holiday, parent, false)
        return HolidayViewHolder(view)
    }

    override fun onBindViewHolder(holder: HolidayViewHolder, position: Int) {
        val holiday = holidays[position]
        holder.bind(holiday)
    }

    override fun getItemCount(): Int {
        return holidays.size
    }

    class HolidayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textHolidayName: TextView = itemView.findViewById(R.id.text_holiday_name)

        fun bind(holiday: Holiday) {
            textHolidayName.text = holiday.name
        }
    }
}
