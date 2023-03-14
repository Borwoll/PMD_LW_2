package com.example.pmd_lw_2

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myListData: Array<ListData> = arrayOf<ListData>(
            ListData("Блюда из курицы", R.drawable.add_box_foreground)
        )
        val recyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView
        val adapter = ListAdapter(myListData)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}