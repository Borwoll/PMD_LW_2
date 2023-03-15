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
            ListData(getString(R.string.first_slug), getString(R.string.first_category), R.mipmap.catalog_1_foreground),
            ListData(getString(R.string.second_slug),getString(R.string.second_category), R.mipmap.catalog_2_foreground),
            ListData(getString(R.string.third_slug), getString(R.string.third_category), R.mipmap.catalog_6_foreground),
            ListData(getString(R.string.four_slug), getString(R.string.four_category), R.mipmap.catalog_9_foreground)
        )
        val recyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView
        val adapter = ListAdapter(myListData, R.layout.list_item)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}