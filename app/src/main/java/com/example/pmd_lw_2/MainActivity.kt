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
            ListData("first", getString(R.string.first_category), R.mipmap.catalog_1_foreground),
            ListData("second",getString(R.string.second_category), R.mipmap.catalog_2_foreground),
            ListData("salad", getString(R.string.third_category), R.mipmap.catalog_6_foreground),
            ListData("drinks", getString(R.string.four_category), R.mipmap.catalog_9_foreground)
        )
        val recyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView
        val adapter = ListAdapter(myListData)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}