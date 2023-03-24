package com.example.pmd_lw_2

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject
import org.json.JSONTokener
import java.io.InputStream
import java.net.URL

class RecipesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var myListData: Array<ListData> = arrayOf<ListData>()
        val factory = ItemClickListFactory()
        val itemClickList = factory.makeItemClickList(ItemType.ContentRecipe)
        val adapter = itemClickList?.let { ListAdapter(myListData, R.layout.list_item, it) }
        val recyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        Thread(Runnable {
            Thread.sleep(1000)
            runOnUiThread {
                getNewDataSet(recyclerView, itemClickList)
            }
        }).start()
    }

    private fun getNewDataSet(recyclerView: RecyclerView, itemClickList: CategoryItemClickList?) {
        var myListData: Array<ListData> = arrayOf<ListData>()
        val description = intent.getStringExtra("description")
        try {
            val url = URL("https://www.themealdb.com/api/json/v1/1/filter.php?c=$description")
            val mealsObject = JSONTokener(url.readText()).nextValue() as JSONObject
            val mealsArray = mealsObject.getJSONArray("meals")
            for (i in 0 until mealsArray.length()) {
                val strMeal = mealsArray.getJSONObject(i).getString("strMeal")
                val strMealThumb = mealsArray.getJSONObject(i).getString("strMealThumb")
                val idMeal = mealsArray.getJSONObject(i).getString("idMeal")
                val bitmap = BitmapFactory.decodeStream(URL(strMealThumb).content as InputStream)
                myListData += ListData(idMeal, strMeal, bitmap)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val adapterNewDataSet = itemClickList?.let { ListAdapter(myListData, R.layout.list_item, it) }
        recyclerView.swapAdapter(adapterNewDataSet, true);
    }
}