package com.example.nietypowykalendarz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HolidaysActivity2 : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var holidaysAdapter: HolidaysAdapter2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_holidays)

        recyclerView = findViewById(R.id.recycler_view_holidays)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}

