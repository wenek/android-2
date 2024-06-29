package com.example.nietypowykalendarz

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nietypowykalendarz.Holiday
import com.example.nietypowykalendarz.databinding.ItemHolidayBinding

class HolidaysAdapter : RecyclerView.Adapter<HolidaysAdapter.HolidayViewHolder>() {

    private var holidaysList: List<Holiday> = listOf()

    inner class HolidayViewHolder(private val binding: ItemHolidayBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(holiday: Holiday) {
            binding.textHolidayName.text = holiday.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolidayViewHolder {
        val binding = ItemHolidayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HolidayViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HolidayViewHolder, position: Int) {
        holder.bind(holidaysList[position])
    }

    override fun getItemCount(): Int {
        return holidaysList.size
    }

    fun submitList(holidays: List<Holiday>) {
        holidaysList = holidays
        notifyDataSetChanged()
    }
}
