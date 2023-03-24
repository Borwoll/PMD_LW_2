package com.example.pmd_lw_2

import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.*
import java.io.InputStream
import java.net.URL


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        var myListData: Array<ListData> = arrayOf<ListData>()

        try {
            val url = URL("https://www.themealdb.com/api/json/v1/1/categories.php")
            val categoriesObject = JSONTokener(url.readText()).nextValue() as JSONObject
            val categoriesArray = categoriesObject.getJSONArray("categories")
            for (i in 0 until categoriesArray.length()) {
                val idCategory = categoriesArray.getJSONObject(i).getString("idCategory")
                val strCategory = categoriesArray.getJSONObject(i).getString("strCategory")
                val strCategoryThumb = categoriesArray.getJSONObject(i).getString("strCategoryThumb")
                val bitmap = BitmapFactory.decodeStream(URL(strCategoryThumb).content as InputStream)
                myListData += ListData(idCategory, strCategory, bitmap)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val recyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView
        val factory = ItemClickListFactory()
        val itemClickList = factory.makeItemClickList(ItemType.Recipes)
        val adapter = itemClickList?.let { ListAdapter(myListData, R.layout.list_item, it) }
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}