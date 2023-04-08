package com.example.pmd_lw_2

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import java.io.InputStream
import java.net.URL


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
        holder.textViewContent.text = _listData[position].getContent()
        holder.relativeLayout.setOnClickListener { view ->
            val slug = myListData.getSlug()
            val description = myListData.getDescription()
            if (slug != null && description != null) {
                itemClickList.onCategoryClick(slug, description, view)
            }
        }

        Thread {
            val url = URL(_listData[position].getImageURL())
            val inputStream: InputStream = url.openStream()
            val bitmap = BitmapFactory.decodeStream(inputStream)

            (holder.imageView.getContext() as AppCompatActivity).runOnUiThread {
                holder.imageView.setImageBitmap(bitmap)
            }
        }.start()
    }


    override fun getItemCount(): Int {
        return _listData.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView
        var textView: TextView
        var textViewContent: TextView
        var relativeLayout: RelativeLayout

        init {
            imageView = itemView.findViewById<View>(R.id.imageView) as ImageView
            textView = itemView.findViewById<View>(R.id.textView) as TextView
            textViewContent = itemView.findViewById<View>(R.id.textViewContent) as TextView
            relativeLayout = itemView.findViewById<View>(R.id.relativeLayout) as RelativeLayout
        }
    }
}