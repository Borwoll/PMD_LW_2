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
            ListData("Первые блюда", R.mipmap.catalog_1_foreground),
            ListData("Вторые блюда", R.mipmap.catalog_2_foreground),
            ListData("Салаты", R.mipmap.catalog_6_foreground),
            ListData("Закуски", R.mipmap.catalog_3_foreground),
            ListData("Десерты", R.mipmap.catalog_5_foreground),
            ListData("Выпечка", R.mipmap.catalog_4_foreground),
            ListData("Напитки", R.mipmap.catalog_9_foreground)
        )
        val recyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView
        val adapter = ListAdapter(myListData)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}