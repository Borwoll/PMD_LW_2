package com.example.pmd_lw_2

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.io.InputStream
import java.net.URL
import android.util.Base64
import android.util.Base64.DEFAULT
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ListAdapter(private var _listData: Array<ListData>, private var list_item: Int, private var itemClickList: CategoryItemClickList) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem: View = layoutInflater.inflate(list_item, parent, false)
        return ViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val myListData: ListData = _listData[position]
        holder.textView.text = _listData[position].getDescription()

        val instructions = _listData[position].getContent()
        if (instructions != null) {
            holder.textViewContent.text = instructions
            holder.textViewContent.visibility = View.VISIBLE
        }

        holder.linearLayout.setOnClickListener { view ->
            val id = myListData.getID()
            val description = myListData.getDescription()
            itemClickList.onCategoryClick(id, description, view)
        }

        CoroutineScope(Dispatchers.IO).launch {
            var bitmap: Bitmap? = null
            try {
                val url = _listData[position].getImageURL()
                bitmap = if (URLUtil.isValidUrl(url)) {
                    URLUtil.isValidUrl(url)
                    val inputStream: InputStream = URL(url).openStream()
                    BitmapFactory.decodeStream(inputStream)
                } else {
                    val byteArray = Base64.decode(url, DEFAULT)
                    BitmapFactory.decodeByteArray(byteArray,
                        0, byteArray.size)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            withContext(Dispatchers.Main) {
                holder.imageView.setImageBitmap(bitmap)
            }
        }
    }


    override fun getItemCount(): Int {
        return _listData.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView
        var textView: TextView
        var textViewContent: TextView
        var linearLayout: LinearLayout

        init {
            imageView = itemView.findViewById<View>(R.id.imageView) as ImageView
            textView = itemView.findViewById<View>(R.id.textView) as TextView
            textViewContent = itemView.findViewById<View>(R.id.textViewContent) as TextView
            linearLayout = itemView.findViewById<View>(R.id.llMain) as LinearLayout
        }
    }
}