package com.example.retrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofit.R
import com.example.retrofit.model.PostModel
import kotlinx.android.synthetic.main.post_item_layout.view.*

class PostAdapter(val itemss: List<PostModel>):RecyclerView.Adapter<PostAdapter.ItemsHolder>() {
    inner class ItemsHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsHolder {
        return ItemsHolder(LayoutInflater.from(parent.context).inflate(R.layout.post_item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ItemsHolder, position: Int) {
        val item = itemss[position]
        holder.itemView.tvTitle.text = item.text
        holder.itemView.tvDate.text = item.publishDate
        Glide.with(holder.itemView.context).load(item.image).into(holder.itemView.imagePost)
    }

    override fun getItemCount(): Int {
        return itemss.count()
    }


}
