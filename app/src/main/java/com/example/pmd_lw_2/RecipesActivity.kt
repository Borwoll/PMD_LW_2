package com.example.pmd_lw_2

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject
import org.json.JSONTokener
import java.io.ByteArrayOutputStream
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

        val categoryID = intent.getStringExtra("id")
        val description = intent.getStringExtra("description")
        Thread(Runnable {
            try {
                val url = URL("https://www.themealdb.com/api/json/v1/1/filter.php?c=$description")
                val mealsObject = JSONTokener(url.readText()).nextValue() as JSONObject
                val mealsArray = mealsObject.getJSONArray("meals")
                for (i in 0 until mealsArray.length()) {
                    val strMeal = mealsArray.getJSONObject(i).getString("strMeal")
                    val strMealThumb = mealsArray.getJSONObject(i).getString("strMealThumb")
                    val idMeal = mealsArray.getJSONObject(i).getString("idMeal")
                    myListData += ListData(idMeal, strMeal, strMealThumb)
                }
            } catch (e: Exception) {
                val db = baseContext.openOrCreateDatabase("app.db", MODE_PRIVATE, null)
                val query = db.rawQuery("SELECT * FROM recipes WHERE category_id=?", arrayOf(categoryID))
                if (query.count != 0) {
                    while (query.moveToNext()) {
                        val id = query.getString(0)
                        val text = query.getString(2)
                        val image = query.getString(3)
                        myListData += ListData(id, text, image)
                    }
                    query.close()
                } else
                    e.printStackTrace()
                db.close()
            }
            runOnUiThread {
                val adapterNewDataSet = itemClickList?.let { ListAdapter(myListData, R.layout.list_item, it) }
                recyclerView.swapAdapter(adapterNewDataSet, true);
            }
        }).start()
    }
}