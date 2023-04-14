package com.example.pmd_lw_2

import android.R.attr
import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.util.Base64
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject
import org.json.JSONTokener
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.net.URL


class MainActivity : AppCompatActivity() {
    @SuppressLint("Recycle")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        var myListData: Array<ListData> = arrayOf<ListData>()
        val recyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView
        val factory = ItemClickListFactory()
        val itemClickList = factory.makeItemClickList(ItemType.Recipes)
        val adapter = itemClickList?.let { ListAdapter(myListData, R.layout.list_item, it) }
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        var hasErrorConnection = false

        Thread(Runnable {
            try {
                val url = URL("https://www.themealdb.com/api/json/v1/1/categories.php")
                val categoriesObject = JSONTokener(url.readText()).nextValue() as JSONObject
                val categoriesArray = categoriesObject.getJSONArray("categories")
                for (i in 0 until categoriesArray.length()) {
                    val idCategory = categoriesArray.getJSONObject(i).getString("idCategory")
                    val strCategory = categoriesArray.getJSONObject(i).getString("strCategory")
                    val strCategoryThumb = categoriesArray.getJSONObject(i).getString("strCategoryThumb")
                    myListData += ListData(idCategory, strCategory, strCategoryThumb)
                }
            } catch (e: Exception) {
                hasErrorConnection = true
                val db = baseContext.openOrCreateDatabase("app.db", MODE_PRIVATE, null)
                val query = db.rawQuery("SELECT * FROM categories", null)
                if (query.count != 0) {
                    while (query.moveToNext()) {
                        val id = query.getString(0)
                        val text = query.getString(1)
                        val image = query.getString(2)
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

            if (!hasErrorConnection) {
                val db = baseContext.openOrCreateDatabase("app.db", MODE_PRIVATE, null)
                db.execSQL("CREATE TABLE IF NOT EXISTS categories (id INTEGER PRIMARY KEY, text TEXT, image TEXT)")
                db.execSQL("CREATE TABLE IF NOT EXISTS recipes (id INTEGER PRIMARY KEY, category_id INTEGER, text TEXT, image TEXT, FOREIGN KEY (category_id) REFERENCES categories(id))")
                db.execSQL("CREATE TABLE IF NOT EXISTS contentRecipe (id INTEGER PRIMARY KEY, recipe_id INTEGER, text TEXT, content TEXT, image TEXT, FOREIGN KEY (recipe_id) REFERENCES recipes(id))")

                val categoryURL = URL("https://www.themealdb.com/api/json/v1/1/categories.php")
                val categoriesObject = JSONTokener(categoryURL.readText()).nextValue() as JSONObject
                val categoriesArray = categoriesObject.getJSONArray("categories")
                for (i in 0 until categoriesArray.length()) {
                    val idCategory = categoriesArray.getJSONObject(i).getString("idCategory")
                    val strCategory = categoriesArray.getJSONObject(i).getString("strCategory")
                    val strCategoryThumb = categoriesArray.getJSONObject(i).getString("strCategoryThumb")

                    val query = db.rawQuery("SELECT * FROM categories WHERE id=?", arrayOf(idCategory))
                    if (query.count == 0) {
                        val url = URL(strCategoryThumb)
                        val inputStream: InputStream = url.openStream()
                        val bitmap = BitmapFactory.decodeStream(inputStream)

                        val baos = ByteArrayOutputStream()
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
                        val b = baos.toByteArray()

                        db.execSQL("INSERT INTO categories (id, text, image) VALUES (?, ?, ?)", arrayOf(idCategory, strCategory,
                            Base64.encodeToString(b, Base64.DEFAULT)))
                    }

                    val mealsUrl = URL("https://www.themealdb.com/api/json/v1/1/filter.php?c=$strCategory")
                    val mealsObject = JSONTokener(mealsUrl.readText()).nextValue() as JSONObject
                    val mealsArray = mealsObject.getJSONArray("meals")
                    for (j in 0 until mealsArray.length()) {
                        val idMeal = mealsArray.getJSONObject(j).getString("idMeal")
                        val strMeal = mealsArray.getJSONObject(j).getString("strMeal")
                        val strMealThumb = mealsArray.getJSONObject(j).getString("strMealThumb")

                        val queryMeals = db.rawQuery("SELECT * FROM recipes WHERE id=?", arrayOf(idMeal))
                        if (queryMeals.count == 0) {
                            val url = URL(strMealThumb)
                            val inputStream: InputStream = url.openStream()
                            val bitmap = BitmapFactory.decodeStream(inputStream)

                            val baos = ByteArrayOutputStream()
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
                            val b = baos.toByteArray()

                            db.execSQL("INSERT INTO recipes (id, category_id, text, image) VALUES (?, ?, ?, ?)", arrayOf(idMeal, idCategory, strMeal,
                                Base64.encodeToString(b, Base64.DEFAULT)))
                        }

                        val contentUrl = URL("https://www.themealdb.com/api/json/v1/1/lookup.php?i=$idMeal")
                        val contentMealsObject = JSONTokener(contentUrl.readText()).nextValue() as JSONObject
                        val contentMealsArray = contentMealsObject.getJSONArray("meals")
                        val contentIdMeal = contentMealsArray.getJSONObject(0).getString("idMeal")
                        val contentStrMeal = contentMealsArray.getJSONObject(0).getString("strMeal")
                        val contentStrMealThumb = contentMealsArray.getJSONObject(0).getString("strMealThumb")
                        val contentStrInstructions = contentMealsArray.getJSONObject(0).getString("strInstructions")

                        val contentQuery = db.rawQuery("SELECT * FROM contentRecipe WHERE id=?", arrayOf(contentIdMeal))
                        if (contentQuery.count == 0) {
                            val url = URL(contentStrMealThumb)
                            val inputStream: InputStream = url.openStream()
                            val bitmap = BitmapFactory.decodeStream(inputStream)

                            val baos = ByteArrayOutputStream()
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
                            val b = baos.toByteArray()

                            db.execSQL("INSERT INTO contentRecipe (id, recipe_id, text, content, image) VALUES (?, ?, ?, ?, ?)", arrayOf(contentIdMeal, idMeal, contentStrMeal, contentStrInstructions,
                                Base64.encodeToString(b, Base64.DEFAULT)))
                        }
                    }
                }
                db.close()
            }
        }).start()
    }
}