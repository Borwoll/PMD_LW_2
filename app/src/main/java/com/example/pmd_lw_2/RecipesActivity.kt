package com.example.pmd_lw_2

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.system.exitProcess

class RecipesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myListData1: Array<ListData> = arrayOf<ListData>(
            ListData(getString(R.string.first_bean_soup_slug), getString(R.string.first_bean_soup), R.mipmap.first_bean_soup_foreground),
            ListData(getString(R.string.first_pumpkin_soup_slug), getString(R.string.first_pumpkin_soup), R.mipmap.first_pumpkin_soup_foreground),
            ListData(getString(R.string.first_tomato_soup_slug), getString(R.string.first_tomato_soup), R.mipmap.first_tomato_soup_foreground),
            ListData(getString(R.string.first_cabbage_soup_slug), getString(R.string.first_cabbage_soup), R.mipmap.first_cabbage_soup_foreground)
        )

        val myListData2: Array<ListData> = arrayOf<ListData>(
            ListData(getString(R.string.second_potato_boats_slug), getString(R.string.second_potato_boats), R.mipmap.second_potato_boats_foreground),
            ListData(getString(R.string.second_meatballs_slug), getString(R.string.second_meatballs), R.mipmap.second_meatballs_foreground),
            ListData(getString(R.string.second_vegetables_slug), getString(R.string.second_vegetables), R.mipmap.second_vegetables_foreground),
            ListData(getString(R.string.second_porridge_slug), getString(R.string.second_porridge), R.mipmap.second_porridge_foreground)
        )

        val myListData3: Array<ListData> = arrayOf<ListData>(
            ListData(getString(R.string.third_avocado_slug), getString(R.string.third_avocado), R.mipmap.third_avocado_foreground),
            ListData(getString(R.string.thid_winter_slug), getString(R.string.thid_winter), R.mipmap.thid_winter_foreground),
            ListData(getString(R.string.third_apple_slug), getString(R.string.third_apple), R.mipmap.third_apple_foreground),
            ListData(getString(R.string.third_spring_slug),getString(R.string.third_spring), R.mipmap.third_spring_foreground)
        )

        val myListData4: Array<ListData> = arrayOf<ListData>(
            ListData(getString(R.string.four_new_year_slug), getString(R.string.four_new_year), R.mipmap.four_new_year_foreground),
            ListData(getString(R.string.four_tea_slug), getString(R.string.four_tea), R.mipmap.four_tea_foreground),
            ListData(getString(R.string.four_coconut_slug), getString(R.string.four_coconut), R.mipmap.four_coconut_foreground),
            ListData(getString(R.string.four_milk_slug), getString(R.string.four_milk), R.mipmap.four_milk_foreground)
        )

        val slug = intent.getStringExtra("slug")
        var adapter = ListAdapter(myListData1, R.layout.list_item)
        when (slug) {
            getString(R.string.first_slug) -> {
                adapter = ListAdapter(myListData1, R.layout.list_item)
            }
            getString(R.string.second_slug) -> {
                adapter = ListAdapter(myListData2, R.layout.list_item)
            }
            getString(R.string.third_slug) -> {
                adapter = ListAdapter(myListData3, R.layout.list_item)
            }
            getString(R.string.four_slug) -> {
                adapter = ListAdapter(myListData4, R.layout.list_item)
            }
            else -> {
                exitProcess(0);
            }
        }

        val recyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}