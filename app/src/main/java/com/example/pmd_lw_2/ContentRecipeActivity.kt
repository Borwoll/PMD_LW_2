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

class ContentRecipeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var myListData: Array<ListData> = arrayOf<ListData>()
        val factory = ItemClickListFactory()
        val itemClickList = factory.makeItemClickList(ItemType.ContentRecipe)
        val recyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = itemClickList?.let { ListAdapter(myListData, R.layout.content_recipe_item, it) }

        val recipeID = intent.getStringExtra("id")
        Thread(Runnable {
            try {
                val url = URL("https://www.themealdb.com/api/json/v1/1/lookup.php?i=$recipeID")
                val text = url.readText()
                val mealsObject = JSONTokener(url.readText()).nextValue() as JSONObject
                val mealsArray = mealsObject.getJSONArray("meals")
                Log.e("test", "test")
                for (i in 0 until mealsArray.length()) {
                    val idMeal = mealsArray.getJSONObject(i).getString("idMeal")
                    val strMeal = mealsArray.getJSONObject(i).getString("strMeal")
                    val strMealThumb = mealsArray.getJSONObject(i).getString("strMealThumb")
                    val strInstructions = mealsArray.getJSONObject(i).getString("strInstructions")
                    myListData += ListData(idMeal, strMeal, strMealThumb, strInstructions)
                }
            } catch (e: Exception) {
                val db = baseContext.openOrCreateDatabase("app.db", MODE_PRIVATE, null)
                val query = db.rawQuery("SELECT * FROM contentRecipe WHERE id=?", arrayOf(recipeID))
                if (query.count != 0) {
                    while (query.moveToNext()) {
                        val id = query.getString(0)
                        val text = query.getString(2)
                        val content = query.getString(3)
                        val image = query.getString(4)
                        myListData += ListData(id, text, image, content)
                    }
                    query.close()
                } else
                    e.printStackTrace()
                db.close()
            }
            runOnUiThread {
                val adapterNewDataSet = itemClickList?.let { ListAdapter(myListData, R.layout.content_recipe_item, it) }
                recyclerView.swapAdapter(adapterNewDataSet, true);
            }
        }).start()
    }
}