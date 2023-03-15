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
        setContentView(R.layout.activity_recipes)
        val myListData1: Array<ListData> = arrayOf<ListData>(
            ListData("first_bean_soup", getString(R.string.first_bean_soup), R.mipmap.first_bean_soup_foreground),
            ListData("first_pumpkin_soup", getString(R.string.first_pumpkin_soup), R.mipmap.first_pumpkin_soup_foreground),
            ListData("first_tomato_soup", getString(R.string.first_tomato_soup), R.mipmap.first_tomato_soup_foreground),
            ListData("first_cabbage_soup", getString(R.string.first_cabbage_soup), R.mipmap.first_cabbage_soup_foreground)
        )

        val myListData2: Array<ListData> = arrayOf<ListData>(
            ListData("second_potato_boats", getString(R.string.second_potato_boats), R.mipmap.second_potato_boats_foreground),
            ListData("second_meatballs", getString(R.string.second_meatballs), R.mipmap.second_meatballs_foreground),
            ListData("second_vegetables", getString(R.string.second_vegetables), R.mipmap.second_vegetables_foreground),
            ListData("second_porridge", getString(R.string.second_porridge), R.mipmap.second_porridge_foreground)
        )

        val myListData3: Array<ListData> = arrayOf<ListData>(
            ListData("third_avocado", getString(R.string.third_avocado), R.mipmap.third_avocado_foreground),
            ListData("thid_winter", getString(R.string.thid_winter), R.mipmap.thid_winter_foreground),
            ListData("third_apple", getString(R.string.third_apple), R.mipmap.third_apple_foreground),
            ListData("third_spring",getString(R.string.third_spring), R.mipmap.third_spring_foreground)
        )

        val myListData4: Array<ListData> = arrayOf<ListData>(
            ListData("four_new_year", getString(R.string.four_new_year), R.mipmap.four_new_year_foreground),
            ListData("four_tea", getString(R.string.four_tea), R.mipmap.four_tea_foreground),
            ListData("four_coconut", getString(R.string.four_coconut), R.mipmap.four_coconut_foreground),
            ListData("four_milk", getString(R.string.four_milk), R.mipmap.four_milk_foreground)
        )

        val slug = intent.getStringExtra("slug")
        var adapter = ListAdapter(myListData1)
        when (slug) {
            "first" -> {
                adapter = ListAdapter(myListData1)
            }
            "second" -> {
                adapter = ListAdapter(myListData2)
            }
            "salad" -> {
                adapter = ListAdapter(myListData3)
            }
            "drinks" -> {
                adapter = ListAdapter(myListData4)
            }
            else -> {
                exitProcess(0);
            }
        }

        val recyclerView = findViewById<View>(R.id.recyclerView2) as RecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}