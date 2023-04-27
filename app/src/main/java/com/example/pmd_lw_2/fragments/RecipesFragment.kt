package com.example.pmd_lw_2.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pmd_lw_2.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import org.json.JSONTokener
import java.net.URL

class RecipesFragment : Fragment() {
    override fun onCreateView(inflater: android.view.LayoutInflater, container: android.view.ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var myListData: Array<ListData> = arrayOf<ListData>()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycleView)
        val factory = ItemClickListFactory()
        val itemClickList = factory.makeItemClickList(ItemType.ContentRecipe)
        val adapter = itemClickList?.let { ListAdapter(myListData, R.layout.items_list, it) }
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        val categoryID = requireActivity().intent.extras!!.getString("id")
        val description = requireActivity().intent.extras!!.getString("description")
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val url = URL("https://www.themealdb.com/api/json/v1/1/filter.php?c=$description")
                val mealsObject = JSONTokener(url.readText()).nextValue() as JSONObject
                val mealsArray = mealsObject.getJSONArray("meals")
                myListData = Array(mealsArray.length()) { i ->
                    val idMeal = mealsArray.getJSONObject(i).getString("idMeal")
                    val strMeal = mealsArray.getJSONObject(i).getString("strMeal")
                    val strMealThumb =
                        mealsArray.getJSONObject(i).getString("strMealThumb")
                    ListData(idMeal, strMeal, strMealThumb)
                }
            } catch (e: Exception) {
                val db = requireContext().openOrCreateDatabase("app.db", Context.MODE_PRIVATE, null)
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

            withContext(Dispatchers.Main) {
                val newAdapter = itemClickList?.let { ListAdapter(myListData, R.layout.items_list, it) }
                recyclerView.swapAdapter(newAdapter, true)
            }
        }
    }
}