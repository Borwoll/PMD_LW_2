package com.example.pmd_lw_2

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.system.exitProcess

class ContentRecipeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myListData: Map<String, Array<ListData>> = mapOf(
            "first_bean_soup" to arrayOf<ListData>(ListData(getString(R.string.first_bean_soup), getString(R.string.first_bean_soup), R.mipmap.first_bean_soup_foreground, getString(R.string.first_bean_soup_content))),
            "first_pumpkin_soup" to arrayOf<ListData>(ListData(getString(R.string.first_pumpkin_soup), getString(R.string.first_pumpkin_soup), R.mipmap.first_pumpkin_soup_foreground, getString(R.string.first_pumpkin_soup_content))),
            "first_tomato_soup" to arrayOf<ListData>(ListData(getString(R.string.first_tomato_soup), getString(R.string.first_tomato_soup), R.mipmap.first_tomato_soup_foreground, getString(R.string.first_tomato_soup_content))),
            "first_cabbage_soup" to arrayOf<ListData>(ListData(getString(R.string.first_cabbage_soup), getString(R.string.first_cabbage_soup), R.mipmap.first_cabbage_soup_foreground, getString(R.string.first_cabbage_soup_content))),
            "second_potato_boats" to arrayOf<ListData>(ListData(getString(R.string.second_potato_boats), getString(R.string.second_potato_boats), R.mipmap.second_potato_boats_foreground, getString(R.string.second_potato_boats_content))),
            "second_meatballs" to arrayOf<ListData>(ListData(getString(R.string.second_meatballs), getString(R.string.second_meatballs), R.mipmap.second_meatballs_foreground, getString(R.string.second_meatballs_content))),
            "second_vegetables" to arrayOf<ListData>(ListData(getString(R.string.second_vegetables), getString(R.string.second_vegetables), R.mipmap.second_vegetables_foreground, getString(R.string.second_vegetables_content))),
            "second_porridge" to arrayOf<ListData>(ListData(getString(R.string.second_porridge), getString(R.string.second_porridge), R.mipmap.second_porridge_foreground, getString(R.string.second_porridge_content))),
            "third_avocado" to arrayOf<ListData>(ListData(getString(R.string.third_avocado), getString(R.string.third_avocado), R.mipmap.third_avocado_foreground, getString(R.string.third_avocado_content))),
            "thid_winter" to arrayOf<ListData>(ListData(getString(R.string.thid_winter), getString(R.string.thid_winter), R.mipmap.thid_winter_foreground, getString(R.string.thid_winter_content))),
            "third_apple" to arrayOf<ListData>(ListData(getString(R.string.third_apple), getString(R.string.third_apple), R.mipmap.third_apple_foreground, getString(R.string.third_apple_content))),
            "third_spring" to arrayOf<ListData>(ListData(getString(R.string.third_spring), getString(R.string.third_spring), R.mipmap.third_spring_foreground, getString(R.string.third_spring_content))),
            "four_new_year" to arrayOf<ListData>(ListData(getString(R.string.four_new_year), getString(R.string.four_new_year), R.mipmap.four_new_year_foreground, getString(R.string.four_new_year_content))),
            "four_tea" to arrayOf<ListData>(ListData(getString(R.string.four_tea), getString(R.string.four_tea), R.mipmap.four_tea_foreground, getString(R.string.four_tea_content))),
            "four_coconut" to arrayOf<ListData>(ListData(getString(R.string.four_coconut), getString(R.string.four_coconut), R.mipmap.four_coconut_foreground, getString(R.string.four_coconut_content))),
            "four_milk" to arrayOf<ListData>(ListData(getString(R.string.four_milk), getString(R.string.four_milk), R.mipmap.four_milk_foreground, getString(R.string.four_milk_content))))

        var adapter = myListData[intent.getStringExtra("slug")]?.let { ListAdapter(it, R.layout.content_recipe_item) }
        val recyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}