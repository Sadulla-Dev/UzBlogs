package com.example.retrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofit.R
import com.example.retrofit.model.UserModel
import kotlinx.android.synthetic.main.item_layout.view.*

interface UserAdapterListener{
    fun onClick(item: UserModel)
}
class UserAdapter(val items: List<UserModel>, val adapterListener: UserAdapterListener):RecyclerView.Adapter<UserAdapter.ItemsHolder>() {
    inner class ItemsHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsHolder {
        return ItemsHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ItemsHolder, position: Int) {
        val item = items[position]
        holder.itemView.tvName.text = item.firstName
        Glide.with(holder.itemView.context).load(item.picture).into(holder.itemView.imgUser)



        holder.itemView.setOnClickListener {
            adapterListener.onClick(item)
        }
    }

    override fun getItemCount(): Int {
        return items.count()

    }

}
