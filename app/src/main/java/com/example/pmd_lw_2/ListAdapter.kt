package com.example.pmd_lw_2

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ListAdapter(private var _listData: Array<ListData>) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem: View = layoutInflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val myListData: ListData = _listData[position]
        holder.textView.setText(_listData[position].getDescription())
        holder.imageView.setImageResource(_listData[position].getImageId())
        holder.relativeLayout.setOnClickListener { view ->
            val intent = Intent(view.context, RecipesActivity::class.java)
            intent.putExtra("slug", myListData.getSlug())
            view.context.startActivity(intent)
        }
    }


    override fun getItemCount(): Int {
        return _listData.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView
        var textView: TextView
        var relativeLayout: RelativeLayout

        init {
            imageView = itemView.findViewById<View>(R.id.imageView) as ImageView
            textView = itemView.findViewById<View>(R.id.textView) as TextView
            relativeLayout = itemView.findViewById<View>(R.id.relativeLayout) as RelativeLayout
        }
    }
}